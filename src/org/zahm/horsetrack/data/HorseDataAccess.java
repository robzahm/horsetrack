package org.zahm.horsetrack.data;

import org.zahm.horsetrack.exception.InvalidHorseException;
import org.zahm.horsetrack.model.Horse;

import java.util.*;

/**
 * Class to manage the state of the horses in lieu of a database
 * Singleton class for simplicity of state maintenance
 */
public class HorseDataAccess {
    private static HorseDataAccess instance = new HorseDataAccess();

    public static HorseDataAccess getInstance() {
        return HorseDataAccess.instance;
    }

    // Keep an ordered list of horses, and an index for fast lookup
    private ArrayList<Horse> horses = new ArrayList<Horse>();
    private HashMap<Integer, Integer> horseIndex = new HashMap<Integer, Integer>();

    // Keep a reference to the winning horse
    private Horse winningHorse;

    protected HorseDataAccess() {
        // Add the horses to the list, assuming sorted order
        addHorse(new Horse(1, "That Darn Gray Cat", 5));
        addHorse(new Horse(2, "Fort Utopia", 10));
        addHorse(new Horse(3, "Count Sheep", 9));
        addHorse(new Horse(4, "Ms Traitour", 5));
        addHorse(new Horse(5, "Real Princess", 3));
        addHorse(new Horse(6, "Pa Kettle", 5));
        addHorse(new Horse(7, "Gin Stinger", 6));

        // Default the winner
        winningHorse = horses.get(0);
    }

    // Add horse to the arraylist and index
    private void addHorse(Horse horse) {
        horses.add(horse);
        horseIndex.put(horse.getNumber(), horses.size()-1);
    }

    public Horse findHorseWithNumber(int horseNumber) throws InvalidHorseException {
        try {
            return horses.get(horseIndex.get(horseNumber));
        } catch (Exception e) {
            throw new InvalidHorseException(Integer.toString(horseNumber));
        }
    }

    public void setWinningHorse (Horse newWinner) {
        winningHorse = newWinner;
    }

    public Horse getWinningHorse () {
        return winningHorse;
    }

    public List<Horse> getHorses() {
        return horses;
    }
}


