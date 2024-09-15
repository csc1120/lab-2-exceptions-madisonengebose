/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Madison Engebose
 * Created: 9/12/2024
 */
package engebosem;

import java.util.Scanner;

public class Driver {
    static int[] newArray;
    static final int  MINDICE = 2;
    static final int MAXDICE = 10;
    static int position;

    public static void main(String[] args){
        boolean validInput = false;
        int[] userInput;
        Die[] dice = null;
        do{
            userInput = getInput();
            try{
                dice = createDice(userInput[0], userInput[1]);
                validInput = true;
            }catch(IllegalArgumentException e){
                System.out.println("Bad die creation: Illegal number of sides: " + userInput[1]);
            }
        }while(!validInput);
        int[] rollsOutput = rollDice(dice, userInput[1], userInput[2]);
        int max = findMax(rollsOutput);
        report(userInput[0], rollsOutput, max);
    }

    private static int[] getInput(){
        int[] formattedInput = new int[3];
        Scanner userInput = new Scanner(System.in);
        boolean validInput = false;
        do{
            System.out.println("Please enter the number of dice to roll," +
                    " how many sides the dice have, and how many rolls to complete, " +
                    "separating the values by a space. Example \"2 6 1000\"");
            System.out.println("Enter configuration: ");
            String[] userEntry = userInput.nextLine().split(" ");
            try{
                if(userEntry.length != 3){
                    throw new IllegalArgumentException();
                }
                for(int i = 0; i<3; i++){
                    formattedInput[i] = Integer.parseInt(userEntry[i]);
                }
                validInput = true;
            } catch (NumberFormatException e){
                System.out.println("Invalid input: All values must be whole numbers.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: Expected 3 values but only received " + formattedInput.length);
            }
        }while(!validInput);
        return formattedInput;
    }

    private static Die[] createDice(int numDice, int numSides){
        Die[] diceArray = new Die[numDice];
        for(int i = 0;i < numDice; i++) {
            diceArray[i] = new Die(numSides);
        }
        return diceArray;
    }

    private static int[] rollDice(Die[] dice, int numSides, int numRolls){
        int maxRollValue = dice.length * numSides;
        int[] rollAmount = new int[maxRollValue - dice.length + 1];

        for(int i = 0; i < numRolls; i++){
            int diceTotal = 0;
            for(Die die : dice){
                die.roll();
                diceTotal += die.getCurrentValue();
            }
            if (diceTotal - dice.length < rollAmount.length) {
                rollAmount[diceTotal - dice.length]++;
            }
        }
        return rollAmount;
    }

    private static int findMax(int[] rolls){
       int maxRolled = 0;
       for(int roll : rolls){
           if(roll > maxRolled){
               maxRolled = roll;
           }
       }
       return maxRolled;
    }

    private static void report(int numDice, int[] rolls, int max) {
//        int scale = max / 10;
//        int MaxDigits = (int) Math.log10(max) + 1;
//        int largestValue = rolls.length + numDice;
//        int maxDigits = (int) Math.log10(largestValue) + 1;
//        int colWidth = maxDigits + 2;
//        for (int i = 0; i < rolls.length; i++) {
//            int count = rolls[i];
//            int numStars = count / scale;
//
//            StringBuilder stars = new StringBuilder();
//            for (int j = 0; j < numStars; j++) {
//                stars.append("*" );
//            }
//
//            System.out.printf("%d: %4d%s%n", i + numDice, rolls[i], stars);
        int scale = max / 10;
        int maxDigits = (int) Math.log10(max) + 1; // Width for the roll counts
        int colWidth = maxDigits-1; // Width for the column label including ": "

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < rolls.length; i++) {
            int count = rolls[i];
            int numStars = count / scale;

            // Generate the stars with an even amount of spaces before them
            String stars = " ".repeat(4) + "*".repeat(numStars);

            // Build the formatted string for the current line
            String line = String.format("%-" + colWidth + "d:%- 4d%s%n", i + numDice, count, stars);

            // Append the line to the StringBuilder
            output.append(line);
        }

        // Print the entire output
        System.out.print(output.toString());


        }
    }
