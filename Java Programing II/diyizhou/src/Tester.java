
import java.util.Scanner;

public class Tester {

    public static void main(String[] args) {
        String[] contents = new String[6];
        int front = 11;

        front = (front+1) % contents.length;
        System.out.println(front);
    }
}
