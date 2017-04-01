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

    public void processCommand(String input) {
        try {
            if (RESTOCK_COMMAND.equalsIgnoreCase(input)) {
                inventoryManager.restock();
            } else if (QUIT_COMMAND.equalsIgnoreCase(input)) {
                Main.quit();
            } else if (SET_WINNER_COMMAND.equalsIgnoreCase(input)) {

                horseManager.checkPayout(0, 0);
            }
            // else if (input can be parsed into two numbers
            else {
                throw new InvalidCommandException();
            }
        }
        catch (InvalidCommandException e) {
            System.out.println(String.format("Invalid Command: %s", input));
        }
    }
}
