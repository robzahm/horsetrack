package org.zahm.horsetrack.exception;

/**
 * Created by Zahm Robert on 4/1/2017.
 */
public class InvalidBetException extends HorseTrackInputException {
    protected String formattedMessage = "Invalid Bet: %s";

    public InvalidBetException(String input) {
        super(input);
    }

    @Override
    public String getMessage() {
        return String.format(formattedMessage, input);
    }
}
