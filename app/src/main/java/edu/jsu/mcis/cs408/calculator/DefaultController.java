package edu.jsu.mcis.cs408.calculator;

public class DefaultController extends AbstractController
{

    /*
     * These static property names are used as the identifiers for the elements
     * of the Models and Views which may need to be updated.  These updates can
     * be a result of changes to the Model which must be reflected in the View,
     * or a result of changes to the View (in response to user input) which must
     * be reflected in the Model.
     */

    public static final String ELEMENT_OUTPUT_PROPERTY = "Output";
    public static final String ELEMENT_TEXT2_PROPERTY = "Text2";
    public static final String ELEMENT_CALSTATE_PROPERTY = "CalState";
    public static final String ELEMENT_OPERATOR_PROPERTY = "Operator";
    public static final String ELEMENT_OPERATORSYMBOL_PROPERTY = "OperatorSymbol";
    public static final String ELEMENT_EQUALS_PROPERTY = "Equals";
    public static final String ELEMENT_UNARYOP_PROPERTY = "UnaryOp";
    public static final String ELEMENT_SIGN_PROPERTY ="Sign";
    public static final String ELEMENT_ROOT_PROPERTY = "Root";

    /*
     * This is the change method which corresponds to ELEMENT_TEXT1_PROPERTY.
     * It receives the new data for the Model, and invokes "setModelProperty()"
     * (inherited from AbstractController) so that the proper Model can be found
     * and updated properly.
     */

    public void changeElementOutput(String newText) {
        setModelProperty(ELEMENT_OUTPUT_PROPERTY, newText);
    }

    public void changeElementCalState(CalculatorState newState) {
        setModelProperty(ELEMENT_CALSTATE_PROPERTY, newState);
    }

    public void changeElementOperator(Operator op) {
        setModelProperty(ELEMENT_OPERATOR_PROPERTY, op);
    }

    public void changeElementOperatorSymbol(String newText) {
        setModelProperty(ELEMENT_OPERATORSYMBOL_PROPERTY, newText);
    }

    /*
     * This is the change method which corresponds to ELEMENT_TEXT2_PROPERTY.
     */

    public void changeElementEquals(String newText) {
        setModelProperty(ELEMENT_EQUALS_PROPERTY, newText);
    }
    public void changeElementUnaryOp(String newText) {
        setModelProperty(ELEMENT_UNARYOP_PROPERTY, newText);
    }
    public void changeElementSign(String newText) {
        setModelProperty(ELEMENT_SIGN_PROPERTY, newText);
    }
    public void changeElementRoot(String newText) {
        setModelProperty(ELEMENT_ROOT_PROPERTY, newText);
    }

}
