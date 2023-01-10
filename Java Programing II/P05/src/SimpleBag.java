//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Simple Bag
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * This class contains SimpleBag algorithms in loadData() and removeRandom().
 *
 */
public class SimpleBag {
    protected String[] data; // an array that store words from file
    protected Random random; // a random object

    /**
     * Create a new SimpleBag object with a seed.
     * <p>
     * Initializes a protected field, data, which is an array of Strings with capacity 80,000.
     * Initializes a protected Random object, random, using the provided seed value.
     *
     * @param seed used by Random to generate random numbers.
     */
    public SimpleBag(int seed) {
        data = new String[80000];
        random = new Random(seed);
    }

    /**
     * This method reads the text contents of the provided file, inserting each new space-separated
     * word at the beginning of the data array.
     * <p>
     * All strings currently in the array should be shifted to the right by one index to make room.
     * That is, the string at index N should be moved to index N+1, and so forth. If any exceptions
     * come up while reading the File, return from the method.
     * <p>
     * Complexity: O(N^2)
     *
     * @param f a provided file object
     */
    public void loadData(File f) {
        Scanner in = null;
        String[] tempElements = null; // Stored temp values before moving the String to index N+1.

        try {
            in = new Scanner(f);
            in.nextLine(); // skip 78210, which is the first line.
            tempElements = new String[data.length];

            while (in.hasNext()) {
                // If the first index in the array is null, sign a value from scanner and continue.
                if (data[0] == null) {
                    data[0] = in.next();
                    continue;
                }

                tempElements[0] = data[0]; // Store the first index in the temp array.
                data[0] = in.next(); // Sign a value from the scanner to data array at first index.

                // Shift strings in the array shifted to the right by one index to make room.
                for (int i = 1; i < data.length; i++) {
                    tempElements[i] = data[i];
                    data[i] = tempElements[i - 1];
                    if (tempElements[i] == null) {
                        break;
                    }
                }
            }

            in.close();// Close the File.
        } catch (FileNotFoundException e) {
            return;
        }

        int size = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                size++;
            }
            else {
                break;
            }
        }

        System.out.println(size);


    }

    /**
     * This method use a generate random number as a index to remove a string in the array.
     * <p>
     * Counts the number of Strings (i.e. non-null) values in the data array and generates a random
     * index between 0 and the number of Strings stored in this bag (exclusive). Removes and returns
     * the String at that index. Fills gaps by moving all following strings to the left by one index.
     * N -> N-1, etc If the bag contains no strings, this method returns null.
     * <p>
     * Complexity: O(N)
     *
     * @return null if the bag contains no strings. Otherwise, return the removed String.
     */
    public String removeRandom() {
        int size = 0;
        int randNum = -1;
        String temp = null;
        String removeString = null;

        // Track current number in the array
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                size++;
            } else {
                break;
            }
        }

        // If the array doesn't contain anything, return null
        if (size == 0) {
            return null;
        }

        randNum = random.nextInt(size);// Generates a random integer in a specify range.
        removeString = data[randNum]; // Store the remove string.
        data[randNum] = null; // Sign a null value to the remove string at its index.

        // Fills gaps by moving all following strings to the left by one index.
        for (int i = randNum; i < size - 1; i++) {
            temp = data[i];
            data[i] = data[i + 1];
            data[i + 1] = temp;
        }
        return removeString;
    }
}

