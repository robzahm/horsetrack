package org.zahm.horsetrack.manager;

import org.zahm.horsetrack.exception.InvalidHorseException;
import org.zahm.horsetrack.model.Horse;

import java.util.ArrayList;

/**
 * Created by Zahm Robert on 4/1/2017.
 */
public class HorseManager {
    public static final String HORSES_TEXT = "Horses:";

    // Keep as list?  We want a fast lookup to set the winner, and an ordered list to print
    // Will assume no duplicate horse numbers
    // Sorted Map?
    private ArrayList<Horse> horses = new ArrayList<Horse>();

    // Keep a reference to the winning horse
    private Horse winningHorse;

    // !! ASSUMES THE INDEX MATCHES THE HORSE NUMBER
    private Horse getHorseWithNumber(int horseNumber) throws InvalidHorseException {
        try {
            return horses.get(horseNumber-1);
        }
        catch (Exception e) {
            throw new InvalidHorseException(Integer.toString(horseNumber));
        }
    }

    public HorseManager() {
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


    public void setWinner (int winner) throws InvalidHorseException {
        winningHorse = getHorseWithNumber(winner);
    }

    public int checkPayout(int horseNumber, int amountOfBet){
        int winnings = 0;
        if (winningHorse.getNumber() == horseNumber) {
            winnings = winningHorse.calculatePayout(amountOfBet);
            System.out.println(String.format("Payout: %s,$%d", winningHorse.getName(), winnings));
        } else {
            System.out.println(String.format("No Payout: %s", winningHorse.getName()));
        }

        return winnings;
    }

    // !!!!! MAKE THE MANAGERS AN ABSTRACT CLASS?
    public void printStatus() {
        System.out.println(HORSES_TEXT);
        for (Horse horse: horses) {
            horse.printStatus(winningHorse);
        }
    }
}
