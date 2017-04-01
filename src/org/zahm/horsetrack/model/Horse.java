package org.zahm.horsetrack.model;

/**
 * Created by Zahm Robert on 4/1/2017.
 */
public class Horse {
    public static final String WON = "won";
    public static final String LOST = "lost";

    private int number;
    private String name;
    private int odds;

    public Horse(int number, String name, int odds) {
        this.setNumber(number);
        this.setName(name);
        this.setOdds(odds);
    }

    private String printIsWinner(Horse winningHorse) {
        if (winningHorse.getNumber() == getNumber())
            return WON;
        else
            return LOST;
    }

    // Change to print/log from a central location
    public void printStatus(Horse winningHorse) {
        System.out.println(String.format("%d,%s,%d,%s",
                getNumber(), getName(), getOdds(), printIsWinner(winningHorse)));
    }

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
