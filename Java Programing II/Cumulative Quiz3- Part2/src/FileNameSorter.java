///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Weiqian Zhi
// Campus ID: 9082593279
// WiscEmail: wzhi3@wisc.edu
////////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * This FileSorter class implements a sorting algorithm: sortFileNames() method, along with
 * several helper methods and one simple test method. The sortFileNames() method sorts file names
 * in lexicographic order with respect to the provided specification.
 *
 * Complete the missing implementation marked by the //TODO tags.
 *
 */
public class FileNameSorter {

    /**
     * This method returns the greater of two provided strings as input, with respect to the result of
     * String.compareTo() method.
     *
     * @param s1 a string
     * @param s2 another string
     * @return the greatest of s1 and s2 with respect to the lexicographic order
     */
    protected static String max(String s1, String s2) {
        if (s1.compareTo(s2) > 0) {
            return s1;
        }
        else {
            return s2;
        }
    }

    /**
     * Recursive method which returns the maximum element of an oversize array of strings
     *
     * @param data a non empty oversize array
     * @param size number of elements stored in an oversize array
     * @return the maximum element of the oversize array data of strings
     */
    protected static String getMaximum(String[] data, int size) {
        if (size == 1) {
            return data[size - 1];
        }
        else {
            String temp = getMaximum(data,size - 1);

            if ((max(data[size - 1], temp)).equals(data[size - 1])) {
                return data[size - 1];
            }
            else {
                return temp;
            }
        }
    }

    /**
     * Swaps elements at indexes i and j of an array
     *
     * @param data array of strings
     * @param i    an index
     * @param j    another index
     * @throws ArrayIndexOutOfBoundsException if i or j are out of bounds of the range of indexes from
     *                                        0 .. data.length-1
     */
    private static void swap(String[] data, int i, int j) {
        String x = data[i];
        data[i] = data[j];
        data[j] = x;
    }

    /**
     * Returns the index of the first occurrence of a string within an oversize array
     *
     * @param s    string to search
     * @param data reference to an array of strings
     * @param size size of data
     * @return the index of the first occurrence of s within the oversize array data, or -1 if no
     *         match found.
     */
    private static int indexOf(String s, String[] data, int size) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(s))
                return i;
        }
        return -1;
    }

    /**
     * Sorts an array of file names in the lexicographic order using the recursive getMaximum()
     * method. This sort operation operates in-place.
     *
     * @param names an unsorted array of file names
     */
    public static void sortFileNames(String[] names) {
        String temp = "";
        int maxIndex = -1;
        String maxVal = "";
        int count = names.length;

        while (count != 0) {
            maxVal = getMaximum(names, count);

            maxIndex = indexOf(maxVal, names, names.length);

            if (maxIndex != -1) {
                swap(names, count - 1, maxIndex);
            }

            count--;
        }
    }

    /**
     * One short and simple test for the sortFileNames() method.
     *
     * @return true when this test passes, otherwise false
     */
    public static boolean test() {
        String[] array = {"dog", "cat", "pig", "animal", "fox", "goose"};
        String[] expect = {"animal", "cat", "dog", "fox", "goose", "pig"};
        sortFileNames(array);

        if (!(Arrays.deepEquals(array, expect))) {
            return false;
        }

        return true;
    }

    /**
     * Driver for the test method above.
     *
     * @param args is unused
     */
    public static void main(String[] args) {
        System.out.println("test(): " + test());
    }
}
