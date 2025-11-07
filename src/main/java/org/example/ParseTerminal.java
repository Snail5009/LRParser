package org.example;


public class ParseTerminal extends ParseNode {
    private String value;

    public ParseTerminal(String type) {
        super(type);
    }

    public String getValue() {
        if (value == null) {
            throw new RuntimeException("getValue() cannot be called with a " +
                "valueless terminal.");
        }
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public ParseTerminal clone() {
        return new ParseTerminal(type);
    }
}
