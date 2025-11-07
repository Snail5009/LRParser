package org.example;

import java.util.ArrayList;

public class ItemsList {
    private ArrayList<Items> itemSets;

    public ItemsList(ArrayList<Items> itemSets) {
        this.itemSets = itemSets;
    }
    
    public ItemsList() {
        itemSets = new ArrayList<>();
    }

    public boolean isUnique(IndexedProduction p) {
        for (Items itemSet : itemSets) {
            for (IndexedProduction production : itemSet.getProductions()) {
                if (production.equals(p)) return false;
            }
        }
        return true;
    }

    public void add(Items itemSet) {
        itemSets.add(itemSet);
    }

    public Items get(int index) {
        return itemSets.get(index);
    }

    public Items getLast() {
        return itemSets.getLast();
    }

    public int size() {
        return itemSets.size();
    }
}
