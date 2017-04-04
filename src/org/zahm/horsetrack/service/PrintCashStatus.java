package org.zahm.horsetrack.service;

import org.zahm.horsetrack.data.CashDataAccess;
import org.zahm.horsetrack.io.Output;
import org.zahm.horsetrack.model.Cash;

/**
 * Created by Zahm Robert on 4/3/2017.
 */
public class PrintCashStatus {
    private static final String INVENTORY_TEXT = "Inventory:";

    private CashDataAccess cashDataAccess = CashDataAccess.getInstance();
    private Output output = new Output();

    public void printStatus() {
        output.logOutput(INVENTORY_TEXT);
        for (Cash cash: cashDataAccess.getCashInventory()) {
            output.logOutput(cash.getStatus());
        }
    }
}
