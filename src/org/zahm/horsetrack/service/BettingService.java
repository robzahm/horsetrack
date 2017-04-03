package org.zahm.horsetrack.service;

import org.zahm.horsetrack.exception.InvalidHorseException;
import org.zahm.horsetrack.io.Output;
import org.zahm.horsetrack.model.Horse;

import java.util.*;

/**
 * Service class for the Horses and Betting
 */
public class BettingService {
    private static final String HORSES_TEXT = "Horses:";

    // We want a fast lookup to get the horse by number, and an ordered list to print the status
    // Will assume no duplicate horse numbers
    private SortedMap<Integer, Horse> horses = new TreeMap<Integer, Horse>();

    // Keep a reference to the winning horse
    private Horse winningHorse;

    public BettingService() {
        // Add the horses to the list
        horses.put(1, new Horse(1, "That Darn Gray Cat", 5));
        horses.put(2 ,new Horse(2, "Fort Utopia", 10));
        horses.put(3, new Horse(3, "Count Sheep", 9));
        horses.put(4, new Horse(4, "Ms Traitour", 5));
        horses.put(5, new Horse(5, "Real Princess", 3));
        horses.put(6, new Horse(6, "Pa Kettle", 5));
        horses.put(7, new Horse(7, "Gin Stinger", 6));

        // Default the winner
        winningHorse = horses.get(0);
    }

    /**
     * Helper method to return the horse with the given number
     * @param horseNumber
     * @return
     * @throws InvalidHorseException
     */
    private Horse getHorseWithNumber(int horseNumber) throws InvalidHorseException {
        try {
            return horses.get(horseNumber);
        }
        catch (Exception e) {
            throw new InvalidHorseException(Integer.toString(horseNumber));
        }
    }

    /**
     * Sets the winning horse
     * @param winner
     * @throws InvalidHorseException
     */
    public void setWinner (int winner) throws InvalidHorseException {
        winningHorse = getHorseWithNumber(winner);
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
        int winnings = 0;
        if (winningHorse.getNumber() == horseNumber) {
            winnings = winningHorse.getOdds() * amountOfBet;
            Output.logOutput(String.format("Payout: %s,$%d", winningHorse.getName(), winnings));
        } else {
            Output.logOutput(String.format("No Payout: %s", getHorseWithNumber(horseNumber).getName()));
        }

        return winnings;
    }

    /**
     * Print out the status of each horse
     */
    public void printStatus() {
        Output.logOutput(HORSES_TEXT);
        for (Horse horse: horses.values()) {
            horse.printStatus(winningHorse);
        }
    }
}
