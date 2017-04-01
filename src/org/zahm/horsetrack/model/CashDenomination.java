package org.zahm.horsetrack.model;

/**
 * Created by Zahm Robert on 4/1/2017.
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

    // Change to print/log from a central location
    public void printStatus() {
        System.out.println(String.format("$%d,%d",
                getDenominationValue(), getNumBills()));
    }

    // Return the amount of money that will be dispensed
    public int getAmountToDispense(int amount) {
        // Determine the theoretical number of bills of this denomination that can service the request
        numBillsToDispense = Math.floorDiv(amount, denominationValue);

        // Return the actual number of bills of this denomination that can service the request
        // (e.g, there might not be enough bills to meet the need)
        numBillsToDispense = Math.min(numBillsToDispense, numBills);

        // Return the amount being dispensed
        return numBillsToDispense * denominationValue;
    }

    // "Commit" the transaction and dispense the bills
    public void dispenseBills() {
        this.numBills -= numBillsToDispense;
        System.out.println(String.format("$%d,%d", denominationValue, numBillsToDispense));
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
