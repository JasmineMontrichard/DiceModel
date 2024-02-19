import java.util.Random;

/**
 * The Die class represents a sided die (depending on what the user specifies) and provides methods for rolling the die,
 * retrieving its current side, and obtaining information on its number of sides.
 *
 * @author Jasmine Montrichard
 * @version 1
 */

public class Die {
    private final int numSides;
    private int currentSide;

    // Constructor to create a Die with a specified number of sides
    public Die(int numSides) {
        this.numSides = numSides;
        roll(); // Roll the die to initialize the current side
    }

    // Getter to get the number of sides of the die
    public int getNumSides() {
        return numSides;
    }

    // Getter to get the current side of the die
    public int getCurrentSide() {
        return currentSide;
    }

    // Rolls the die and generates a random side
    public void roll() {
        Random rand = new Random();
        currentSide = rand.nextInt(numSides) + 1;
    }

    // Gives a string representation of the Die
    @Override
    public String toString() {
        return "The die has " + numSides + " sides. The number " + currentSide + " is currently showing.";
    }
}
