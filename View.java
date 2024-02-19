import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * This class serves as a user interface for interacting with simulated dice. The program allows the user to specify
 * the number of dice and the number of sides for each die, then provides options to roll the dice and view the results.
 *
 * @author Jasmine Montrichard
 * @version 1
 */

public class View {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean toExit = false; // if the user wants to exit or not
        boolean isValid = false; // if the user input is valid
        int numDice = 0; // Represents the number of dice
        int choice = 0; // Represents the option chosen by the user

        // Get the number of dice from the user
        System.out.print("How many dice? ");
        while (!isValid) {
            try {
                numDice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a whole number: ");
            } catch (InputMismatchException e) {
                System.out.print("Invalid input - not a number. Please enter a whole number: ");
            } catch (Exception e) {
                System.out.println("An error occured.");
                e.printStackTrace();
            }
            if (numDice > 0) {
                isValid = true;
            } else if (numDice < 0) {
                System.out.print("Invalid input. Please enter in a number equal to or greater than 1: ");
            }
        }
        isValid = false;
        
        // Get the number of sides for each die from the user
        int[] sidesArray = new int[numDice];
        System.out.print("Enter the number of sides of each die: ");
        for (int i = 0; i < numDice; i++) {
            try {
                sidesArray[i] = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a whole number: ");
                i--;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input - not a number. Please enter a whole number: ");
                i--;
            } catch (Exception e) {
                System.out.println("An error occured.");
                e.printStackTrace();
            }
            try {
                if (sidesArray[i] > 0) {
                    isValid = true;
                } else if (sidesArray[i] < 0) {
                    System.out.print("Invalid input. Please enter in a number equal to or greater than 1: ");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // empty
            }
        }
        isValid = false;

        // Create the DiceCollection based on user input
        DiceCollection diceCollection = new DiceCollection(sidesArray);
        
        System.out.println(diceCollection.toString());

        // Menu loop
        while (!toExit) {
            System.out.print("Enter 1 to roll once, 2 to roll 100,000 times, or 3 to quit: ");
            while (!isValid) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a whole number.");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input - not a number. Please enter a whole number.");
                } catch (Exception e) {
                    System.out.println("An error occured.");
                    e.printStackTrace();
                }
                if (choice >= 1 && choice <= 3) {
                    isValid = true;
                } else if (choice < 0 || choice > 3) {
                    System.out.println("Invalid input. Please choose a number from 1-3.");
                }
            }
            isValid = false;

            switch (choice) {
                case 1:
                    // Roll once and show the result
                    diceCollection.rollAllDice();
                    System.out.println(diceCollection.toString());
                    break;
                case 2:
                    // Roll 100,000 times and show the histogram
                    int numRolls = 100000;
                    int[] resultHistogram = diceCollection.histogram(numRolls);
                    printHistogram(resultHistogram, diceCollection);
                    break;
                case 3:
                    // Exit the program
                    toExit = true;
                    System.out.println("\nBYE!!!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    // Prints the histogram
    public static void printHistogram(int[] histogram, DiceCollection c) {
        System.out.println();
        for (int i = c.getMinimumSum(); i < c.getMaximumSum()+1; i++) {
            System.out.print(i + ": " + histogram[i] + " ");
            // calculates the num of asterisks to print, each representing 500
            int numOfAsterisks = histogram[i] / 500;
            for (int j = 0; j < numOfAsterisks; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("\n   * represents 500\n");
    }
}