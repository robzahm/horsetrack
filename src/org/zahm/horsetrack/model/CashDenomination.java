package org.zahm.horsetrack.model;

import org.zahm.horsetrack.io.Output;

/**
 * Domain object for a specific cash denomination
 */
public class CashDenomination {
    // Value of this cash denomination (e.g., $1, $5, etc.)
    private int denominationValue;

    // Number of bills in the machine
    private int numBills;

    // Number of bills that will service a request
    // Used as a "transaction" in case the combination of money in the machine cannot service the request
    private int numBillsToDispense = 0;

    public CashDenomination(int theDenominationValue, int theNumBills) {
        this.setDenominationValue(theDenominationValue);
        this.setNumBills(theNumBills);
    }

    /**
     * Prints the status of this cash denomination
     */
    public void printStatus() {
        Output.logOutput(String.format("$%d,%d",
                getDenominationValue(), getNumBills()));
    }

    /**
     * Returns the number of bills of this denomination that will be dispensed to complete
     * the payout amount
     * @param amount
     * @return
     */
    public int calculateBillsToDispense(int amount) {
        // Determine the theoretical number of bills of this denomination that can service the request
        numBillsToDispense = Math.floorDiv(amount, denominationValue);

        // Return the actual number of bills of this denomination that can service the request
        // (e.g, there might not be enough bills to meet the need)
        numBillsToDispense = Math.min(numBillsToDispense, numBills);

        // Return the dollar amount being dispensed
        return numBillsToDispense * denominationValue;
    }

    /**
     * Dispense the bills that were set in the calculation
     * !! Should this state be offloaded to the service?
     */
    public void dispenseBills() {
        this.numBills -= numBillsToDispense;
        Output.logOutput(String.format("$%d,%d", denominationValue, numBillsToDispense));
    }


    public int getDenominationValue() {
        return denominationValue;
    }

    public void setDenominationValue(int denominationValue) {
        this.denominationValue = denominationValue;
    }

    public int getNumBills() {
        return numBills;
    }

    public void setNumBills(int numBills) {
        this.numBills = numBills;
    }
}
