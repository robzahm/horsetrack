package org.zahm.horsetrack.service;

import org.zahm.horsetrack.data.CashDataAccess;

/**
 * Created by Zahm Robert on 4/3/2017.
 */
public class CashRestockService {
    private CashDataAccess cashDataAccess  = CashDataAccess.getInstance();

    /**
     * Restocks the cash inventory
     */
    public void restock() {
        cashDataAccess.restock();
    }
}
