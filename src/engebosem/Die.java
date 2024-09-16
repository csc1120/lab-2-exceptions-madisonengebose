/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Madison Engebose
 * Created: 9/12/2024
 */
package engebosem;
import java.util.Random;
/**
 * This class creates the Die object
 * Contains the constructor method, a method that will
 * return what value on the die is face up, and a method
 * that will roll the die and randomly generate a number.
 */
public class Die {
    /**
     * The minimum amount of sides a die could have
     */
    public static final int MINSIDES = 2;
    /**
     * The maximum amount of sides a die could have
     */
    public static final int MAXSIDES = 100;
    private static int currentValue;
    private static int numSides;
    private static final Random RANDOM = new Random();

    /**
     * This is the constructor for the Die object.
     * It creates and initializes the values for the object.
     * @param numSides The number of sides the dice has
     * @throws IllegalArgumentException throws an exception if the number of sides the
     * user chose is not within the specified bounds of 2 and 100.
     */
    public Die(int numSides) throws IllegalArgumentException{
        if(numSides<MINSIDES || numSides > MAXSIDES) {
            throw new IllegalArgumentException();
        }
        this.numSides = numSides;
    }

    /**
     * Gets the current value on the top of the dice after it has been rolled.
     * @return The number on the the die after being rolled
     * @throws DieNotRolledException throws this exception if the die has not been rolled yet.
     */
    public static int getCurrentValue() throws DieNotRolledException{
        if (currentValue <1) {
            throw new DieNotRolledException();
        }
        int value = currentValue;
        currentValue = 0; // Reset the value on the die to 0
        return value; // return value;
    }
    /**
     * Rolls the die using a random number generator.
     */
    public static void roll(){
        currentValue = RANDOM.nextInt(numSides) + 1;
    }
}