package edu.jsu.mcis.cs408.calculator;
public enum Operator {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("x"),
    DIVIDE("\u00F7"),
    ROOT("\u221A"),
    SIGN("\u00B1"),
    PERCENT("%"),
    NONE("");

    private final String symbol;   // in kilograms
    Operator(String symbol) {
        this.symbol = symbol;
    }
    private String symbol() { return symbol; }

}

