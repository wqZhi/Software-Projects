
import java.util.ArrayList;

class Business {
    protected String name;
    protected String address;

    public Business() {}

    public Business(String busName, String busAddress) {
        name = busName;
        address = busAddress;
    }

    @Override
    public String toString() {
        return name + " -- " + address;
    }
}


public class ArrayListPrinter {

    // Method prints an ArrayList of Objects
    public static void PrintArrayList(ArrayList<Object> objList) {
        int i;

        for (i = 0; i < objList.size(); ++i) {
            System.out.println(objList.get(i));
        }
    }

    public static void main(String[] args) {
        ArrayList<Object> objList = new ArrayList<Object>();

        // Add new instances of various classes to objList
        objList.add(new Object());
        objList.add(12);
        objList.add(3.14);
        objList.add("Hello!");
        objList.add(new Business("ACME", "5 Main St"));

        // Print list of Objects
        PrintArrayList(objList);
    }
}

