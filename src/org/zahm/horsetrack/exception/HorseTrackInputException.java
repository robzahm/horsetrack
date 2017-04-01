package org.zahm.horsetrack.exception;

/**
 * Created by Zahm Robert on 4/1/2017.
 */
public class HorseTrackInputException extends Exception {
    protected String input;
    protected String formattedMessage = "Unexpected HorseTrackInputException: %s";

    public HorseTrackInputException() {}

    public HorseTrackInputException(String theInput) {
        this.input = theInput;
    }

    @Override
    public String getMessage() {
        return String.format(formattedMessage, input);
    }
}
