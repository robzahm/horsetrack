package org.zahm.horsetrack;

import org.zahm.horsetrack.io.InputProcessor;

import java.util.Scanner;

/**
 * Main driver class for the application
 */
public class Main {
    private static boolean isRunning = true;

    public static void main(String[] args) {
        // Initialize the services
        InputProcessor inputProcessor = new InputProcessor();
        Scanner input = new Scanner(System.in);

        // Run loop
        while (isRunning) {
            // Process the input command
            String command = input.nextLine();
            inputProcessor.processCommand(command);
        }
    }

    /**
     * Quits the application
     */
    public static void quit() {
        isRunning = false;
    }
}
