package edu.jsu.mcis.cs408.calculator;

public class DefaultController extends AbstractController
{

    public static final String ELEMENT_OUTPUT_PROPERTY = "Output";
    public static final String ELEMENT_CALSTATE_PROPERTY = "CalState";
    public static final String ELEMENT_OPERATOR_PROPERTY = "Operator";
    public static final String ELEMENT_OPERATORSYMBOL_PROPERTY = "OperatorSymbol";
    public static final String ELEMENT_EQUALS_PROPERTY = "Equals";
    public static final String ELEMENT_UNARYOP_PROPERTY = "UnaryOp";
    public static final String ELEMENT_SIGN_PROPERTY ="Sign";
    public static final String ELEMENT_ROOT_PROPERTY = "Root";

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
