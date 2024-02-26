package edu.jsu.mcis.cs408.calculator;


import android.util.Log;


public class DefaultModel extends AbstractModel {

    public static final String TAG = "DefaultModel";

    /*
     * This is a simple implementation of an AbstractModel which encapsulates
     * two text fields, text1 and text2, which (in this example) are each
     * reflected in the View as an EditText field and a TextView label.
     */

    private String text1;
    private StringBuilder output = new StringBuilder();

    private String text2;
    //private String clear = context.getResources().getString(R.string.placeHolderNum);
    private CalculatorState calState;

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
        this.output = output.append(newText);

        Log.i(TAG, "Text1 Change: From " + oldText + " to " + newText);

        firePropertyChange(DefaultController.ELEMENT_OUTPUT_PROPERTY, oldText, output.toString());

    }

    public void setCalState(CalculatorState newState) {

        CalculatorState oldState = this.calState;
        this.calState = newState;
        firePropertyChange(DefaultController.ELEMENT_CALSTATE_PROPERTY, oldState, newState);
        runCalState(calState);

    }
    public void runCalState(CalculatorState state) {

        if(state.equals(CalculatorState.CLEAR)) {
            firePropertyChange(DefaultController.ELEMENT_OUTPUT_PROPERTY, output.toString(), "0");
            output = new StringBuilder();
        }
    }

    public void setText2(String newText) {

        String oldText = this.text2;
        this.text2 = newText;

        Log.i(TAG, "Text2 Change: From " + oldText + " to " + newText);

        firePropertyChange(DefaultController.ELEMENT_TEXT2_PROPERTY, oldText, newText);

    }

}
