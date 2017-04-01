package org.zahm.horsetrack.manager;

import org.zahm.horsetrack.exceptions.InvalidHorseException;
import org.zahm.horsetrack.model.Horse;

import java.util.ArrayList;

/**
 * Created by Zahm Robert on 4/1/2017.
 */
public class HorseManager {
    public static final String HORSES_TEXT = "Horses:";

    // Keep as list?  We want a fast lookup to set the winner, and an ordered list to print
    // Will assume no duplicate horse numbers
    private ArrayList<Horse> horses = new ArrayList<Horse>();

    // Keep a reference to the winning horse
    private Horse winningHorse;

    public HorseManager() {
        // Create the default winning horse
        winningHorse = new Horse(1, "That Darn Gray Cat", 5, true);

        // Add the horses to the list
        horses.add(winningHorse);
        horses.add(new Horse(2, "Fort Utopia", 10, false));
        horses.add(new Horse(3, "Count Sheep", 9, false));
        horses.add(new Horse(4, "Ms Traitour", 5, false));
        horses.add(new Horse(5, "Real Princess", 3, false));
        horses.add(new Horse(6, "Pa Kettle", 5, false));
        horses.add(new Horse(7, "Gin Stinger", 6, false));
    }

    public void setWinner (int winner){
        winningHorse = horses.get(winner);
    }

    public int checkPayout(int horseNumber, int amountOfBet) {
        int winnings = 0;
        if (winningHorse.getNumber() == horseNumber) {
            winnings = winningHorse.calculatePayout(amountOfBet);
            System.out.println(String.format("Payout: %s,%d", winningHorse.getName(), winnings));
        } else {
            System.out.println(String.format("No Payout: %s", winningHorse.getName()));
        }

        return winnings;
    }

    // !!!!! MAKE THE MANAGERS AN ABSTRACT CLASS?
    public void printStatus() {
        System.out.println(HORSES_TEXT);
        for (Horse horse: horses) {
            horse.printStatus();
        }
    }
}
