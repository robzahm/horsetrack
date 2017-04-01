package org.zahm.horsetrack.io;

import org.zahm.horsetrack.exceptions.InvalidCommandException;
import org.zahm.horsetrack.Main;
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
                    throw new InvalidCommandException();

                // If the first input is "W", we are setting a winning horse
                if (SET_WINNER_COMMAND.equalsIgnoreCase(inputSplit[0])) {
                    int winningHorse = Integer.parseInt(inputSplit[1]);
                    horseManager.setWinner(winningHorse);
                }
                // Otherwise, we are checking a bet
                else {
                    int horse = Integer.parseInt(inputSplit[0]);
                    int bet = Integer.parseInt(inputSplit[1]);
                    int payout = horseManager.checkPayout(horse, bet);
                    if (payout > 0)
                        inventoryManager.payout(payout);
                }
            }


            horseManager.checkPayout(0, 0);
        }/*
        catch (InvalidBetException e) {
            System.out.println(String.format("Invalid Bet: %s", input));
        }
        catch (InvalidHorseException e) {
            System.out.println(String.format("Invalid Horse Number: %s", input));
        }*/
        catch (InvalidCommandException e) {
            System.out.println(String.format("Invalid Command: %s", input));
        }
    }
}
