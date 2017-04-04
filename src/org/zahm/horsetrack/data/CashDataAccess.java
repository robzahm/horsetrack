package org.zahm.horsetrack.data;

import org.zahm.horsetrack.model.Cash;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage the state of the horses in lieu of a database
 * Singleton class for simplicity of state maintenance
 */
public class CashDataAccess {
    private static CashDataAccess instance = new CashDataAccess();

    public static CashDataAccess getInstance() {
        return CashDataAccess.instance;
    }

    // Ordered list of the cash inventory
    private ArrayList<Cash> inventory;

    protected CashDataAccess() {
        restock();
    }

    // Assume it is okay to lose track of the amount of money in the machine when restocking
    public void restock() {
        // Assuming data is added to the list in sorted order
        inventory = new ArrayList<Cash>();
        inventory.add(new Cash(1,10));
        inventory.add(new Cash(5,10));
        inventory.add(new Cash(10,10));
        inventory.add(new Cash(20, 10));
        inventory.add(new Cash(100, 10));
    }

    public List<Cash> getCashInventory() {
        return inventory;
    }

}
