package org.zahm.horsetrack.service;

import org.zahm.horsetrack.data.CashDataAccess;
import org.zahm.horsetrack.io.Output;
import org.zahm.horsetrack.model.Cash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Service class to manage the cash inventory of the machine
 */
public class CashService {
    private static final String INVENTORY_TEXT = "Inventory:";

    public CashService() {    }

    /**
     * Restocks the cash inventory
     */
    public void restock() {
        CashDataAccess.getInstance().restock();
    }

    private ArrayList<Cash> getOrderedCashInventory() {
        return CashDataAccess.getInstance().getOrderedCashInventory();
    }

    /**
     * Performs the payout if the machine can service the request, otherwise an insufficient funds
     * message will be logged
     *
     * Work backwards through the list using the largest bills possible to service the request in order
     * to pay out using the fewest number of bills possible
     *
     * Will need to keep track of the dispensed bills so that we can commit or roll back if it turns out that we don't have
     * the right mix of bills to service the request (method synchronized to prevent concurrent payouts that could lead
     * to an inconsistent state)
     * @param payoutAmount
     */
    public synchronized void payout(int payoutAmount) {
        // Variable to track the remaining payout as we put together the bills to dispense
        int remainingPayout = payoutAmount;

        // List to track the number of dispensed bills
        HashMap<Cash, Integer> billsToDispense = new HashMap<Cash, Integer>();

        for (int i = getOrderedCashInventory().size()-1; i >= 0; i--) {
            Cash cash = getOrderedCashInventory().get(i);

            // Find the number of bills of this denomination to dispense, and note it in the map
            int numBillsToDispense = cash.calculateNumBillsToDispense(remainingPayout);
            billsToDispense.put(cash, numBillsToDispense);

            // Calculate the amount that will be dispensed, and decrease the remaining amount by this value
            int amountToDispense = numBillsToDispense * cash.getDenomination();
            remainingPayout -= amountToDispense;

            if (remainingPayout == 0)
                break;
        }

        // If we've gotten to the end and the payout amount is 0, "commit" the transaction, dispense the bills,
        // and log the message to the console
        if (remainingPayout == 0) {
            Output.logOutput("Dispensing:");
            for (Cash cash:getOrderedCashInventory()) {
                int numBillsToDispense = 0;
                if (billsToDispense.containsKey(cash))
                    numBillsToDispense =  billsToDispense.get(cash);

                cash.dispenseBills(numBillsToDispense);
                Output.logOutput(String.format("$%d,%d", cash.getDenomination(), numBillsToDispense));
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
        for (Cash cash: getOrderedCashInventory()) {
            cash.printStatus();
        }
    }
}
