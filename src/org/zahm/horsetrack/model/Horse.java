package org.zahm.horsetrack.model;

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

    // Returns a string representation of the status
    public String getStatus(Horse winningHorse) {
        String winnerText = LOST;
        if (this.equals(winningHorse))
            winnerText = WON;

        return String.format("%d,%s,%d,%s",
                getNumber(), getName(), getOdds(), winnerText);
    }

    // Calculate the payout should this horse win
    public int calculatePayout(int betAmount) {
        return this.getOdds() * betAmount;
    }

    // Use the horse number for equals, and set it as the hashcode value

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Horse))
            return false;
        if (obj == this)
            return true;
        return this.number == (((Horse)obj).number);
    }

    @Override
    public int hashCode() {
        return this.number;
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
