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
 * This class creates the Die object, and has a method that will
 * return what value on the die is face up, and a method
 * that will roll the die and randomly generate a number.
 */
public class Die {
    //The minimum amount of sides a die could have
    public static final int MINSIDES = 2;
    //The maximum amount of sides the die could have
    public static final int MAXSIDES = 100;
    private static int currentValue;
    private static int numSides;
    private static final Random RANDOM = new Random();

    /**
     * This class creates and initializes the values for the Die object
     * @param numSides The number of sides the dice has
     * @throws IllegalArgumentException throws an exception if the number of sides the
     * user chose is not within the specified bounds
     */
   public Die(int numSides) throws IllegalArgumentException{
    if(numSides<MINSIDES || numSides > MAXSIDES){
       throw new IllegalArgumentException();
    }
    this.numSides = numSides;
   }

   public static int getCurrentValue() throws DieNotRolledException{
       if (currentValue <= 0 || currentValue > numSides) {
           throw new DieNotRolledException();
       }
       int value = currentValue;
       currentValue = 0; // Reset currentValue to 0 after getting it
       return value;

   }

   public static void roll(){
      currentValue = RANDOM.nextInt(numSides) + 1;
   }
}