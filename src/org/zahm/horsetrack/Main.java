package org.zahm.horsetrack;

import org.zahm.horsetrack.io.InputProcessor;
import org.zahm.horsetrack.manager.HorseManager;
import org.zahm.horsetrack.manager.InventoryManager;

import java.util.Scanner;

// !! REFACTOR THIS CLASS
public class Main {
    private static boolean isRunning = true;

    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        HorseManager horseManager = new HorseManager();
        InputProcessor inputProcessor = new InputProcessor(inventoryManager, horseManager);

        Scanner input = new Scanner(System.in);
        while (isRunning) {
            // Run loop

            // Print the latest inventory status
            inventoryManager.printStatus();
            horseManager.printStatus();

            // Process the input command
            String command = input.nextLine();
            inputProcessor.processCommand(command);
        }
    }

    public static void quit() {
        isRunning = false;
    }
}
