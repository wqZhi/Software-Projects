//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Benchmark
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
import java.io.PrintWriter;

/**
 * This class is to test and compare the function of SimpleBag class and CleverBag class methods.
 *
 */
public class Benchmark {
    public static void main(String[] args) {
        File in = new File("frank.txt");
        File out = new File("results.txt");
        int[] nValues = {10, 100, 1000, 10000};
        createResultsFile(in, out, nValues);
    }

    /**
     * This method runs both classes’ loadData() implementations on the same text file.
     * <p>
     * Tracks the time spent in milliseconds to complete each loadData(). System.currentTimeMillis()
     * method is to get the current system time in milliseconds.
     *
     * @param f a provided file object
     * @param s a SimpleBag object
     * @param c a CleverBag object
     * @return a formatted String with the elapsed times for each of the bag types.
     */
    public static String compareLoadData(File f, SimpleBag s, CleverBag c) {
        // Compute the elapsed time for SimpleBag loadData();
        long simpleStartTime = System.currentTimeMillis();
        s.loadData(f);
        long simpleEndTime = System.currentTimeMillis();
        long simpleBagLoadTime = simpleEndTime - simpleStartTime;

        // Compute the elapsed time for CleverBag loadData();
        long cleverStartTime = System.currentTimeMillis();
        c.loadData(f);
        long cleverEndTime = System.currentTimeMillis();
        long cleverBagLoadTime = cleverEndTime - cleverStartTime;

        return "load:\t" + simpleBagLoadTime + "\t" + cleverBagLoadTime + "\n";
    }

    /**
     * This method is to call each of the Bag classes’ removeRandom() methods n times, and compare how
     * long it takes each one to complete those calls.
     * <p>
     * Tracks the time spent in milliseconds to complete each type of remove.
     * System.currentTimeMillis() method is to get the current system time in milliseconds.
     *
     * @param n a element from the nValues array.
     * @param s a SimpleBag object
     * @param c a CleverBag object
     * @return a formatted string with n and the elapsed times for each of the bag types.
     */
    public static String compareRemove(int n, SimpleBag s, CleverBag c) {
        // Compute the elapsed time for SimpleBag removeRandom();
        long simpleStartTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            s.removeRandom();
        }
        long simpleEndTime = System.currentTimeMillis();
        long simpleBagRemoveTime = simpleEndTime - simpleStartTime;

        // Compute the elapsed time for CleverBag removeRandom();
        long cleverStartTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            c.removeRandom();
        }
        long cleverEndTime = System.currentTimeMillis();
        long cleverBagRemoveTime = cleverEndTime - cleverStartTime;

        return n + "\t" + simpleBagRemoveTime + "\t" + cleverBagRemoveTime + "\n";
    }

    /**
     * This method is to call each of the Bag classes’ loadData() methods on the same input File and
     * compare how long it takes each one to complete.
     * <p>
     * Creates one instance each of a SimpleBag and a CleverBag. Calls compareLoadData() to compare
     * the two different data loads using the in parameter. Calls compareRemove() on each of the
     * provided nValues to compare the two different remove implementations. Writes the results of the
     * data load comparison followed by the remove comparisons to a file specified by the out
     * parameter. Handles any exceptions raised by the methods it uses.
     *
     * @param in      the provided file to loaded
     * @param out     a provided file to write the results
     * @param nValues an array that
     */
    public static void createResultsFile(File in, File out, int[] nValues) {
        PrintWriter writer = null;
        String loadResult = null;
        String removeResult = "";

        // Create a SimpleBag and a CleverBag object.
        SimpleBag s = new SimpleBag(0);
        CleverBag c = new CleverBag(0);

        // Pass the in File and bags to the compareLoadData(), and save the output.
        loadResult = compareLoadData(in, s, c);
        // Pass each of then Values and bags to the compareRemove(), and save each line of output.
        for (int i = 0; i < nValues.length; i++) {
            removeResult += compareRemove(nValues[i], s, c);
        }

        // Use the out File parameter to open a PrintWriter and write the results to the file
        try {
            writer = new PrintWriter(out);
            writer.println(loadResult + removeResult);
            writer.close();// Close the PrintWriter.
        } catch (FileNotFoundException e) {
            return;
        }

    }
}


