package org.zahm.horsetrack;

import org.zahm.horsetrack.manager.InventoryManager;

public class Main {

    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.printStatus();
    }
}
