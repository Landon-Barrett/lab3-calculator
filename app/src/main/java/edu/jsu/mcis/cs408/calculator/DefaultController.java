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

    /*
     * This is the change method which corresponds to ELEMENT_TEXT2_PROPERTY.
     */

    public void changeElementText2(String newText) {
        setModelProperty(ELEMENT_TEXT2_PROPERTY, newText);
    }

}
