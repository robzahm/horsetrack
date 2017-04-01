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
    private boolean isWinner;

    public Horse(int number, String name, int odds, boolean isWinner) {
        this.setNumber(number);
        this.setName(name);
        this.setOdds(odds);
        this.setWinner(isWinner);
    }

    // Change to print/log from a central location
    public void printStatus() {
        System.out.println(String.format("%d,%s,%d,%s",
                getNumber(), getName(), getOdds(), printIsWinner()));
    }

    public int calculateWinnings(int amountOfBet) {
        return getOdds() * amountOfBet;
    }

    public String printIsWinner() {
        if (isWinner)
            return WON;
        else
            return LOST;
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

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }
}
