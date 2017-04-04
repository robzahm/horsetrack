package org.zahm.horsetrack.service;

import org.zahm.horsetrack.data.CashDataAccess;

/**
 * Created by Zahm Robert on 4/3/2017.
 */
public class CashRestockService {
    // Could be injectable
    private CashDataAccess cashDataAccess = CashDataAccess.getInstance();

    public void restock() {
        cashDataAccess.restock();
    }
}
