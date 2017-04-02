package org.zahm.horsetrack.model;

import org.zahm.horsetrack.io.Output;

/**
 * Domain object that stores the properties of the Horse
 */
public class Horse {
    private static final String WON = "won";
    private static final String LOST = "lost";

    private int number;
    private String name;
    private int odds;

    public Horse(int number, String name, int odds) {
        this.setNumber(number);
        this.setName(name);
        this.setOdds(odds);
    }

    /**
     * Helper method used to log the winning/losing status message
     * @param winningHorse
     * @return
     */
    private String printIsWinner(Horse winningHorse) {
        if (winningHorse.getNumber() == getNumber())
            return WON;
        else
            return LOST;
    }

    /**
     * Logs the status of this horse, including whether or not it is the winner
     * @param winningHorse
     */
    public void printStatus(Horse winningHorse) {
        Output.logOutput(String.format("%d,%s,%d,%s",
                getNumber(), getName(), getOdds(), printIsWinner(winningHorse)));
    }

    /**
     * Returns the payout if this horse wins
     * @param amountOfBet
     * @return
     */
    public int calculatePayout(int amountOfBet) {
        return getOdds() * amountOfBet;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOdds() {
        return odds;
    }

    public void setOdds(int odds) {
        this.odds = odds;
    }
}
