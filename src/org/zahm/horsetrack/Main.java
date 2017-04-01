package org.zahm.horsetrack;

import org.zahm.horsetrack.manager.HorseManager;
import org.zahm.horsetrack.manager.InventoryManager;

import java.util.Scanner;

// !! REFACTOR THIS CLASS
public class Main {
    private static boolean isRunning = true;

    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        HorseManager horseManager = new HorseManager();

        inventoryManager.printStatus();
        horseManager.printStatus();

        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            // Run loop
            String input = scanner.nextLine();
        }
    }

    public static void quit() {
        isRunning = false;
    }
}
