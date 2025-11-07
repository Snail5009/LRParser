package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class IndexedProduction extends Production {
    private int index;
    public IndexedProduction(ParseNonterminal from,
        ParseNode[] to, int index) {
        super(from, to);
        check(index);
        this.index = index;
    }

    public IndexedProduction(Production production, int index) {
        super((ParseNonterminal)production.from, production.to);
        check(index);
        this.index = index;
    }

    @Override
    public IndexedProduction clone() {
        return new IndexedProduction((ParseNonterminal)from.clone(), to.clone(), index);
    }

    public int getIndex() {
        return index;
    }

    public ParseNode getNext() {
        if (to[index] != null) return to[index];
        else throw new RuntimeException("Cannot get the next value when the index is at the " +
        "end of the production.");
    }

    public boolean hasIndexed() {
        if (index >= to.length) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(from.getType());
        string.append(" -> ");
        for (int i = 0; i < to.length; i++) {
            if (i == index) string.append(".");
            string.append("<");
            string.append(to[i].getType());
            string.append(">");
        }
        if (index == to.length) string.append(".");
        return string.toString();
    }

    public void advance() {
        check(index + 1);
        index++;
    }

    public void stepBack() {
        check(index - 1);
        index--;
    }

    private void check(int index) {
        if (index > to.length || index < 0)
            throw new RuntimeException("Cannot have an indexed production whose " +
            "index goes beyond its bounds.");
    }

    @Override
    public boolean equals(Object rhs) {
        if (!(rhs instanceof IndexedProduction)) return false;
        IndexedProduction p = (IndexedProduction)rhs;
        
        if (!p.from.equalsType(from) || p.index != index) {
            return false;
        }

        if (p.to.length != to.length) return false;

        for (int i = 0; i < to.length; i++) {
            if (!p.to[i].equalsType(to[i])) return false;
        }
        return true;
    }
}
