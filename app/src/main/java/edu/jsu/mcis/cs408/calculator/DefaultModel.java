package edu.jsu.mcis.cs408.calculator;


import android.icu.number.ScientificNotation;
import android.util.Log;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class DefaultModel extends AbstractModel {

    public static final String TAG = "DefaultModel";
    private static final int MAX_SCREEN_SIZE = 11;

    /*
     * This is a simple implementation of an AbstractModel which encapsulates
     * two text fields, text1 and text2, which (in this example) are each
     * reflected in the View as an EditText field and a TextView label.
     */

    private String text1;
    private StringBuilder output = new StringBuilder();
    private boolean rhsClear = false;
    private boolean hasDecimal = false;
    private boolean percentAdd;
    private boolean errorMode = false;
    private boolean percentSubtract = false;
    private BigDecimal lhs = new BigDecimal(0);
    private BigDecimal rhs = new BigDecimal(0);
    private BigDecimal result;
    private Operator operator = Operator.NONE;

    private String text2;
    //private String clear = context.getResources().getString(R.string.placeHolderNum);
    private CalculatorState calState = CalculatorState.CLEAR;

    /*
     * Initialize the model elements to known default values.  We use the setter
     * methods instead of changing the values directly so that these changes are
     * properly announced to the Controller, and so that the Views can be updated
     * accordingly.
     */

    public void initDefault() {

        //setOutput("0");
        setText2("Sample Text 2");
        //setCalState(calState.CLEAR);

    }

    /*
     * Simple getter methods for text1 and text2
     */

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    /*
     * Setters for text1 and text2.  Notice that, in addition to changing the
     * values, these methods announce the change to the controller by firing a
     * PropertyChange event.  Any registered AbstractController subclasses will
     * receive this event, and will propagate it to all registered Views so that
     * they can update themselves accordingly.
     */

    public void setOutput(String newText) {
        String oldText = this.output.toString();
        if (!(operator.equals(Operator.NONE)) && !rhsClear) {
            rhsClear = true;
            output = new StringBuilder();
        }

        if (newText.equals(".") && !hasDecimal) {
            this.output.append(newText);
            hasDecimal = true;
        } else if (newText.equals(".") && hasDecimal) {
            //Do nothing.
        } else if ((output.length() + 1) >= MAX_SCREEN_SIZE) {
            //Do nothing.
        } else {
            this.output = output.append(newText);
        }

        Log.i(TAG, "Text1 Change: From " + oldText + " to " + newText);

        if(!errorMode)
            firePropertyChange(DefaultController.ELEMENT_OUTPUT_PROPERTY, oldText, output.toString());

        if (output.length() == 1 && !(calState.equals(CalculatorState.RHS))) {
            setCalState(CalculatorState.LHS);
        } else {
            runCalState();
        }

    }

    public void setOperatorSymbol(String newText) {

        String oldText = this.output.toString();
        if (!(operator.equals(Operator.NONE)) && !(operator.equals(Operator.PERCENT))) {
            if(calState.equals(CalculatorState.RHS)) {
                //Do Nothing.
            }
            else {
                this.output.deleteCharAt(output.length() - 1);
                this.output.append(newText);
            }
        }
        else {
            this.output = output.append(newText);
        }
        Log.i(TAG, "Text1 Change: From " + oldText + " to " + newText);
        if(!errorMode)
            firePropertyChange(DefaultController.ELEMENT_OUTPUT_PROPERTY, oldText, output.toString());

        runCalState();

    }

    public void setCalState(CalculatorState newState) {

        CalculatorState oldState = this.calState;
        this.calState = newState;
        firePropertyChange(DefaultController.ELEMENT_CALSTATE_PROPERTY, oldState, newState);
        runCalState();

    }

    public void setOperator(Operator op) {

        operator = op;
    }

    public void runCalState() {

        if(calState.equals(CalculatorState.CLEAR)) {
            errorMode = false;
            clear();
            firePropertyChange(DefaultController.ELEMENT_OUTPUT_PROPERTY, output.toString(), "0");
        }
        else if(calState.equals(CalculatorState.LHS)) {
            lhs = new BigDecimal(output.toString());
        }
        else if(calState.equals(CalculatorState.OP_SELECTED)) {
                calState = CalculatorState.RHS;
                hasDecimal = false;
                if(operator.equals(Operator.ADD)) {
                    percentAdd = true;
                }
                else if(operator.equals(Operator.SUBTRACT)) {
                    percentSubtract = true;
                }
        }
        else if(calState.equals(CalculatorState.RHS)) {

            rhs = new BigDecimal(output.toString());
        }
        else if(calState.equals(CalculatorState.RESULT)) {
            rhsClear = false;
            if(operator.equals(Operator.ADD)) {
                result = lhs.add(rhs);
            }
            else if(operator.equals(Operator.SUBTRACT)) {
                result = lhs.subtract(rhs);
            }
            else if(operator.equals(Operator.MULTIPLY)) {
                result = lhs.multiply(rhs);
            }
            else if(operator.equals(Operator.DIVIDE)) {
                try {
                    result = lhs.divide(rhs, 3, RoundingMode.HALF_UP);
                }
                catch(ArithmeticException e) {
                    Log.e(TAG, "An Error has occured " + e.getMessage(), e);
                    setCalState(CalculatorState.ERROR);


                }
            }
            else if(operator.equals(Operator.SIGN)) {
                result = lhs.negate();
            }
            else if(operator.equals(Operator.ROOT)) {

                if(lhs.compareTo(new BigDecimal(0)) == -1) {
                    setCalState(CalculatorState.ERROR);
                }
                else {
                    double num;
                    num = Math.sqrt(lhs.doubleValue());
                    result = BigDecimal.valueOf(num);
                    result = result.round(new MathContext(5));
                }
            }
            else if(operator.equals(Operator.PERCENT)) {
                try {
                    rhs = rhs.divide(new BigDecimal(100));
                    result = lhs.multiply(rhs);
                    if(percentAdd) {
                        result = lhs.add(result);
                    }
                    else if(percentSubtract) {
                        result = lhs.subtract(result);
                    }
                }
                catch(ArithmeticException e) {
                    Log.e(TAG, "An Error has occured " + e.getMessage(), e);
                    setCalState(CalculatorState.ERROR);


                }


            }

            String resultString = result.toString();
            if(!errorMode)
                firePropertyChange(DefaultController.ELEMENT_OUTPUT_PROPERTY, output.toString(), resultString);

            lhs = result;
            hasDecimal = false;
            percentAdd = false;
            percentSubtract = false;
            operator = Operator.NONE;
            output = new StringBuilder();
            output.append(result.toString());
            calState = CalculatorState.LHS;
        }
        else if(calState.equals(CalculatorState.ERROR)) {
            errorMode = true;
            firePropertyChange(DefaultController.ELEMENT_OUTPUT_PROPERTY, output.toString(), "ERROR");
            clear();

        }
    }

    public void clear() {
        this.result = new BigDecimal(0);
        this.lhs = new BigDecimal(0);
        this.rhs = new BigDecimal(0);
        this.operator = Operator.NONE;
        output = new StringBuilder();
    }

    public void setText2(String newText) {

        String oldText = this.text2;
        this.text2 = newText;

        Log.i(TAG, "Text2 Change: From " + oldText + " to " + newText);

        firePropertyChange(DefaultController.ELEMENT_TEXT2_PROPERTY, oldText, newText);

    }

}
