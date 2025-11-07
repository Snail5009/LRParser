package org.example;

public abstract class ParseNode {
    private String type;

    public ParseNode(String type)
        throws IllegalArgumentException {
        if (type == null || type.length() == 0)
            throw new IllegalArgumentException("A node must have at least a non-empty " +
                "type.");
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
