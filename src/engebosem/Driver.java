/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Madison Engebose
 * Created: 9/12/2024
 */
package engebosem;

import java.util.Scanner;

/**
 * This class asks the user for input regarding the specifications of the dice they want.
 * It then runs methods that will ensure the user's input is valid, roll the dice,
 * count up the results of the dice rolls, the format and print out the totals of the rolls.
 */
public class Driver {
    /**
     * Values of the minimum and maximum amount of dice a user can select
     */
    static final int MINDICE = 2;
    static final int MAXDICE = 10;

    /**
     * Main method that asks the user for their input regarding the dice creation.
     * This method calls the other methods in the class to verify the user's input,
     * create the dice, roll the dice, find the number on the dice that was rolled the most,
     * and print out a formatted output.
     * @param args Not used
     */
    public static void main(String[] args) {
        int[] userInput;
        Die[] dice = null;
        boolean validInput = false;
        //Calls the method to get the input from the user
        do {
            userInput = getInput();
            //checks to make sure the values the user gave are valid
            try {
                dice = createDice(userInput[0], userInput[1]);
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Bad die creation: Illegal number of sides: " + userInput[1]);
            }
            //checks to make sure the number of dice the user entered is a valid amount
            try {
                if (userInput[0] < MINDICE || userInput[0] > MAXDICE) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid number of dice: " + userInput[0] +
                        ". Number of dice must be between "
                        + MINDICE + " and " + MAXDICE);
                validInput = false;
            }
        } while (!validInput);
        //Creates an array of the values rolled from the dice
        int[] rollsOutput = rollDice(dice, userInput[1], userInput[2]);
        //Stores the maximum amount of times a number on the dice was rolled.
        int max = findMax(rollsOutput);
        //Displays a formatted output to the user
        report(userInput[0], rollsOutput, max);
    }

    /**
     * This method informs the user of what inputs are needed and stores the entered values.
     * @return an integer array of the values the user inputted.
     */
    private static int[] getInput() {
        int[] formattedInput = new int[3];
        //Creates the scanner to read the user input
        Scanner userInput = new Scanner(System.in);
        boolean validInput = false;
        do {
            //Informs the user of what information is needed
            System.out.println("Please enter the number of dice to roll," +
                    " how many sides the dice have, and how many rolls to complete, " +
                    "separating the values by a space. Example \"2 6 1000\"");
            System.out.println("Enter configuration: ");
            //Reads, separates, and stores the user's inputs
            String[] userEntry = userInput.nextLine().split(" ");
            try {
                //Checks to see if the user entered all the information needed
                if (userEntry.length != 3) {
                    throw new IllegalArgumentException();
                }
                //Takes the user's input and turns it into an integer array instead of a string
                for (int i = 0; i < 3; i++) {
                    formattedInput[i] = Integer.parseInt(userEntry[i]);
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: All values must be whole numbers.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: Expected 3 values but only received "
                        + formattedInput.length);
            }
        } while (!validInput);
        return formattedInput;
    }

    /**
     * This method creates a Die Object using the user's inputted values
     * @param numDice the number of dice the user chose to create
     * @param numSides the number of sides that each dice has
     * @return An array of Die objects.
     */
    private static Die[] createDice(int numDice, int numSides) {
        Die[] diceArray = new Die[numDice];
        for (int i = 0; i < numDice; i++) {
            diceArray[i] = new Die(numSides);
        }
        return diceArray;
    }

    /**
     * This method rolls the dice, and randomizes the values "rolled".
     * It then stores the results in an array.
     * @param dice The array containing the die objects
     * @param numSides The number of sides that each dice has
     * @param numRolls The number of rolls the user chose
     * @return An integer array of the results from rolling the dice.
     */
    private static int[] rollDice(Die[] dice, int numSides, int numRolls) {
        int maxRollValue = dice.length * numSides;
        int[] rollAmount = new int[maxRollValue - dice.length + 1];
        //If the user chose to roll the dice 0 times
        if (numRolls == 0) {
            throw new DieNotRolledException();
        }
        //Rolls each dice the number of times that the user chose,
        //and adds each result to the array
        for (int i = 0; i < numRolls; i++) {
            int diceTotal = 0;
            for (Die die : dice) {
                die.roll();
                diceTotal += die.getCurrentValue();
            }
            rollAmount[diceTotal - dice.length]++;
        }
        return rollAmount;
    }

    /**
     *This method goes through the array results from rolling the dice,
     * finds the number that was rolled the most, and returns the amount of times it was rolled.
     * @param rolls The array containing the results from rolling the dice
     * @return The integer value of the maximum amount that one number on the die was rolled
     */
    private static int findMax(int[] rolls) {
        int maxRolled = 0;
        for (int roll : rolls) {
            if (roll > maxRolled) {
                maxRolled = roll;
            }
        }
        return maxRolled;
    }

    /**
     *This method takes the results from rolling the dice, formats the data,
     * and prints it out in a user-friendly way
     * @param numDice The number of dice the user chose to create
     * @param rolls The results from rolling the dice
     * @param max The largest amount of times that one number was rolled
     */
    private static void report(int numDice, int[] rolls, int max) {
        final int scaleRatio = 10;
        int highestValueDigits;
        int maxRolledDigits;
        int rollDigit;
        int dieNumberDigits;
        String valueSpace;
        String dieDigitSpace;
        int minSpaces = 4;
        int scale = max / scaleRatio;
        if (scale == 0) {
            scale = 1;
        }
        //The amount of digits in the largest value
        highestValueDigits = (int) Math.log10(rolls.length + numDice) + 1;

        //The amount of digits in the maximum count
        maxRolledDigits = (int) Math.log10(max) + 1;

        //Formats the stars based on the scale value and
        //the number of times a value was rolled
        for (int i = 0; i < rolls.length; i++) {
            int numStars = rolls[i] / scale;
            //If the value was rolled fewer times than the scale value
            if (rolls[i] != 0) {
                rollDigit = (int) Math.log10(rolls[i]) + 1;
            } else{
                rollDigit = 1;
            }
            //The number of digits the die value takes up
            dieNumberDigits = (int) Math.log10(i + numDice) + 1;
            //Creates the spaces for the die values
            dieDigitSpace = " ".repeat(highestValueDigits - dieNumberDigits);
            //Creates the spaces within the roll values
            valueSpace = " ".repeat(Math.max(maxRolledDigits +
                    minSpaces - rollDigit + 1, minSpaces));
            //Creates the stars
            StringBuilder stars = new StringBuilder();
            for (int k = 0; k < numStars; k++) {
                stars.append("*");
            }
            //prints out the results
            System.out.printf("%d%s: %d%s%s%n", i + numDice, dieDigitSpace, rolls[i],
                    valueSpace, stars);
        }
    }
}
