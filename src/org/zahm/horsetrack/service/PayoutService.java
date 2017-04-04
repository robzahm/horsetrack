package org.zahm.horsetrack.service;

import org.zahm.horsetrack.data.CashDataAccess;
import org.zahm.horsetrack.data.HorseDataAccess;
import org.zahm.horsetrack.exception.InvalidHorseException;
import org.zahm.horsetrack.io.Output;
import org.zahm.horsetrack.model.Cash;
import org.zahm.horsetrack.model.Horse;

import java.util.HashMap;

/**
 * Created by Zahm Robert on 4/3/2017.
 */
public class PayoutService {
    private CashDataAccess cashDataAccess = CashDataAccess.getInstance();
    private HorseDataAccess horseDataAccess = HorseDataAccess.getInstance();
    private Output output = new Output();

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
    private int calculatePayout(int horseNumber, int amountOfBet) throws InvalidHorseException {
        Horse winningHorse = horseDataAccess.getWinningHorse();
        int winnings = 0;

        if (winningHorse.getNumber() == horseNumber) {
            winnings = winningHorse.calculatePayout(amountOfBet);
            output.logOutput(String.format("Payout: %s,$%d", winningHorse.getName(), winnings));
        } else {
            output.logOutput(String.format("No Payout: %s", horseDataAccess.findHorseWithNumber(horseNumber).getName()));
        }

        return winnings;
    }

    /**
     * Performs the payout if the machine can service the request, otherwise an insufficient funds
     * message will be logged
     *
     * Work backwards through the list using the largest bills possible to service the request in order
     * to pay out using the fewest number of bills possible
     *
     * Will need to keep track of the dispensed bills so that we can commit or roll back if it turns out that we don't have
     * the right mix of bills to service the request (method synchronized to prevent concurrent payouts that could lead
     * to an inconsistent state)
     */
    public synchronized void payout(int horseNumber, int amountOfBet) throws InvalidHorseException {
        // Calculate the payout, and return if there is none
        int payoutAmount = calculatePayout(horseNumber, amountOfBet);
        if (payoutAmount == 0)
            return;

        // Variable to track the remaining payout as we put together the bills to dispense
        int remainingPayout = payoutAmount;

        // List to track the number of dispensed bills
        HashMap<Cash, Integer> billsToDispense = new HashMap<Cash, Integer>();

        for (int i = cashDataAccess.getCashInventory().size()-1; i >= 0; i--) {
            Cash cash = cashDataAccess.getCashInventory().get(i);

            // Find the number of bills of this denomination to dispense, and note it in the map
            int numBillsToDispense = cash.calculateNumBillsToDispense(remainingPayout);
            billsToDispense.put(cash, numBillsToDispense);

            // Calculate the amount that will be dispensed, and decrease the remaining amount by this value
            int amountToDispense = numBillsToDispense * cash.getDenomination();
            remainingPayout -= amountToDispense;

            if (remainingPayout == 0)
                break;
        }

        // If we've gotten to the end and the payout amount is 0, "commit" the transaction, dispense the bills,
        // and log the message to the console
        if (remainingPayout == 0) {
            output.logOutput("Dispensing:");
            for (Cash cash:cashDataAccess.getCashInventory()) {
                int numBillsToDispense = 0;
                if (billsToDispense.containsKey(cash))
                    numBillsToDispense =  billsToDispense.get(cash);

                cash.dispenseBills(numBillsToDispense);
                output.logOutput(String.format("$%d,%d", cash.getDenomination(), numBillsToDispense));
            }
        }
        // If the remaining amount to be paid out is not 0, we didn't have the right mix of bills
        // to service this request
        else {
            output.logOutput(String.format("Insufficient Funds: %d", payoutAmount));
        }
    }
}
