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
   private static Random random;
   static final int MINSIDES = 2;
   static final int MAXSIDES = 100;

   public Die(int numSides) throws IllegalArgumentException{
    if(numSides<MINSIDES | numSides > MAXSIDES){
       throw new IllegalArgumentException("Bad die creation: Illegal number of sides: " + numSides);
    }
   }

   public static int getCurrentValue() throws DieNotRolledException{
       if(currentValue == 0){
           throw DieNotRolledException();
       }else{
           return currentValue;
       }

   }

   public static void roll(){
      currentValue = random.ints(1, 1,101);
   }
}