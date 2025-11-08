package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

public class GotoActionTable {
    private ArrayList<ArrayList<Goto>> gotoTable = new ArrayList<>();
    private ArrayList<ArrayList<Action>> actionTable = new ArrayList<>();
    private ItemsList itemSets;
    private HashSet<ParseTerminal> terminals;
    private HashSet<ParseNonterminal> nonterminals;

    public GotoActionTable(ItemsList itemSets) {
        terminals = itemSets.getTerminals();
        nonterminals = itemSets.getNonterminals();


        for (int i = 0; i < itemSets.size(); i++) {
            gotoTable.add(new ArrayList<Goto>());
            for (int j = 0; j < nonterminals.size(); j++) {
                gotoTable.get(i).add(new Goto());
            }
        }

        for (int i = 0; i < itemSets.size(); i++) {
            actionTable.add(new ArrayList<Action>());
            for (int j = 0; j < terminals.size(); j++) {
                actionTable.get(i).add(new Action());
            }
        }

        this.itemSets = itemSets;
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        table.append("       | ");

        for (ParseNode i : terminals) {
            table.append("%5s | ".formatted(i));
        }
        for (ParseNode i : nonterminals) {
            table.append("%5s | ".formatted(i));
        }
        table.append("\n");
        assert(gotoTable.size() == actionTable.size());
        for (int i = 0; i < gotoTable.size(); i++) {
            table.append(" %5d | ".formatted(i));
            for (Action j : actionTable.get(i)) {
                table.append("%5s | ".formatted(j));
            }
            for (Goto j : gotoTable.get(i)) {
                table.append("%5s | ".formatted(j));
            }
            table.append("\n");
        }
        return table.toString();
    }
}
