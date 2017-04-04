package org.zahm.horsetrack.service;

import org.zahm.horsetrack.data.HorseDataAccess;
import org.zahm.horsetrack.io.Output;
import org.zahm.horsetrack.model.Horse;

/**
 * Created by Zahm Robert on 4/3/2017.
 */
public class PrintHorseStatusService {
    private static final String HORSES_TEXT = "Horses:";
    private static final String WON = "won";
    private static final String LOST = "lost";

    private static HorseDataAccess horseDataAccess = HorseDataAccess.getInstance();

    /**
     * Helper method used to log the winning/losing status message
     * @param winningHorse
     * @return
     */
    private static String printIsWinner(Horse theHorse, Horse winningHorse) {
        if (theHorse.equals(winningHorse))
            return WON;
        else
            return LOST;
    }

    /**
     * Print out the status of each horse
     */
    public static void printHorseStatus() {
        Output.logOutput(HORSES_TEXT);

        Horse winningHorse = horseDataAccess.getWinningHorse();

        for (Horse horse: horseDataAccess.getHorses()) {

            Output.logOutput(String.format("%d,%s,%d,%s",
                    horse.getNumber(), horse.getName(), horse.getOdds(), printIsWinner(horse, winningHorse)));
        }
    }
}
