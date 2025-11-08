package org.example;

import java.util.ArrayList;
import java.util.HashSet;

public class Grammar {
    private ArrayList<Production> productions = new ArrayList<>();

    public Grammar() {

    }

    @Override
    public String toString() {
        StringBuilder productionsStrings = new StringBuilder();
        productions.forEach((p) -> {
            productionsStrings.append(p.toString());
            productionsStrings.append("\n");
        });
        return productionsStrings.toString();
    }

    public void addProduction(ParseNonterminal from, ParseNode[] to) {
        productions.add(new Production(from, to));
    }

    public void addProduction(ParseNonterminal from, ParseNode to) {
        addProduction(from, new ParseNode[]{ to });
    }

    public ArrayList<Production> getProductions() {
        return productions;
    }

    public ItemsList generateItemSets() {
        ItemsList itemSets = new ItemsList();
        

        int n = 0;
        
        // (1) adding the grammar rules
        itemSets.add(new Items());
        for (Production p : getProductions()) {
            itemSets.get(n).addProduction(new IndexedProduction(p, 0));
        }
        itemSets.add(new Items());

        while (true) {

            HashSet<ParseNode> A = new HashSet<>();

            if (itemSets.size() == n) break;

            // (3) obtaining the indexed characters of I_n
            for (IndexedProduction p : itemSets.get(n).getProductions()) {
                if (!p.hasIndexed()) continue;

                /// slight alteration to the pdf
                /// (which is also inaccurate but oh well
                /// I'm not gonna redo it right now),
                /// but I don't need to keep going if it's
                /// a $ ////
                if (p.getNext().type == "$") continue;

                if (!A.contains(p.getNext())) {
                    A.add(p.getNext());
                }
            }

            // (4) shifing
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
                            continue;
                        }
                        itemSets.getLast().addProduction(newItem);
                    }
                }
                
                // (5) reducing

                for (IndexedProduction p : addReductions(itemSets.getLast(), itemSets.get(0))) {
                    itemSets.getLast().addProduction(p);
                }

            }
            
            n++;
        }

        itemSets.deleteUnusedItemSets();

        return itemSets;
    }

    private static ArrayList<IndexedProduction> addReductions(Items itemSet, Items i0) {
        ArrayList<IndexedProduction> toAdd = new ArrayList<>();
        for (IndexedProduction p : itemSet.getProductions()) {
            if (!p.hasIndexed()) continue;
            if (p.getNext() instanceof ParseNonterminal) {
                for (IndexedProduction i0Production
                    : i0.getProductions()) {
                    if (i0Production.getFrom().equalsType(p.getNext())) {
                        if (!toAdd.contains(i0Production) && !itemSet.getProductions().contains(i0Production)) {
                            toAdd.add(i0Production);
                        }
                    }
                }
            }
        }
        if (toAdd.size() > 0) toAdd.addAll(addReductions(new Items(toAdd), i0));
        return toAdd;
    }
}
