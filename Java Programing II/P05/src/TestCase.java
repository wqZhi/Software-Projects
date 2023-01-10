import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads the text contents of the provided file, inserting each new space-separated word at the
 * beginning of the data array.
 * All strings currently in the array should be shifted to the right by one index to make room.
 * That is, the string at index N should be moved to index N+1, and so forth.
 * If you encounter any exceptions while reading the File, simply return from the method.
 * <p>
 * Complexity: O(___)
 */
public class TestCase {
    public static void main(String[] args) {
        String[] data = new String[80000];
        File f = new File("ys.txt");
        Scanner in = null;
        String temp = null;
        int size = 0;
        //String[] tempArray = new String[data.length];

        try {
            in = new Scanner(f);
            in.nextLine();
            int count = 0;
            String[] tempString = new String[80000];

            while (in.hasNextLine()) {

                for (int i = 0; i < tempString.length; i++) {
                    if (in.hasNext()) {
                        tempString[i] = in.next();
                        count++;
                    }
                }
            }

            String[] newArray = new String[count];
            String[] tempArray = new String[count];

            for (int i = 0; i < count; i++) {
                newArray[i] = tempString[i];
            }

            for (int start = 0, end = count - 1; start < end; start++, end--) {
                String temp2 = newArray[end];
                tempArray[end] = newArray[start];
                tempArray[start] = temp;
            }

            for (int i = 0; i < data.length; i++) {
                data[i] = tempArray[i];
            }

            //            while(in.hasNextLine()) {
            //                tempArray = in.nextLine().split(" ");
            //            }

            for (int i = 0; i < data.length; i++) {
                if (data[i] != null) {
                    size++;
                }
                else {
                    break;
                }
            }

            System.out.println(size);

            in.close();
        } catch (FileNotFoundException e) {
            return;
        }
    }
}
