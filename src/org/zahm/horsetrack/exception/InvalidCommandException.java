package org.zahm.horsetrack.exception;

/**
 * Created by Zahm Robert on 4/1/2017.
 */
public class InvalidCommandException extends HorseTrackInputException {
    protected String formattedMessage = "Invalid Command: %s";

    public InvalidCommandException(String input) {
        super(input);
    }
}
