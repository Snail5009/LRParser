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

    public ArrayList<Items> getList() {
        return itemSets;
    }

    public void deleteUnusedItemSets() {
        itemSets.removeIf((i) -> {
            return i.getProductions().size() == 0;
        });
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < itemSets.size(); i++) {
            result.append("I_");
            result.append(i);
            result.append(":\n");
            result.append(itemSets.get(i).toString());
        }
        return result.toString();
    }
}
