package org.example;

import java.util.HashSet;

public class TemporaryMain {

    public static void main(String[] args) {
        ParseNonterminal S = new ParseNonterminal("S");
        ParseNonterminal E = new ParseNonterminal("E");
        ParseNonterminal V = new ParseNonterminal("V");

        ParseTerminal plus = new ParseTerminal("+");
        ParseTerminal integer = new ParseTerminal("integer");
        ParseTerminal eof = new ParseTerminal("$");

        Grammar grammar = new Grammar();
        grammar.addProduction(S, new ParseNode[] { E, eof });
        grammar.addProduction(E, new ParseNode[] { E, plus, V });
        grammar.addProduction(E, V);
        grammar.addProduction(V, integer);

        System.out.printf("Grammarrrrr:\n%s\n", grammar);


        ItemsList itemSets = new ItemsList();
        

        int n = 0;
        
        // (1) adding the grammar rules
        itemSets.add(new Items());
        for (Production p : grammar.getProductions()) {
            itemSets.get(n).addProduction(new IndexedProduction(p, 0));
        }
        System.out.printf("I_%d:\n%s\n", n, itemSets.get(n));

        while (true) {

            HashSet<ParseNode> A = new HashSet<>();

            if (itemSets.size() == n) break;

            // (3) obtaining the indexed characters of I_n
            for (IndexedProduction p : itemSets.get(n).getProductions()) {
                if (!p.hasIndexed()) continue;

                /// slight alteration, but I don't need to keep going if it's
                /// a $ ////
                if (p.getNext().type == "$") continue;

                if (!A.contains(p.getNext())) {
                    A.add(p.getNext());
                }
            }

            // (4)
            for (ParseNode pn : A) {
                boolean existsItemSet = false;
                for (IndexedProduction p : itemSets.get(n).getProductions()) {
                    if (!p.hasIndexed()) continue;
                    if (p.getNext() == pn) {
                        if (!existsItemSet) {
                            itemSets.add(new Items());
                            existsItemSet = true;
                        }
                        IndexedProduction newItem = p.clone();
                        newItem.advance();
                        if (!itemSets.isUnique(newItem)) {
                            System.out.printf("NOOOOO NOT UNIQUE (%s would " +
                            "be added but it is not unique)\n", newItem);
                            continue;
                        }
                        itemSets.getLast().addProduction(newItem);
                    }
                }
                System.out.printf("I_%d:\n%s\n", itemSets.size() - 1, itemSets.getLast());
            }
            n++;
        }


        ParseString string = new ParseString();
        string.add(integer);
        string.add(plus);
        string.add(integer);

        System.out.printf("Stringggg:\n%s\n", string);
        
    }
}
