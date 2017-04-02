package org.zahm.horsetrack.service;

import org.zahm.horsetrack.io.Output;
import org.zahm.horsetrack.model.CashDenomination;

import java.util.ArrayList;

/**
 * Service class to manage the cash inventory of the machine
 */
public class CashService {
    private static final String INVENTORY_TEXT = "Inventory:";

    private ArrayList<CashDenomination> inventory;

    public CashService() {
        restock();
    }

    /**
     * Resets the cash inventory to its initial amount
     *
     * Assume it is okay to lose track of the amount of money in the machine when restocking
     */
    public void restock() {
        inventory = new ArrayList<CashDenomination>();
        inventory.add(new CashDenomination(1,10));
        inventory.add(new CashDenomination(5,10));
        inventory.add(new CashDenomination(10,10));
        inventory.add(new CashDenomination(20, 10));
        inventory.add(new CashDenomination(100, 10));
    }

    /**
     * Performs the payout if the machine can service the request, otherwise an insufficient funds
     * message will be logged
     *
     * Work backwards through the list using the largest bills possible to service the request in order
     * to pay out using the fewest number of bills possible
     * @param payoutAmount
     */
    public void payout(int payoutAmount) {
        int remainingPayout = payoutAmount;

        for (int i = inventory.size()-1; i >= 0; i--) {
            CashDenomination denomination = inventory.get(i);
            int amountToDispense = denomination.calculateBillsToDispense(remainingPayout);
            remainingPayout -= amountToDispense;
        }

        // If we've gotten to the end and the payout amount is 0, "commit" the transaction, dispense the bills,
        // and log the message to the console
        if (remainingPayout == 0) {
            Output.logOutput("Dispensing:");
            for (CashDenomination denomination:inventory) {
                denomination.dispenseBills();
            }
        }
        // If the remaining amount to be paid out is not 0, we didn't have the right mix of bills
        // to service this request
        else {
            Output.logOutput(String.format("Insufficient Funds: %d", payoutAmount));
        }
    }

    /**
     * Print the status of the cash inventory
     */
    public void printStatus() {
        Output.logOutput(INVENTORY_TEXT);
        for (CashDenomination denomination: inventory) {
            denomination.printStatus();
        }
    }
}
