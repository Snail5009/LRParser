package org.example;

import java.util.ArrayList;
import java.util.HashSet;

public class GotoActionTable {
    private ArrayList<ArrayList<Goto>> gotoTable = new ArrayList<>();
    private ArrayList<ArrayList<Action>> actionTable = new ArrayList<>();
    private ArrayList<ParseTerminal> terminals = new ArrayList<>();
    private ArrayList<ParseNonterminal> nonterminals = new ArrayList<>();

    public GotoActionTable(ItemsList itemSets) {
        terminals.addAll(itemSets.getTerminals());
        nonterminals.addAll(itemSets.getNonterminals());


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

        addShifts(itemSets);
    }

    private void addShifts(ItemsList itemSets) {
        for (int i = 0; i < itemSets.size(); i++) {
            Items itemSet = itemSets.get(i);
            for (IndexedProduction p : itemSet.getProductions()) {
                if (!p.hasIndexed()) continue;
                Integer state = findStateWithAdvancedIndex(itemSets, p);
                System.out.printf("for %s, %d and %s\n", p, state, p.getNext());
                if (state == null) continue;
                setAction(i, p.getNext(), new Action(true, state));
            }
        }
    }

    private Integer findStateWithAdvancedIndex(ItemsList itemSets, IndexedProduction p) {
        if (!p.hasIndexed()) return null;

        p = p.clone();
        ParseNode relevantNote = p.getNext();
        p.advance();

        for (int i = 0; i < itemSets.size(); i++) {
            Items itemSet = itemSets.get(i);
            for (IndexedProduction production : itemSet.getProductions()) {
                if (production.equals(p)) {
                    return i;
                }
            }
        }

        return null;
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

    public void setAction(int state, ParseNode character, Action a) {
        if (character instanceof ParseTerminal) {
            if (terminals.contains(character)) {
                actionTable.get(state).set(terminals.indexOf(character), a);
            }
        }
    }

    public void setGoto(int state, ParseNode character, Goto a) {
        if (character instanceof ParseNonterminal) {
            if (nonterminals.contains(character)) {
                gotoTable.get(state).set(nonterminals.indexOf(character), a);
            }
        }
    }
}
