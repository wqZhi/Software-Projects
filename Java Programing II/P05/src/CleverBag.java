//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Clever Bag
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
import java.util.Scanner;

/**
 * This class inherit from SimpleBag class, which also contains methods from SimpleBag.
 *
 */
public class CleverBag extends SimpleBag {
    private int size; // Track the current number of initialized Strings in the parent class's data
    // array.

    /**
     * Create a new CleverBag object with a seed.
     * <p>
     * Calls the super class’ constructor with appropriate arguments. Initializes the private integer
     * field, size, which will track the current number of initialized Strings in the parent
     * class’data array.
     *
     * @param seed used by Random to generate random numbers.
     */
    public CleverBag(int seed) {
        super(seed);
        size = 0;
    }

    /**
     * This method reads the text contents of the provided file, inserts the new words at the end of
     * the array and then updates the size field accordingly.
     * <p>
     * If any exceptions comes up while reading the File, return from the method.
     * <p>
     * Complexity: O(N)
     *
     * @param f a provided file object
     */
    @Override
    public void loadData(File f) {
        Scanner in = null;

        try {
            in = new Scanner(f);
            in.nextLine();// skip 78210, which is the first line.

            // inserts the new words at the end of the array
            for (int i = 0; i < data.length; i++) {
                if (in.hasNext()) {
                    data[i] = in.next();
                    size++;// increment the size if found a words in the array.
                }
            }
            in.close();// Close the File.
        } catch (FileNotFoundException e) {
            return;
        }
    }

    /**
     * This method use a generate random number as a index to remove a string in the array.
     * <p>
     * Generates a random integer between 0 and the current size. Removes and returns the String at
     * that index. Fills gaps by moving the last String into the gap and decrementing size. If the bag
     * contains no strings, this method returns null.
     * <p>
     * Complexity: O(1)
     *
     * @return null if the bag contains no strings. Otherwise, return the removed string.
     */
    @Override
    public String removeRandom() {
        int randNum = -1;
        String removeString = null;

        // If the array doesn't contain anything, return null
        if (size == 0) {
            return null;
        }

        randNum = random.nextInt(size);// Generates a random integer in a specify range.
        removeString = data[randNum];// Store the remove string.
        data[randNum] = null;// Removes the String at that index.
        data[randNum] = data[size - 1];// Fills gaps by moving the last String into the gap.
        size--;// Decrementing the array size after removed a string.

        return removeString;
    }
}

