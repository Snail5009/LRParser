package org.example;

public class Production {
    private ParseNode from;
    private ParseNode[] to;

    public Production(ParseNonterminal from, ParseNode[] to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(from.getType());
        string.append(" -> ");
        for (ParseNode i : to) {
            string.append("<");
            string.append(i.getType());
            string.append(">");
        }
        return string.toString();
    }

    public ParseNode getFrom() {
        return from;
    }

    public ParseNode[] getTo() {
        return to;
    }
}
