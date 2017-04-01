package org.zahm.horsetrack.manager;

import org.zahm.horsetrack.model.CashDenomination;

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

    // Work backwards through the list using the largest bills possible to service the request
    public void payout(int payoutAmount) {
        int remainingPayout = payoutAmount;

        for (int i = inventory.size()-1; i >= 0; i--) {
            CashDenomination denomination = inventory.get(i);
            int amountToDispense = denomination.getAmountToDispense(payoutAmount);
            remainingPayout -= amountToDispense;
        }

        // If we've gotten to the end and the payout amount is 0, "commit" the transaction, dispense the bills,
        // and log the message to the console
        if (remainingPayout == 0) {
            System.out.println("Dispensing:");
            for (CashDenomination denomination:inventory) {
                denomination.dispenseBills();
            }
        } else {
            System.out.println(String.format("Insufficient Funds: %d", payoutAmount));
        }
    }

    public void printStatus() {
        System.out.println(INVENTORY_TEXT);
        for (CashDenomination denomination: inventory) {
            denomination.printStatus();
        }
    }
}
