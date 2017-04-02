package org.zahm.horsetrack;

import org.zahm.horsetrack.io.InputProcessor;
import org.zahm.horsetrack.service.BettingService;
import org.zahm.horsetrack.service.CashService;

import java.util.Scanner;

/**
 * Main driver class for the application
 */
public class Main {
    private static boolean isRunning = true;

    public static void main(String[] args) {
        // Initialize the services
        CashService inventoryManager = new CashService();
        BettingService horseManager = new BettingService();
        InputProcessor inputProcessor = new InputProcessor(inventoryManager, horseManager);
        Scanner input = new Scanner(System.in);

        // Print the starting inventory status
        inventoryManager.printStatus();
        horseManager.printStatus();

        // Run loop
        while (isRunning) {
            // Process the input command
            String command = input.nextLine();
            inputProcessor.processCommand(command);

            // Print the latest inventory status
            // (assuming we should do so even after a quit command)
            inventoryManager.printStatus();
            horseManager.printStatus();
        }
    }

    /**
     * Quits the application
     */
    public static void quit() {
        isRunning = false;
    }
}
