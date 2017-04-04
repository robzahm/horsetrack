package org.zahm.horsetrack.io;

import org.zahm.horsetrack.Main;
import org.zahm.horsetrack.exception.HorseTrackInputException;
import org.zahm.horsetrack.exception.InvalidBetException;
import org.zahm.horsetrack.exception.InvalidCommandException;
import org.zahm.horsetrack.exception.InvalidHorseException;
import org.zahm.horsetrack.service.BettingService;
import org.zahm.horsetrack.service.CashService;

/**
 * Class to handle the user input
 */
public class InputProcessor {
    private static final String RESTOCK_COMMAND = "R";
    private static final String QUIT_COMMAND = "Q";
    private static final String SET_WINNER_COMMAND = "W";

    private CashService inventoryManager;
    private BettingService horseManager;

    public InputProcessor(CashService theInventoryManager , BettingService theHorseManager) {
        this.inventoryManager = theInventoryManager;
        this.horseManager = theHorseManager;
    }

    /**
     * Handle a restock
     */
    private void handleRestockCommand() {
        inventoryManager.restock();
    }

    /**
     * Handle a quit
     */
    private void handleQuitCommand() {
        Main.quit();
    }

    /**
     * Validate the input that an int horse number has been provided and call the service
     * @param winningHorseNumber
     * @throws InvalidHorseException
     */
    private void handleWinnerCommand(String winningHorseNumber) throws InvalidHorseException {
        try {
            int horseNumber = Integer.parseInt(winningHorseNumber);
            horseManager.setWinningHorseByNumber(horseNumber);
        } catch (Exception e) {
            throw new InvalidHorseException(winningHorseNumber);
        }
    }

    /**
     * Validate the input that an int horse number and bet amount have been provided and call the service
     * @param horseNumberString
     * @param betAmountString
     * @throws InvalidHorseException
     * @throws InvalidBetException
     */
    private void handleCheckBetCommand(String horseNumberString, String betAmountString) throws InvalidHorseException, InvalidBetException {
        int horseNumber, betAmount;

        try {
            horseNumber = Integer.parseInt(horseNumberString);
        } catch (Exception e) {
            throw new InvalidHorseException(horseNumberString);
        }

        try {
            betAmount = Integer.parseInt(betAmountString);

            // Validate that the bet amount is > 0
            if (betAmount < 0)
                throw new InvalidBetException(betAmountString);

        } catch (Exception e) {
            throw new InvalidBetException(betAmountString);
        }

        int payout = horseManager.calculatePayout(horseNumber, betAmount);
        if (payout > 0)
            inventoryManager.payout(payout);
    }

    /**
     * The following commands are valid (not case sensitive):
     * R : Restock
     * Q: Quit
     * W #: Set Winning Horse
     * # #: Payout
     *
     * Anything else is invalid, as are decimals
     * @param input
     */
    public void processCommand(String input) {
        try {
            // Split the input string by the whitespace
            String[] inputSplit = input.split(" ");

            // If the input is a single string, we have a restock or quit case
            if (inputSplit.length == 1)
            {
                if (RESTOCK_COMMAND.equalsIgnoreCase(input)) {
                    handleRestockCommand();
                }
                else if (QUIT_COMMAND.equalsIgnoreCase(input)) {
                    handleQuitCommand();
                }
                else {
                    throw new InvalidCommandException(input);
                }
            }
            // If the input is 2 strings, we have a "set winner" or "check bet" case
            else if (inputSplit.length == 2) {
                // Set Winner Case
                if (SET_WINNER_COMMAND.equalsIgnoreCase(inputSplit[0])) {
                    handleWinnerCommand(inputSplit[1]);
                }
                // Check Bet Case
                else {
                    handleCheckBetCommand(inputSplit[0], inputSplit[1]);
                }
            }
            // If the input is 3+ strings, we have a bad command
            else {
                throw new InvalidCommandException(input);
            }
        }
        catch (HorseTrackInputException e) {
            Output.logOutput(e.getFormattedMessage());
        }
        catch (Exception e) {
            Output.logOutput(String.format("Unexpected error: %s", e.getMessage()));
        }
    }
}
