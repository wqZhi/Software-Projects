import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Color implements Comparable<Color> {
    private short red;
    private short green;
    private short blue;

    Color(short red, short green, short blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public int compareTo(Color otherColor) {
        if (red > otherColor.red) {
            return 1;
        }
        else if (red < otherColor.red) {
            return -1;
        }
        else if (green > otherColor.green) {
            return 1;
        }
        else if (green < otherColor.green) {
            return -1;
        }
        else if (blue > otherColor.blue) {
            return 1;
        }
        else if (blue < otherColor.blue) {
            return -1;
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %d)", red, green, blue);
    }
}

public class ColorSorter {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        final int NUM_ELEMENTS = 5;
        ArrayList<Color> colors = new ArrayList<Color>();
        int i;
        short red;
        short green;
        short blue;

        for (i = 0; i < NUM_ELEMENTS; ++i) {
            red = (short)(scnr.nextShort());
            green = (short)scnr.nextShort();
            blue = (short)scnr.nextShort();
            colors.add(new Color(red, green, blue));
        }

        Collections.sort(colors);

        for (i = 0; i < NUM_ELEMENTS; ++i) {
            System.out.println(colors.get(i));
        }
    }
}
