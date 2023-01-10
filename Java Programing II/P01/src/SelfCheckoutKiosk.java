//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Self Checkout Kiosk
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
public class SelfCheckoutKiosk {
    public static final double TAX_RATE = 0.05;
    public static final String[][] GROCERY_ITEMS =
        new String[][] {{"Apple", "$1.59"}, {"Avocado", "$0.59"}, {"Banana", "$0.49"},
            {"Beef", "$3.79"}, {"Blueberry", "$6.89"}, {"Broccoli", "$1.79"}, {"Butter", "$4.59"},
            {"Carrot", "$1.19"}, {"Cereal", "$3.69"}, {"Cheese", "$3.49"}, {"Chicken", "$5.09"},
            {"Chocolate", "$3.19"}, {"Cookie", "$9.5"}, {"Cucumber", "$0.79"}, {"Eggs", "$3.09"},
            {"Grape", "$2.29"}, {"Ice Cream", "$5.39"}, {"Milk", "$2.09"}, {"Mushroom", "$1.79"},
            {"Onion", "$0.79"}, {"Pepper", "$1.99"}, {"Pizza", "$11.5"}, {"Potato", "$0.69"},
            {"Spinach", "$3.09"}, {"Tomato", "$1.79"}};

    /**
     * Returns the name of the item given its index
     *
     * @param index unique identifier of an item
     * @return item name
     */
    public static String getItemName(int index) {
        String itemName = GROCERY_ITEMS[index][0];

        return itemName;
    }

    /**
     * Returns the price of an item given its index (unique identifier)
     *
     * @param index unique identifier of an item
     * @return item price
     */
    public static double getItemPrice(int index) {
        String temp = GROCERY_ITEMS[index][1].substring(1);
        double itemPrice = Double.parseDouble(temp);

        return itemPrice;
    }

    /**
     * Prints the Catalog of the grocery store (item identifiers, names, and prices)
     */
    public static void printCatalog() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Item id \tName \t Price");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        for (int i = 0; i < GROCERY_ITEMS.length; i++) {
            System.out.println(i + "\t\t" + getItemName(i) + " \t " + getItemPrice(i));
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    }


    /**
     * Adds the name of a grocery item given its identifier at the end of
     * (the bagging area) the oversize array defined by the items array and its size
     * <p>
     * If the items array reaches its capacity, the following message:
     * "Error! No additional item can be scanned. Please wait for assistance."
     * will be displayed and the method returns without making any change
     * to the contents of the items array.
     *
     * @param id    identifier of the item to be added to the bagging area
     *              (index of the item in the GROCERY_ITEMS array)
     * @param items array storing the names of the items checked out
     *              and placed in the bagging area
     * @param size  number of elements stored in items before trying to add a new item
     * @return the number of elements stored in bagging area after the item
     * with the provided identifier was added to the bagging area
     */
    public static int addItemToBaggingArea(int id, String[] items, int size) {
        if ((size + 1) > items.length) {
            System.out.println(
                "Error! No additional item can be scanned." + " Please wait for assistance.");
            return size;
        }
        else {
            items[size] = GROCERY_ITEMS[id][0];
            return size + 1;
        }

    }


    /**
     * The comparison to find the occurrences of item is case insensitive.
     *
     * @param item  item to count its occurrences
     * @param items a bag of string items
     * @param size  number of items stored in items
     * @return the number of occurrences of a given item in an oversize array of strings.
     */
    public static int count(String item, String[] items, int size) {
        int occurrence = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].equalsIgnoreCase(item)) {
                occurrence++;
            }
        }

        return occurrence;
    }


    /**
     * Returns the index of the first occurrence of item in items if found,
     * and -1 if the item not found
     *
     * @param item  element to search for
     * @param items an array of string elements
     * @param size  number of elements stored in items
     * @return the index of the first occurrence of item in items if found,
     * and -1 if the item not found
     */
    public static int indexOf(String item, String[] items, int size) {
        int itemIndex = -1;
        for (int i = 0; i < size; i++) {
            if (items[i].equalsIgnoreCase(item)) {
                itemIndex = i;
                break;
            }
        }

        return itemIndex;

    }

    /**
     * Removes the first occurrence of itemToRemove from the bagging area
     * defined by the array items and its size. If no match with
     * itemToRemove is found, the method displays the following error
     * message "WARNING: item not found." without making any change
     * to the items array. This method compacts the contents of the items
     * array after removing the itemToRemove so there are no empty spaces
     * in the middle of the array.
     *
     * @param itemToRemove item to remove from the bagging area
     * @param items        a bag of items
     * @param size         number of elements stored in the bag of items
     * @return the number of items present in the cart after the
     * itemToRemove is removed from the cart
     */
    public static int remove(String itemToRemove, String[] items, int size) {
        int j;
        for (int i = 0; i < size; i++) {
            if (items[i].equalsIgnoreCase(itemToRemove)) {
                for (j = i; j < size -1; j++) {
                    items[j] = items[j + 1];
                }
                items[j] = null;

                return size - 1;
            }
        }

        System.out.println("WARNING: item not found.");
        return size;
    }

    /**
     * Gets a copy of the items array without duplicates. Adds every unique item
     * stored within the items array to the itemsSet array.The itemsSet array is
     * initially empty. Recall that a set is a collection which does not contain
     * duplicate items).
     * On the other hand, this method does not make any change to the contents
     * of the items array.
     *
     * @param items    list of items added to the bagging area
     * @param size     number of elements stored in items
     * @param itemsSet reference to an empty array which is going to contain the set
     *                 of items checked out (it does not contain duplicates)
     * @return the number of elements in items without accounting duplicates.
     * In other words, this metho d returns the new size of the itemsSet array
     */
    // Note that we assume that the length of itemsSet equals
    // at least the size of items. This means that itemsSet array
    // can store the set of scanned items at checkout
    public static int getUniqueCheckedOutItems(String[] items, int size, String[] itemsSet) {
        int uniqueCount = 0;

        String temp = null;
        for (int i = 0; i < size; i++) {
            temp = items[i];
            for (int j = 0; j < itemsSet.length; j++) {
                if (itemsSet[j] == null && itemsSet[j] != temp) {
                    itemsSet[j] = temp;
                    break;
                } else if (itemsSet[j] == temp) {
                    break;
                }
            }
        }

        for (String x : itemsSet) {
            if (x != null) {
                uniqueCount++;
            }
        }

        return uniqueCount;
    }

    /**
     * Returns the total value (price) of the scanned items at checkout
     * without tax in $ (double)
     *
     * @param items an array which stores the items checked out
     * @param size  number of elements stored in the items array
     * @return the number of elements in items without accounting duplicates.
     */
    public static double getSubTotalPrice(String[] items, int size) {
        double totalPrice = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < GROCERY_ITEMS.length; j++) {
                if (items[i].equalsIgnoreCase(GROCERY_ITEMS[j][0])) {
                    totalPrice += getItemPrice(j);
                }
            }
        }

        return totalPrice;
        // [Hint] Try to break down this problem into subproblems.
        // define helper methods to help implement the behavior of this method
    }


}
