/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Madison Engebose
 * Created: 9/12/2024
 */
package engebosem;

import java.util.Random;
import java.util.random.RandomGenerator;

public class Die{
   private static int currentValue;
   private static int numSides;
   private static final Random random = new Random();
   public static final int MINSIDES = 2;
   public static final int MAXSIDES = 100;


   public Die(int numSides) throws IllegalArgumentException{
    if(numSides<MINSIDES || numSides > MAXSIDES){
       throw new IllegalArgumentException();
    }
    this.numSides = numSides;
   }

   public static int getCurrentValue() throws DieNotRolledException{
       if(currentValue < MINSIDES || currentValue > MAXSIDES){
           throw new DieNotRolledException();
       }
       int current = currentValue;
       currentValue = 0;
       return current;

   }

   public static void roll(){
      currentValue = random.nextInt(numSides - MINSIDES + 1) + MINSIDES;
   }
}