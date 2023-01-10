///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Adventure methods test
// Course:          CS 200, Fall, 2020
//
// Author:          Weiqian Zhi
// Email:           wzhi3@wisc.edu
// Lecturer's Name: Marc Renault
//
////////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;
import java.util.Random;

/**
 * This contains testing methods for the Adventure program.
 */
public class TestAdventure {

    /**
     * Uncomment testing methods to have them run.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        testParseCoordinates();
        testParseFields();
        testDetermineLocation();
        testEnter();

    }

    /**
     * Tests for the parseCoordinates method.
     */
    private static void testParseCoordinates() {
        boolean error = false;

        {   //example test:
            String coordinateString = "8,6";
            int[] expected = new int[]{8, 6};
            int[] actual = Adventure.parseCoordinates(coordinateString);
            if (!Arrays.equals(expected, actual)) {
                System.out.println("testParseCoordinates 1) Expected: "
                        + Arrays.toString(expected)
                        + " Actual: " + Arrays.toString(actual));
                error = true;
            }
        }

        {   //test:
            String coordinateString = "15,17";
            int[] expected = new int[]{15, 17};
            int[] actual = Adventure.parseCoordinates(coordinateString);
            if (!Arrays.equals(expected, actual)) {
                System.out.println("testParseCoordinates 1) Expected: " + Arrays.toString(expected)
                        + " Actual: " + Arrays.toString(actual));
                error = true;
            }
        }

        if (error) {
            System.out.println("Error in testParseCoordinates.");
        } else {
            System.out.println("All tests in testParseCoordinates passed.");
        }
    }

    private static void testParseFields() {
        boolean error = false;

        {   //test:
            String fieldsString = "pit/You fell into a pit/lose/you feel a draft/";
            String[] expected = new String[]{"pit", "You fell into a pit", "lose", "you feel a draft", ""};
            String[] actual = Adventure.parseFields(fieldsString);
            if (!Arrays.equals(expected, actual)) {
                System.out.println("testParseCoordinates 1) Expected: "
                        + Arrays.toString(expected)
                        + " Actual: " + Arrays.toString(actual));
                error = true;
            }
        }

        if (error) {
            System.out.println("Error in testParseCoordinates.");
        } else {
            System.out.println("All tests in testParseCoordinates passed.");
        }
    }

    private static void testDetermineLocation() {
        boolean error = false;

        {   //test w:
            String direction = "w";
            String[][][] map = new String[10][10][];
            int[] currentLocation = new int[]{8, 6};
            int[] expected = new int[]{7, 6};
            int[] actual = Adventure.determineLocation(map, currentLocation, direction);
            if (!Arrays.equals(expected, actual)) {
                System.out.println("testParseCoordinates 1) Expected: " + Arrays.toString(expected)
                        + " Actual: " + Arrays.toString(actual));
                error = true;
            }
        }

        {   //test s:
            String direction = "s";
            String[][][] map = new String[10][10][];
            int[] currentLocation = new int[]{8, 6};
            int[] expected = new int[]{9, 6};
            int[] actual = Adventure.determineLocation(map, currentLocation, direction);
            if (!Arrays.equals(expected, actual)) {
                System.out.println("testParseCoordinates 1) Expected: " + Arrays.toString(expected)
                        + " Actual: " + Arrays.toString(actual));
                error = true;
            }
        }

        {   //test a:
            String direction = "a";
            String[][][] map = new String[10][10][];
            int[] currentLocation = new int[]{8, 6};
            int[] expected = new int[]{8, 5};
            int[] actual = Adventure.determineLocation(map, currentLocation, direction);
            if (!Arrays.equals(expected, actual)) {
                System.out.println("testParseCoordinates 1) Expected: " + Arrays.toString(expected)
                        + " Actual: " + Arrays.toString(actual));
                error = true;
            }
        }

        {   //test d:
            String direction = "d";
            String[][][] map = new String[10][10][];
            int[] currentLocation = new int[]{8, 6};
            int[] expected = new int[]{8, 7};
            int[] actual = Adventure.determineLocation(map, currentLocation, direction);
            if (!Arrays.equals(expected, actual)) {
                System.out.println("testParseCoordinates 1) Expected: " + Arrays.toString(expected)
                        + " Actual: " + Arrays.toString(actual));
                error = true;
            }
        }

        if (error) {
            System.out.println("Error in testParseCoordinates.");
        } else {
            System.out.println("All tests in testParseCoordinates passed.");
        }
    }

    private static void testEnter() {
        boolean error = false;

        {   //test:
            Random rand =  new Random();
            String[][][] map = new String[10][10][];
            String[] fields = {"Thanos", "You've been killed by the Thanos."
                    , "lose", "die without a full body", ""};
            map[5][5] = fields;
            int[] location = {5,5};
            boolean expected = true;
            boolean actual = Adventure.enter(map, location, rand);
            if ( !expected == actual) {
                System.out.println("testParseCoordinates 1) Expected: " + expected
                        + " Actual: " + actual);
                error = true;
            }
        }

        {   //test:
            Random rand =  new Random();
            String[][][] map = new String[10][10][];
            String[] fields = {"Anonymous", "You meet a person", "NPC"
                    , "May the Force be with you. Keep going.", ""};
            map[4][4] = fields;
            int[] location = {4,4};
            boolean expected = false;
            boolean actual = Adventure.enter(map, location, rand);
            if ( !expected == actual) {
                System.out.println("testParseCoordinates 1) Expected: " + expected
                        + " Actual: " + actual);
                error = true;
            }
        }

        if ( error) {
            System.out.println("Error in testParseCoordinates.");
        } else {
            System.out.println("All tests in testParseCoordinates passed.");
        }
    }


}
