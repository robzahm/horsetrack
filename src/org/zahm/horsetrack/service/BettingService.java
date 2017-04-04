package org.zahm.horsetrack.service;

import org.zahm.horsetrack.data.HorseDataAccess;
import org.zahm.horsetrack.exception.InvalidHorseException;
import org.zahm.horsetrack.io.Output;
import org.zahm.horsetrack.model.Horse;

import java.util.*;

/**
 * Business logic class for the Horses and Betting
 * (each method could be separated into individual classes for further separation of concerns)
 */
public class BettingService {
    private static final String HORSES_TEXT = "Horses:";
    private static final String WON = "won";
    private static final String LOST = "lost";

    public BettingService() {

    }

    /**
     * Helper method used to log the winning/losing status message
     * @param winningHorse
     * @return
     */
    private String printIsWinner(Horse theHorse, Horse winningHorse) {
        if (theHorse.equals(winningHorse))
            return WON;
        else
            return LOST;
    }

    /**
     * Returns the payout of a horse if it is the winning horse (else 0)
     *
     * Assuming that we would have a practical limit on the allowable bet/odds and do not need to consider
     * a case where this overflows the int value
     * @param horseNumber
     * @param amountOfBet
     * @return
     * @throws InvalidHorseException
     */
    public int calculatePayout(int horseNumber, int amountOfBet) throws InvalidHorseException {
        Horse winningHorse = HorseDataAccess.getInstance().getWinningHorse();
        int winnings = 0;

        if (winningHorse.getNumber() == horseNumber) {
            winnings = winningHorse.getOdds() * amountOfBet;
            Output.logOutput(String.format("Payout: %s,$%d", winningHorse.getName(), winnings));
        } else {
            Output.logOutput(String.format("No Payout: %s", HorseDataAccess.getInstance().findHorseWithNumber(horseNumber).getName()));
        }

        return winnings;
    }

    /**
     * Sets the winning horse to the horse with the passed-in number, and throws an Exception if it
     * cannot be found
     * @param winningHorse
     * @throws InvalidHorseException
     */
    public void setWinningHorseByNumber(int winningHorse) throws InvalidHorseException {
        Horse newWinner = HorseDataAccess.getInstance().findHorseWithNumber(winningHorse);
        HorseDataAccess.getInstance().setWinningHorse(newWinner);
    }

    /**
     * Print out the status of each horse
     */
    public void printStatus() {
        Output.logOutput(HORSES_TEXT);

        Horse winningHorse = HorseDataAccess.getInstance().getWinningHorse();

        for (Horse horse: HorseDataAccess.getInstance().getHorses()) {

            Output.logOutput(String.format("%d,%s,%d,%s",
                    horse.getNumber(), horse.getName(), horse.getOdds(), printIsWinner(horse, winningHorse)));
        }
    }
}
