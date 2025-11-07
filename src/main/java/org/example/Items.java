package org.example;

import java.util.ArrayList;

public class Items {
    private ArrayList<IndexedProduction> productions = new ArrayList<>();

    public Items() {
        
    }

    public void addProduction(IndexedProduction production) {
        productions.add(production.clone());
    }

    public ArrayList<IndexedProduction> getProductions() {
        return productions;
    }

    @Override
    public String toString() {
        StringBuilder itemStrings = new StringBuilder();
        productions.forEach((p) -> {
            itemStrings.append(p.toString());
            itemStrings.append("\n");
        });
        return itemStrings.toString();
    }
}
