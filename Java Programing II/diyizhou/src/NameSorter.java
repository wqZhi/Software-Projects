import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Name implements Comparable<Name> {
    private String firstName;
    private String lastName;

    Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int compareTo(Name otherName) {
        int comparisonVal;

        comparisonVal = lastName.compareTo(otherName.lastName);
        if (comparisonVal != 0) {
            return comparisonVal;
        }
        else {
            return firstName.compareTo(otherName.firstName);
        }
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName;
    }
}

public class NameSorter {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        final int NUM_ELEMENTS = 4;
        Name newName;
        ArrayList<Name> userNames = new ArrayList<Name>();
        String firstName;
        String lastName;
        int i;

        for (i = 0; i < NUM_ELEMENTS; ++i) {
            firstName = scnr.next();
            lastName = scnr.next();
            newName = new Name(firstName, lastName);
            userNames.add(newName);
        }

        Collections.sort(userNames);

        for (i = 0; i < NUM_ELEMENTS; ++i) {
            System.out.println(userNames.get(i));
        }
    }
}
