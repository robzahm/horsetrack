package org.zahm.horsetrack.manager;

import org.zahm.horsetrack.model.Horse;

import java.util.ArrayList;

/**
 * Created by Zahm Robert on 4/1/2017.
 */
public class HorseManager {
    public static final String HORSES_TEXT = "Horses:";

    private ArrayList<Horse> horses = new ArrayList<Horse>();

    public HorseManager() {
        horses.add(new Horse(1, "That Darn Gray Cat", 5, true));
        horses.add(new Horse(2, "Fort Utopia", 10, false));
        horses.add(new Horse(3, "Count Sheep", 9, false));
        horses.add(new Horse(4, "Ms Traitour", 5, false));
        horses.add(new Horse(5, "Real Princess", 3, false));
        horses.add(new Horse(6, "Pa Kettle", 5, false));
        horses.add(new Horse(7, "Gin Stinger", 6, false));
    }

    // !!!!! MAKE THE MANAGERS AN ABSTRACT CLASS
    public void printStatus() {
        System.out.println(HORSES_TEXT);
        for (Horse horse: horses) {
            horse.printStatus();
        }
    }
}
