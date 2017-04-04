package org.zahm.horsetrack.data;

import org.zahm.horsetrack.exception.InvalidHorseException;
import org.zahm.horsetrack.model.Horse;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Class to manage the state of the horses in lieu of a database
 * Singleton class for simplicity
 */
public class HorseDataAccess {
    private static HorseDataAccess instance = new HorseDataAccess();

    // We want a fast lookup to get the horse by number, and an ordered list to print the status
    // Will assume no duplicate horse numbers
    private SortedMap<Integer, Horse> horses = new TreeMap<Integer, Horse>();

    // Keep a reference to the winning horse
    private Horse winningHorse;

    protected HorseDataAccess() {
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

    public static HorseDataAccess getInstance() {
        return HorseDataAccess.instance;
    }

    /**
     * Method to return the horse with the given number
     * @param horseNumber
     * @return
     * @throws InvalidHorseException
     */
    public Horse findHorseWithNumber(int horseNumber) throws InvalidHorseException {
        return horses.get(horseNumber);
    }

    /**
     * Sets the winning horse
     * @param newWinner
     * @throws InvalidHorseException
     */
    public void setWinningHorse (Horse newWinner) {
        winningHorse = newWinner;
    }

    /**
     * Returns the winning horse
     * @throws InvalidHorseException
     */
    public Horse getWinningHorse () {
        return winningHorse;
    }

    /**
     * Returns a collection of horses
     * @return
     */
    public Collection<Horse> getHorses() {
        return horses.values();
    }
}
