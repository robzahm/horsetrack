package org.zahm.horsetrack.service;

import org.zahm.horsetrack.data.HorseDataAccess;
import org.zahm.horsetrack.exception.InvalidHorseException;
import org.zahm.horsetrack.model.Horse;

/**
 * Created by Zahm Robert on 4/3/2017.
 */
public class SetWinnerService {
    private HorseDataAccess horseDataAccess = HorseDataAccess.getInstance();

    /**
     * Sets the winning horse to the horse with the passed-in number, and throws an Exception if it
     * cannot be found
     * @param winningHorse
     * @throws InvalidHorseException
     */
    public void setWinningHorseByNumber(int winningHorse) throws InvalidHorseException {
        Horse newWinner = horseDataAccess.findHorseWithNumber(winningHorse);
        if (newWinner == null) {
            throw new InvalidHorseException(winningHorse);
        }

        HorseDataAccess.getInstance().setWinningHorse(newWinner);
    }
}
