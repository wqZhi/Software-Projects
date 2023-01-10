//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Self Checkout Kiosk Tester
// Course:   CS 300 Fall 2020
//
// Author:   Weiqian Zhi
// Email:    wzhi3@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////

/**
 * Checks whether SelfCheckoutKisok.getItemName() and SelfCheckoutKisok.getItemPrice() method work
 */
public class SelfCheckoutKioskTester {

    /**
     * Calls the test methods implemented in this class and displays their output
     * @param args input arguments if any
     */
    public static void main(String[] args) {
//        System.out.println("testItemNameAndPriceGetterMethods(): "
//            + testItemNameAndPriceGetterMethods());
//        System.out.println("testAddItemToBaggingAreaMethods(): "
//            + testAddItemToBaggingArea());
//        System.out.println("testCountMethods(): "
//            + testCount());
//        System.out.println("testIndexOfMethods(): "
//            + testIndexOf());
        System.out.println("testRemoveMethods(): "
            + testRemove());
//        System.out.println("testGetSubTotalPriceMethods(): "
//            + testGetSubTotalPrice());
//        System.out.println("testGetUniqueCheckedOutItemsMethods(): "
//            + testGetUniqueCheckedOutItems());
    }

    /**
     * Checks whether SelfCheckoutKisok.getItemName() and
     * SelfCheckoutKisok.getItemPrice() method work as expected.
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testItemNameAndPriceGetterMethods() {
        for (int i = 0; i < SelfCheckoutKiosk.GROCERY_ITEMS.length; i++) {
            if (!SelfCheckoutKiosk.getItemName(i).equals(SelfCheckoutKiosk.GROCERY_ITEMS[i][0])) {
                System.out.println(
                    "Problem detected: Called your getItemName() method with " + "input value " + i
                        + ". But it did not return the expected output.");
                return false;
            }

            double expectedPriceOutput =
                Double.valueOf(SelfCheckoutKiosk.GROCERY_ITEMS[i][1].substring(1).trim());

            if (Math.abs((SelfCheckoutKiosk.getItemPrice(i) - expectedPriceOutput)) > 0.001) {
                System.out.println("Error");
                return false;
            }
        }
        return true;
    }

    /**
     * Checks the correctness of SelfCheckoutKiosk.addItemToBaggingArea() method
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testAddItemToBaggingArea() {
        String[] items = new String[10];
        int size = 0;

        // (1) Add one item to an empty bagging area
        size = SelfCheckoutKiosk.addItemToBaggingArea(0, items, size);
        if (size != 1) {
            System.out.println("Problem detected: Tried to add one item to an empty, "
                + "bagging area. The returned size must be 1. But your addItemToBaggingArea "
                + "method returned a different output.");
            return false;
        }
        if (!items[0].equals(SelfCheckoutKiosk.getItemName(0))) {
            // notice here the importance of checking for the correctness of your getItemName()
            // method before calling it above
            System.out.println("Problem detected: Tried to add only one item to an empty, "
                + "bagging area. But that item was not appropriately added to the contents "
                + "of the items array.");
        }

        // (2) Consider a non-empty bagging area
        items = new String[] {"Milk", "Chocolate", "Onion", null, null, null, null};
        size = 3;
        size = SelfCheckoutKiosk.addItemToBaggingArea(10, items, size);
        if (size != 4) {
            System.out.println("Problem detected: Tried to add only one item to an non-empty, "
                + "bagging area. The size must be incremented after the method returns. But "
                + "it was not the case");
            return false;
        }
        if (!items[3].equals(SelfCheckoutKiosk.getItemName(10))) {
            System.out.println("Problem detected: Tried to add one item to an non-empty, "
                + "bagging area. But that item was not appropriately added to the contents "
                + "of the items array.");
        }

        // (3) Consider adding an item to a full bagging are
        items = new String[] {"Pizza", "Eggs", "Apples"};
        size = 3;
        size = SelfCheckoutKiosk.addItemToBaggingArea(2, items, size);
        if (size != 3) {
            System.out.println("Problem detected: Tried to add only one item to an full, "
                + "bagging area. The size must be remain same after the method returns. But "
                + "it was not the case");
            return false;
        }

        return true;
    }

    /**
     * Checks the correctness of SelfCheckoutKiosk.count() method
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testCount() {
        String item = "";
        String[] items = new String[10];
        int size = -1;
        int count = -1;

        // (1) a bagging area (defined by the items array and its size) which contains 0 occurrences of the item.
        item = "Apple";
        size = 5;
        items = new String[] {"Milk", "Chocolate", "Onion", "Avocado", "Banana", null};
        count = SelfCheckoutKiosk.count(item, items, size);
        if (count != 0) {
            System.out.println("Problem detected: Tried to find an item's occurrences in the "
                + "bagging area. The returned count must be 0. But your count "
                + "method returned a different output.");
            return false;
        }

        //(2) a bagging area which contains at least 4 items and only one occurrence of the item to count
        item = "Apple";
        size = 6;
        items = new String[] {"Milk", "Chocolate", "Onion", "Apple", "Banana", "Pepper"};
        count = SelfCheckoutKiosk.count(item, items, size);
        if (count != 1) {
            System.out.println("Problem detected: Tried to find an item's occurrences in the "
                + "bagging area. The returned count must be 1. But your count "
                + "method returned a different output.");
            return false;
        }

        //(3) a bagging area which contains at least 5 items and 2 occurrences of the item to count.
        item = "Apple";
        size = 6;
        items = new String[] {"Milk", "Chocolate", "Onion", "Apple", "Banana", "Apple"};
        count = SelfCheckoutKiosk.count(item, items, size);
        if (count != 2) {
            System.out.println("Problem detected: Tried to find an item's occurrences in the "
                + "bagging area. The returned count must be 2. But your count "
                + "method returned a different output.");
            return false;
        }

        return true;
    }

    /**
     * Checks the correctness of SelfCheckoutKiosk.indexOf() method
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testIndexOf() {
        String item = "";
        String[] items = new String[10];
        int size = -1;
        int index = 0;

        //(1) the cases where the items array contains at least one match with the item to find.
        item = "Apple";
        size = 5;
        items = new String[] {"Milk", "Chocolate", "Onion", "Apple", "Banana"};
        index = SelfCheckoutKiosk.indexOf(item, items, size);
        if (index != 3) {
            System.out.println("Problem detected: Tried to find an item was stored "
                + "in the array. The returned index must be 3. But your indexOf "
                + "method returned a different output.");
            return false;
        }

        //(2) The case when the item was not stored in the array and the expected output is -1.
        item = "Apple";
        size = 5;
        items = new String[] {"Milk", "Chocolate", "Onion", "Avocado", "Banana"};
        index = SelfCheckoutKiosk.indexOf(item, items, size);
        if (index != -1) {
            System.out.println("Problem detected: Tried to find an item and it was "
                + "not stored in the array. The returned index must be -1. But your indexOf "
                + "method returned a different output.");
            return false;
        }

        return true;
    }

    /**
     * Checks the correctness of SelfCheckoutKiosk.remove() method
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testRemove() {
        String itemToRemove = "";
        String[] items = new String[10];
        String[] expectItems = new String[10];
        int size = -1;
        int index = 0;

        itemToRemove = "Apple";
        size = 5;
        items = new String[] {"Milk", "Chocolate", "Onion", "Apple", "Banana"};
        size = SelfCheckoutKiosk.remove(itemToRemove, items, size);
        if (size != 4) {
            System.out.println("Problem detected: Tried to check the size in the "
                + "bagging area after removing an item. The returned size must be 4."
                + " But your remove method returned a different output.");
            return false;
        }
        int sameCount = 0;
        expectItems = new String[] {"Milk", "Chocolate", "Onion", "Banana", null};
        for (int i = 0; i < expectItems.length; i++) {
            if (expectItems[i] == items[i]){
                sameCount++;
            }
        }
        if (sameCount != expectItems.length) {
            System.out.println("Problem detected: Tried to check the other items in the "
                + "items array after calling remove method. The items array must contains"
                + " all the other items. But your remove method returned the items "
                + "array does not contains all the other items.");
            return false;
        }

        itemToRemove = "Apple";
        size = 5;
        items = new String[] {"Apple", "Chocolate", "Onion", "Milk", "Banana", null};
        size = SelfCheckoutKiosk.remove(itemToRemove, items, size);
        if (size != 4) {
            System.out.println("Problem detected: Tried to check the size in the "
                + "bagging area after removing an item. The returned size must be 4."
                + " But your remove method returned a different output.");
            return false;
        }
        sameCount = 0;
        expectItems = new String[] {"Chocolate", "Onion", "Milk", "Banana", null, null};
        for (int i = 0; i < expectItems.length; i++) {
            if (expectItems[i] == items[i]){
                sameCount++;
            }
        }
        if (sameCount != expectItems.length) {
            System.out.println("Problem detected: Tried to check the other items in the "
                + "items array after calling remove method. The items array must contains"
                + " all the other items. But your remove method returned the items "
                + "array does not contains all the other items.");
            return false;
        }

        return true;
    }

    /**
     * Checks the correctness of SelfCheckoutKiosk.getSubTotalPrice() method
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    // Checks whether getSubTotalPrice method returns the correct output
    public static boolean testGetSubTotalPrice() {
        return false;
    }

    /**
     * Checks the correctness of SelfCheckoutKiosk.getUniqueCheckedOutItems() method
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    // Checks whether getUniqueCheckedOutput functioning is correct
    public static boolean testGetUniqueCheckedOutItems() {
        return false;
    }
}

//    public Room(String name, int capacity) {
//        try {
//            if (capacity <= 0) {
//                throw new IllegalArgumentException("Error! The capacity can not be zero or less");
//            }
//            else {
//                for (int i = 0; i < names.size(); i++) {
//                    if (name.equals(names.get(i))) {
//                        throw new IllegalArgumentException(
//                            "Error! Name already used by another Room");
//                    }
//                }
//            }
//
//            this.name = name;
//            this.occupants = new String[capacity];
//            names.add(name);
//
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//    }
