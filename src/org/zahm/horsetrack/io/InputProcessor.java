package org.zahm.horsetrack.io;

import org.zahm.horsetrack.exception.*;
import org.zahm.horsetrack.exception.InvalidBetException;
import org.zahm.horsetrack.Main;
import org.zahm.horsetrack.exception.InvalidHorseException;
import org.zahm.horsetrack.manager.HorseManager;
import org.zahm.horsetrack.manager.InventoryManager;

/**
 * Created by Zahm Robert on 4/1/2017.
 */
public class InputProcessor {
    private static final String RESTOCK_COMMAND = "R";
    private static final String QUIT_COMMAND = "Q";
    private static final String SET_WINNER_COMMAND = "W";

    private InventoryManager inventoryManager;
    private HorseManager horseManager;

    public InputProcessor(InventoryManager theInventoryManager ,HorseManager theHorseManager) {
        this.inventoryManager = theInventoryManager;
        this.horseManager = theHorseManager;
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
            // Restock Command
            if (RESTOCK_COMMAND.equalsIgnoreCase(input)) {
                inventoryManager.restock();
            }
            // Quit Command
            else if (QUIT_COMMAND.equalsIgnoreCase(input)) {
                Main.quit();
            }
            // Set Winning Number or Check Bet (or invalid command)
            else {
                // Separate the two inputs - throw an exception if this is invalid
                String[] inputSplit = input.split(" ");
                if (inputSplit.length != 2)
                    throw new InvalidCommandException(input);

                int horse, bet;

                // If the first input is "W", we are setting a winning horse
                if (SET_WINNER_COMMAND.equalsIgnoreCase(inputSplit[0])) {
                    try {
                        horse = Integer.parseInt(inputSplit[1]);
                    } catch (Exception e) {
                        throw new InvalidHorseException(inputSplit[1]);
                    }
                    horseManager.setWinner(horse);
                }
                // Otherwise, we are checking a bet
                else {
                    try {
                        horse = Integer.parseInt(inputSplit[0]);
                    } catch (Exception e) {
                        throw new InvalidHorseException(inputSplit[0]);
                    }
                    try {
                        bet = Integer.parseInt(inputSplit[1]);
                    } catch (Exception e) {
                        throw new InvalidBetException(inputSplit[1]);
                    }
                    int payout = horseManager.checkPayout(horse, bet);
                    if (payout > 0)
                        inventoryManager.payout(payout);
                }
            }
        }
        catch (HorseTrackInputException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(String.format("Unexpected error: %s", e.getMessage()));
        }
    }
}
