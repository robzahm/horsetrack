package org.zahm.horsetrack.model;

/**
 * Created by Zahm Robert on 4/1/2017.
 */
public class CashDenomination {
    private int denominationValue;
    private int numBills;

    public CashDenomination(int theDenominationValue, int theNumBills) {
        this.setDenominationValue(theDenominationValue);
        this.setNumBills(theNumBills);
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
