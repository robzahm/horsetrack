package org.zahm.horsetrack.manager;

import org.zahm.horsetrack.model.CashDenomination;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Zahm Robert on 4/1/2017.
 */
public class InventoryManager {
    public static final String INVENTORY_TEXT = "Inventory:";

    private ArrayList<CashDenomination> inventory;

    public InventoryManager() {
        restock();
    }

    // Assume it is okay to lose track of the amount of money in the machine when restocking
    public void restock() {
        inventory = new ArrayList<CashDenomination>();
        inventory.add(new CashDenomination(1,10));
        inventory.add(new CashDenomination(5,10));
        inventory.add(new CashDenomination(10,10));
        inventory.add(new CashDenomination(20, 10));
        inventory.add(new CashDenomination(100, 10));
    }

    public void printStatus() {
        System.out.println(INVENTORY_TEXT);
        for (CashDenomination denomination: inventory) {
            denomination.printStatus();
        }
    }
}
