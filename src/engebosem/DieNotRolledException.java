/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Madison Engebose
 * Created: 9/12/2024
 */
package engebosem;

public class DieNotRolledException extends RuntimeException {
    public String getMessage(){
        return "Dice needs to be rolled first.";
    }
}
