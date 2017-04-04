package org.zahm.horsetrack.service;

import org.zahm.horsetrack.data.HorseDataAccess;
import org.zahm.horsetrack.io.Output;
import org.zahm.horsetrack.model.Horse;

/**
 * Created by Zahm Robert on 4/3/2017.
 */
public class PrintHorseStatusService {
    private static final String HORSES_TEXT = "Horses:";

    private HorseDataAccess horseDataAccess = HorseDataAccess.getInstance();
    private Output output = new Output();

    public void printStatus() {
        output.logOutput(HORSES_TEXT);

        Horse winningHorse = horseDataAccess.getWinningHorse();

        for (Horse horse: horseDataAccess.getHorses()) {
            output.logOutput(horse.getStatus(winningHorse));
        }
    }
}
