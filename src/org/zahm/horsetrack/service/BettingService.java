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

    // Keep as list?  We want a fast lookup to set the winner, and an ordered list to print
    // Will assume no duplicate horse numbers
    // Sorted Map?
    private ArrayList<Horse> horses = new ArrayList<Horse>();

    // Keep a reference to the winning horse
    private Horse winningHorse;

    public BettingService() {
        // Add the horses to the list
        horses.add(new Horse(1, "That Darn Gray Cat", 5));
        horses.add(new Horse(2, "Fort Utopia", 10));
        horses.add(new Horse(3, "Count Sheep", 9));
        horses.add(new Horse(4, "Ms Traitour", 5));
        horses.add(new Horse(5, "Real Princess", 3));
        horses.add(new Horse(6, "Pa Kettle", 5));
        horses.add(new Horse(7, "Gin Stinger", 6));

        // Default the winner
        winningHorse = horses.get(0);
    }

    /**
     * Helper method to return the horse with the given number
     * !! ASSUMES THE INDEX MATCHES THE HORSE NUMBER
     * @param horseNumber
     * @return
     * @throws InvalidHorseException
     */
    private Horse getHorseWithNumber(int horseNumber) throws InvalidHorseException {
        try {
            return horses.get(horseNumber-1);
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
        for (Horse horse: horses) {
            horse.printStatus(winningHorse);
        }
    }
}
