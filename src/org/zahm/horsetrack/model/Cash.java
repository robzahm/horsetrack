package org.zahm.horsetrack.model;

import org.zahm.horsetrack.io.Output;

/**
 * Domain object for a specific cash denomination
 */
public class Cash implements Comparable<Cash> {
    // Value of this cash denomination (e.g., $1, $5, etc.)
    private int denomination;

    // Number of bills in the machine
    private int numBills;

    public Cash(int theDenomination, int theNumBills) {
        this.setDenomination(theDenomination);
        this.setNumBills(theNumBills);
    }

    /**
     * Prints the status of this cash denomination
     */
    public void printStatus() {
        Output.logOutput(String.format("$%d,%d",
                getDenomination(), getNumBills()));
    }

    /**
     * Returns the number of bills of this denomination that will be dispensed to service the payout
     * @param amount
     * @return
     */
    public int calculateNumBillsToDispense(int amount) {
        // Determine the theoretical number of bills of this denomination that can service the request
        int numBillsToDispense = Math.floorDiv(amount, denomination);

        // Return the actual number of bills of this denomination that can service the request
        // (e.g, there might not be enough bills to meet the need)
        return Math.min(numBillsToDispense, numBills);
    }

    /**
     * Dispense the bills that were set in the calculation
     */
    public void dispenseBills(int numBillsToDispense) {
        this.numBills -= numBillsToDispense;
    }

    /**
     * Denomination-based sort
     * @param otherCash
     * @return
     */
    public int compareTo(Cash otherCash) {
        return this.getDenomination() - otherCash.getDenomination();
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    public int getNumBills() {
        return numBills;
    }

    public void setNumBills(int numBills) {
        this.numBills = numBills;
    }
}
