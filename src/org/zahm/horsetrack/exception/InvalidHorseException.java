package org.zahm.horsetrack.exception;

/**
 * Created by Zahm Robert on 4/1/2017.
 */
public class InvalidHorseException extends HorseTrackInputException {
    protected String formattedMessage = "Invalid Horse Number: %s";

    public InvalidHorseException(String input) {
        super(input);
    }

    public InvalidHorseException(int horseNumber) {
        super(Integer.toString(horseNumber));
    }

    @Override
    public String getFormattedMessage() {
        return String.format(formattedMessage, input);
    }
}
