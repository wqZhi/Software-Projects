//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Palindrome
// Course: CS 300 Fall 2020
//
// Author: Weiqian Zhi
// Email: wzhi3@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////


/**
 * This class is about a recursive versions of palindrome.
 *
 */
public class Palindrome {

    /**
     * Recursively create a simple alphabet pattern, starting at the provided character, moving
     * backward to the beginning of the alphabet, and then forward again to the provided letter,
     * separating each letter with a space.
     * <p>
     * If start is ‘E’, the method should return the string "E D C B A B C D E"
     * <p>
     * This method is only valid for capital letter input; if anything other than a capital letter is
     * provided as an argument, throw an IllegalArgumentException with a descriptive error message.
     *
     * @param start a provided character
     * @return a recursive alphabet pattern
     * @throws IllegalArgumentException if start is anything other than a capital letter
     */
    public static String mirrorA(char start) throws IllegalArgumentException {
        //Check if the input is not a capital letter, then throw an exception.
        if (!Character.isUpperCase(start) || !Character.isLetter(start)) {
            throw new IllegalArgumentException("Invalid input");
        }

        String letter = String.valueOf(start);// Convert a char to string type
        if (start == 'A') {//Base case
            return letter;
        }
        else {// recursive case
            return letter + " " + mirrorA((char) (start - 1)) + " " + letter;
        }
    }

    /**
     * Recursively create an alphabet pattern, starting at the provided character, and moving back and
     * forth to the beginning of the alphabet by steps of size step.
     * <p>
     * If start is ‘E’ and step is 1, the method should return the same string as mirrorA(start).
     * If start is ‘E’ and step is 2, the method should return “E C A C E”
     * If start is ‘E’ and step is 3, the method should return “E B B E” And so on.
     * <p>
     * The method is only valid for capital letter input and strictly positive (not zero or
     * negative) step sizes. For invalid input, throw an IllegalArgumentException with a descriptive
     * error message.
     *
     * @param start the provided character
     * @param step a number determines the next letter in alphabet
     * @return a recursive alphabet pattern
     * @throws IllegalArgumentException if start is anything other than a capital letter or the step sizes is zero or negative value
     */
    public static String mirrorA(char start, int step) throws IllegalArgumentException {
        //Check if the input is not a capital letter or size is not greater than 0, then throw an exception.
        if (!Character.isUpperCase(start) || !Character.isLetter(start) || step <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        String string = String.valueOf(start);// Convert a char to string type
        if (start == 'A') {//Base case
            return string;
        }
        else if ((start - step) < 'A') {//Base case
            return string + " " +  string;
        }
        else{// recursive case
            return string + " " + mirrorA((char)(start - step), step) + " " + string;
        }
    }

    /**
     * Recursively create a simple alphabet pattern, starting the provided character, and moving
     * forward to the end of the alphabet, and then backward again to the provided letter, separating
     * each letter with a space.
     * <p>
     * If start is ‘V’, the method should return the string "V W X Y Z Y X W V"
     * <p>
     * This method is only valid for capital letter input; if anything other than a capital letter is
     * provided as an argument, throw an IllegalArgumentException with a descriptive error message.
     *
     * @param start a provided character
     * @return a recursive alphabet pattern
     * @throws IllegalArgumentException if start is anything other than a capital letter
     */
    public static String mirrorZ(char start) throws IllegalArgumentException {
        //Check if the input is not a capital letter, then throw an exception.
        if (!Character.isUpperCase(start) || !Character.isLetter(start)) {
            throw new IllegalArgumentException("Invalid input");
        }

        String string = String.valueOf(start);// Convert a char to string type
        if (start == 'Z') {//Base case
            return string;
        }
        else {// recursive case
            return string + " " + mirrorZ((char) (start + 1)) + " " + string;
        }
    }

    /**
     * Recursively create an alphabet pattern, starting at the provided character, and moving forward
     * and back to the end of the alphabet by steps of size step.
     * <p>
     * If start is ‘V’ and step is 1, the method should return the same string as mirrorB(end).
     * If start is ‘V’ and step is 2, the method should return “V X Z X V”.
     * If start is ‘V’ and step is 3, the method should return “V Y Y V” And so on.
     * <p>
     * The method is only valid for capital letter input and strictly positive (not zero or
     * negative) step sizes. For invalid input, throw an IllegalArgumentException with a descriptive
     * error message.
     *
     * @param start the provided character
     * @param step a number determines the next letter in alphabet
     * @return a recursive alphabet pattern
     * @throws IllegalArgumentException if start is anything other than a capital letter or the step sizes is zero or negative value
     */
    public static String mirrorZ(char start, int step) throws IllegalArgumentException {
        //Check if the input is not a capital letter or size is not greater than 0, then throw an exception.
        if (!Character.isUpperCase(start) || !Character.isLetter(start) || step <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        String string = String.valueOf(start);// Convert a char to string type
        if (start == 'Z') {//Base case
            return string;
        }
        else if ((start + step) > 'Z') {//Base case
            return string + " " +  string;
        }
        else{// recursive case
            return string + " " + mirrorZ((char)(start + step), step) + " " + string;
        }
    }

}
