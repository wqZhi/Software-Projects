//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Palindrome Tester
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
 * This class is to test all the methods in Palindrome class.
 *
 */
public class PalindromeTester {

    /**
     * This main method is to call the runAllTests method.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        runAllTests();
    }

    /**
     * This method call all of the test methods.
     *
     * @return true if and only if all methods return true.
     */
    public static boolean runAllTests() {
        if (!testMirrorA() || !testMirrorAStep() || !testMirrorZ() || !testMirrorZStep()) {
            System.out.println("Detect errors in code");
            return false;
        }

        return true; // test passed without errors
    }

    /**
     * This methods test MirrorA() for valid AND invalid input against expected results, and must NOT
     * throw exceptions.
     *
     * @return false if detect errors in code. Otherwise, return true.
     */
    public static boolean testMirrorA() {
        String expectMessage = "Invalid input";

        //Check valid input
        String output = Palindrome.mirrorA('E');
        String expected = "E D C B A B C D E";
        if (!(output.equals(expected))) {
            System.out.println("Problem detected: the return value doesn't work as expected");
            return false;
        }

        //Check invalid input: Input is not a capital letter
        try {
            Palindrome.mirrorA('e');// IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e){
            if (e.getMessage() != expectMessage) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println("Successfully caught the invalid input: 'e' in mirrorA()");
        }

        //Check invalid input: Input is not a letter
        try {
            Palindrome.mirrorA('1');// IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e){
            if (e.getMessage() != expectMessage) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println("Successfully caught the invalid input: '1' in mirrorA()");
        }

        return true;// test passed without errors
    }

    /**
     * This methods test MirrorA()' steps for valid AND invalid input against expected results, and
     * must NOT throw exceptions.
     *
     * @return false if detect errors in code. Otherwise, return true.
     */
    public static boolean testMirrorAStep() {
        String output = null;
        String expected = null;
        String expectMessage = "Invalid input";

        //Check valid input
        output = Palindrome.mirrorA('E', 1);
        expected = "E D C B A B C D E";
        if (!(output.equals(expected))) {
            System.out.println("Problem detected: the return value doesn't work as expected");
            return false;
        }

        output = Palindrome.mirrorA('E', 2);
        expected = "E C A C E";
        if (!(output.equals(expected))) {
            System.out.println("Problem detected: the return value doesn't work as expected");
            return false;
        }

        output = Palindrome.mirrorA('E', 3);
        expected = "E B B E";
        if (!(output.equals(expected))) {
            System.out.println("Problem detected: the return value doesn't work as expected");
            return false;
        }

        output = Palindrome.mirrorA('E', 4);
        expected = "E A E";
        if (!(output.equals(expected))) {
            System.out.println("Problem detected: the return value doesn't work as expected");
            return false;
        }

        output = Palindrome.mirrorA('E', 5);
        expected = "E E";
        if (!(output.equals(expected))) {
            System.out.println("Problem detected: the return value doesn't work as expected");
            return false;
        }

        //Check invalid input: Input char is not a capital letter
        try {
            Palindrome.mirrorA('e', 1);//IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e){
            if (e.getMessage() != expectMessage) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println("Successfully caught the invalid input for start: 'e' in mirrorA()");
        }

        //Check invalid input: Input char is not a letter
        try {
            Palindrome.mirrorA('2', 1);// IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e){
            if (e.getMessage() != expectMessage) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println("Successfully caught the invalid input for start: '2' in mirrorA()");
        }

        //Check invalid input: the integer step is equal to 0
        try {
            Palindrome.mirrorA('E', 0);// IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e){
            if (e.getMessage() != expectMessage) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println("Successfully caught the invalid input for step: '0' in mirrorA()");
        }

        //Check invalid input: the integer step is less than 0
        try {
            Palindrome.mirrorA('E', -6);// IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e){
            if (e.getMessage() != expectMessage) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println("Successfully caught the invalid input for step: '-6' in mirrorA()");
        }

        return true;// test passed without errors
    }

    /**
     * This methods test MirrorZ() for valid AND invalid input against expected results, and must NOT
     * throw exceptions.
     *
     * @return false if detect errors in code. Otherwise, return true.
     */
    public static boolean testMirrorZ() {
        String expectMessage = "Invalid input";

        //Check valid input
        String output = Palindrome.mirrorZ('V');
        String expected = "V W X Y Z Y X W V";
        if (!(output.equals(expected))) {
            System.out.println("Problem detected: the return value doesn't work as expected");
            return false;
        }

        //Check invalid input: Input is not a capital letter
        try {
            Palindrome.mirrorZ('v');// IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e){
            if (e.getMessage() != expectMessage) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println("Successfully caught the invalid input: 'v' in mirrorZ()");
        }

        //Check invalid input: Input is not a letter
        try {
            Palindrome.mirrorZ('2');// IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e){
            if (e.getMessage() != expectMessage) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println("Successfully caught the invalid input: '2' in mirrorZ()");
        }

        return true;// test passed without errors
    }

    /**
     * This methods test MirrorZ()' steps for valid AND invalid input against expected results, and
     * must NOT throw exceptions.
     *
     * @return false if detect errors in code. Otherwise, return true.
     */
    public static boolean testMirrorZStep() {
        String output = null;
        String expected = null;
        String expectMessage = "Invalid input";

        //Check valid input
        output = Palindrome.mirrorZ('V', 1);
        expected = "V W X Y Z Y X W V";
        if (!(output.equals(expected))) {
            System.out.println("Problem detected: the return value doesn't work as expected");
            return false;
        }

        output = Palindrome.mirrorZ('V', 2);
        expected = "V X Z X V";
        if (!(output.equals(expected))) {
            System.out.println("Problem detected: the return value doesn't work as expected");
            return false;
        }

        output = Palindrome.mirrorZ('V', 3);
        expected = "V Y Y V";
        if (!(output.equals(expected))) {
            System.out.println("Problem detected: the return value doesn't work as expected");
            return false;
        }

        output = Palindrome.mirrorZ('V', 4);
        expected = "V Z V";
        if (!(output.equals(expected))) {
            System.out.println("Problem detected: the return value doesn't work as expected");
            return false;
        }

        output = Palindrome.mirrorZ('V', 5);
        expected = "V V";
        if (!(output.equals(expected))) {
            System.out.println("Problem detected: the return value doesn't work as expected");
            return false;
        }

        //Check invalid input: Input char is not a capital letter
        try {
            Palindrome.mirrorZ('v', 1);//IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e){
            if (e.getMessage() != expectMessage) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println("Successfully caught the invalid input for start: 'v' in mirrorZ()");
        }

        //Check invalid input: Input char is not a letter
        try {
            Palindrome.mirrorZ('1', 1);// IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e){
            if (e.getMessage() != expectMessage) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println("Successfully caught the invalid input for start: '1' in mirrorZ()");
        }

        //Check invalid input: the integer step is equal to 0
        try {
            Palindrome.mirrorZ('V', 0);// IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e){
            if (e.getMessage() != expectMessage) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println("Successfully caught the invalid input for step: '0' in mirrorZ()");
        }

        //Check invalid input: the integer step is less than 0
        try {
            Palindrome.mirrorZ('V', -1);// IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e){
            if (e.getMessage() != expectMessage) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println("Successfully caught the invalid input for step: '-1' in mirrorZ()");
        }

        return true;// test passed without errors
    }
}
