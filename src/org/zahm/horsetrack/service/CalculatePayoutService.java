package org.zahm.horsetrack.service;

import org.zahm.horsetrack.data.HorseDataAccess;
import org.zahm.horsetrack.exception.InvalidHorseException;
import org.zahm.horsetrack.io.Output;
import org.zahm.horsetrack.model.Horse;

/**
 * Created by Zahm Robert on 4/3/2017.
 */
public class CalculatePayoutService {
    private static HorseDataAccess horseDataAccess = HorseDataAccess.getInstance();

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
    public static int calculatePayout(int horseNumber, int amountOfBet) throws InvalidHorseException {
        Horse winningHorse = horseDataAccess.getWinningHorse();
        int winnings = 0;

        if (winningHorse.getNumber() == horseNumber) {
            winnings = winningHorse.calculatePayout(amountOfBet);
            Output.logOutput(String.format("Payout: %s,$%d", winningHorse.getName(), winnings));
        } else {
            Output.logOutput(String.format("No Payout: %s", horseDataAccess.findHorseWithNumber(horseNumber).getName()));
        }

        return winnings;
    }
}
