package org.example;

import java.util.ArrayList;

public class Grammar {
    private ArrayList<Production> productions = new ArrayList<>();

    public Grammar() {

    }

    @Override
    public String toString() {
        StringBuilder productionsStrings = new StringBuilder();
        productions.forEach((p) -> {
            productionsStrings.append(p.toString());
        });
        return productionsStrings.toString();
    }

    public void addProduction(ParseNonterminal from, ParseNode[] to) {
        productions.add(new Production(from, to));
    }

    public void addProduction(ParseNonterminal from, ParseNode to) {
        addProduction(from, new ParseNode[]{ to });
    }
}
