/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Madison Engebose
 * Created: 9/12/2024
 */
package engebosem;

/**
 * This is the exception class for when the user tries to get the values
 * of a specific dice that has yet to be rolled.
 */
public class DieNotRolledException extends RuntimeException {
    /**
     * This method prints out a message to the user, telling them what the problem is.
     * @return A String telling the user what is wrong.
     */
    public String getMessage(){
        return "Dice needs to be rolled first.";
    }
}
