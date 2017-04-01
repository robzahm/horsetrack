package org.zahm.horsetrack;

import org.zahm.horsetrack.io.InputProcessor;
import org.zahm.horsetrack.manager.HorseManager;
import org.zahm.horsetrack.manager.InventoryManager;

import java.util.Scanner;

public class Main {
    private static boolean isRunning = true;

    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        HorseManager horseManager = new HorseManager();
        InputProcessor inputProcessor = new InputProcessor(inventoryManager, horseManager);

        // Print the starting inventory status
        inventoryManager.printStatus();
        horseManager.printStatus();

        Scanner input = new Scanner(System.in);
        while (isRunning) {
            // Run loop

            // Process the input command
            String command = input.nextLine();
            inputProcessor.processCommand(command);

            // Print the latest inventory status
            // (assuming we should do so even after a quit command)
            inventoryManager.printStatus();
            horseManager.printStatus();
        }
    }

    public static void quit() {
        isRunning = false;
    }
}
