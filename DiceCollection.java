import java.util.Arrays;

/**
 * This class represents a collection of dice, each with a specified number of sides. The class includes methods to
 * manipulate and analyze the collection of dice.
 *
 * @author Jasmine Montrichard
 * @version 1
 */

public class DiceCollection {
    private Die[] diceArray;

    // Constructor to create a DiceCollection with an array of integers representing sides of dice
    public DiceCollection(int[] sidesArray) {
        diceArray = new Die[sidesArray.length];
        for (int i = 0; i < sidesArray.length; i++) {
            diceArray[i] = new Die(sidesArray[i]);
        }
    }

    // Gets the total sum of all the sides showing on the dice
    public int getTotalSum() {
        int sum = 0;
        for (Die die : diceArray) {
            sum += die.getCurrentSide();
        }
        return sum;
    }

    // Gets the maximum possible sum
    public int getMaximumSum() {
        int maxSum = 0;
        for (Die die : diceArray) {
            maxSum += die.getNumSides();
        }
        return maxSum;
    }

    // Gets the minimum possible sum
    public int getMinimumSum() {
        int minSum = diceArray.length; // Minimum sum is achieved when each die shows 1
        return minSum;
    }

    // Rolls all the dice at once
    public void rollAllDice() {
        for (Die die : diceArray) {
            die.roll();
        }
    }

    // Override toString method to provide a string representation of the DiceCollection
    @Override
    public String toString() {
        String result = "\nDice Collection:\n";
        for (int i = 0; i < diceArray.length; i++) {
            result += "Die " + (i+1) + ": " + diceArray[i].toString() + "\n";
        }
        result += "The minimum roll is " + getMinimumSum() + ", " + "the maximum roll is " + getMaximumSum() + ", " + "the current total is " + getTotalSum() + "\n";
        return result;
    }

    // Builds a histogram of the results
    public int[] histogram(int numRolls) {
        int maxPossibleSum = getMaximumSum();
        int[] histogram = new int[maxPossibleSum + 1];

        for (int i = 0; i < numRolls; i++) {
            rollAllDice();
            int sum = getTotalSum();
            histogram[sum]++;
        }

        return histogram;
    }
}
