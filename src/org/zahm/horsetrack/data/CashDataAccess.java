package org.zahm.horsetrack.data;

import org.zahm.horsetrack.model.Cash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Class to manage the state of the horses in lieu of a database
 * Singleton class for simplicity of state maintenance
 */
public class CashDataAccess {
    private static CashDataAccess instance = new CashDataAccess();

    private ArrayList<Cash> inventory;

    protected CashDataAccess() {
        restock();
    }

    public static CashDataAccess getInstance() {
        return CashDataAccess.instance;
    }

    /**
     * Resets the cash inventory to its initial amount
     *
     * Assume it is okay to lose track of the amount of money in the machine when restocking
     */
    public void restock() {
        inventory = new ArrayList<Cash>();
        inventory.add(new Cash(1,10));
        inventory.add(new Cash(5,10));
        inventory.add(new Cash(10,10));
        inventory.add(new Cash(20, 10));
        inventory.add(new Cash(100, 10));

        Collections.sort(inventory);
    }

    public Collection<Cash> getCashInventory() {
        return inventory;
    }

    public ArrayList<Cash> getOrderedCashInventory() {
        return inventory;
    }

}
