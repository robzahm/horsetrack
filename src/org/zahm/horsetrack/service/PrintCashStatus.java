package org.zahm.horsetrack.service;

import org.zahm.horsetrack.data.CashDataAccess;
import org.zahm.horsetrack.io.Output;
import org.zahm.horsetrack.model.Cash;

/**
 * Created by Zahm Robert on 4/3/2017.
 */
public class PrintCashStatus {
    private static final String INVENTORY_TEXT = "Inventory:";

    private static CashDataAccess cashDataAccess = CashDataAccess.getInstance();

    /**
     * Print the status of the cash inventory
     */
    public static void printStatus() {
        Output.logOutput(INVENTORY_TEXT);
        for (Cash cash: cashDataAccess.getOrderedCashInventory()) {
            cash.printStatus();
        }
    }
}
