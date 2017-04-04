package org.zahm.horsetrack.io;

/**
 * Class to log the output of the system in a central place should we need to change from System.out in the future
 */
public class Output {

    /**
     * Static method to log the output
     * Writes to standard out
     * @param message
     */
    public static void logOutput(String message) {
        System.out.println(message);
    }
}
