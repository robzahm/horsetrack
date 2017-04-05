package org.zahm.horsetrack.io;

import org.zahm.horsetrack.Main;
import org.zahm.horsetrack.exception.InvalidBetException;
import org.zahm.horsetrack.exception.InvalidCommandException;
import org.zahm.horsetrack.exception.InvalidHorseException;
import org.zahm.horsetrack.service.*;

/**
 * Class to handle and validate the user input
 */
public class InputProcessor {
    private static final String RESTOCK_COMMAND = "R";
    private static final String QUIT_COMMAND = "Q";
    private static final String SET_WINNER_COMMAND = "W";

    // Dependent services (could be injected with a DI framework)
    private CashRestockService cashRestockService = new CashRestockService();
    private PayoutService payoutServiceService = new PayoutService();
    private PrintCashStatus printCashStatusService = new PrintCashStatus();
    private PrintHorseStatusService printHorseStatusService = new PrintHorseStatusService();
    private SetWinnerService setWinnerService = new SetWinnerService();
    private Output output = new Output();


    public InputProcessor() {
        // Print status on startup
        printStatus();
    }

    private void printStatus() {
        printCashStatusService.printStatus();
        printHorseStatusService.printStatus();
    }

    private int parseInt(String input) throws InvalidCommandException{
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new InvalidCommandException();
        }
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
        int horseNumber = 0;
        int betAmount = 0;

        try {
            // If a blank line was entered, return as we should ignore these commands (and assume we should not print
            // the status)
            if (input.trim().equals(""))
                return;

            // Split the input string by the whitespace
            String[] inputSplit = input.split(" ");

            switch (inputSplit.length) {
                case 1:
                    // Restock
                    if (RESTOCK_COMMAND.equalsIgnoreCase(input)) {
                    cashRestockService.restock();
                    }
                    // Quit
                    else if (QUIT_COMMAND.equalsIgnoreCase(input)) {
                        Main.quit();
                    }
                    else {
                        throw new InvalidCommandException();
                    }
                    break;

                case 2:
                    // Set Winner Case
                    if (SET_WINNER_COMMAND.equalsIgnoreCase(inputSplit[0])) {
                        horseNumber = parseInt(inputSplit[1]);
                        setWinnerService.setWinningHorseByNumber(horseNumber);
                    }
                    // Check Bet Case
                    else {
                        horseNumber = parseInt(inputSplit[0]);
                        betAmount = parseInt(inputSplit[1]);

                        // Validate that the bet amount is >= 0 (assume we should win but pay out $0)
                        if (betAmount < 0)
                            throw new InvalidBetException();

                        payoutServiceService.payout(horseNumber, betAmount);
                    }
                    break;

                default:
                    throw new InvalidCommandException();
            }

            // Print the status after processing each successful command
            printStatus();
        }
        catch (InvalidCommandException e) {
            output.logOutput(String.format("Invalid Command: %s", input));
        }
        catch (InvalidHorseException e) {
            output.logOutput(String.format("Invalid Horse Number: %d", horseNumber));
        }
        catch (InvalidBetException e) {
            output.logOutput(String.format("Invalid Bet: %d", betAmount));
        }
        catch (Exception e) {
            output.logOutput(String.format("Unexpected error: %s", e.getMessage()));
        }
    }
}
