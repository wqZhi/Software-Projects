//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Inventory System Tester
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

import java.util.NoSuchElementException;

/**
 * This class test methods that defined in the InventoryList class and LinkedBox class.
 */
public class InventorySystemTester {

    /**
     * Checks for the methods correctness in LinkedBox.java
     *
     * @return true if the expected behavior is satisfied and false otherwise.
     */
    public static boolean testLinkedBox() {
        Box.restartNextInventoryNumber();

        LinkedBox lb1 = new LinkedBox(new Box(Color.YELLOW), null);
        if (!(lb1.getNext() == null) || !(lb1.getBox().getInventoryNumber() == 1) || !(lb1.getBox()
            .getColor().equals(Color.YELLOW))) {
            System.out.println("Problem detected: The LinkedBox.java function works failed");
        }

        LinkedBox lb2 = new LinkedBox(new Box(Color.BLUE));
        if (!(lb2.getNext() == null) || !(lb2.getBox().getInventoryNumber() == 2) || !(lb2.getBox()
            .getColor().equals(Color.BLUE))) {
            System.out.println("Problem detected: The LinkedBox.java function works failed");
        }

        return true;// test passed without errors
    }

    /**
     * checks for the correctness of the InventoryList.clear() method
     *
     * @return true if the expected behavior is satisfied and false otherwise.
     */
    public static boolean testClear() {
        Box.restartNextInventoryNumber();
        InventoryList list = new InventoryList();

        list.addBrown(new Box(Color.BROWN));
        list.addBrown(new Box(Color.BROWN));
        list.addBlue(new Box(Color.BLUE));
        list.addYellow(new Box(Color.YELLOW));

        list.clear();
        int resultSize = list.size();
        if (!(resultSize == 0 && list.getYellowCount() == 0 && list.getBlueCount() == 0
            && list.getBrownCount() == 0)) {
            System.out.println("Problem detected: InventoryList.clear() failed");
            return false;
        }

        return true;// test passed without errors
    }

    /**
     * checks for the correctness of the InventoryList.addYellow(), InventoryList.addBlue(),
     * and InventoryList.addBrown() methods
     *
     * @return true if the expected behavior is satisfied and false otherwise.
     */
    public static boolean testAddBoxes() {
        Box.restartNextInventoryNumber();
        InventoryList list = new InventoryList();

        //Check addYellow()
        //Case 1: Catch Exception When Box is null or yellowBox's color is not yellow
        Box yellowBox1 = null;
        String expectMessage1 = "\nWARNING: The yellowBox doesn't contain any value";
        try {
            list.addYellow(yellowBox1);//IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e) {
            if (!(e.getMessage().equals(expectMessage1))) {
                System.out.println("Problem detected: the error message was not as expected");
            }
            System.out.println("Successfully caught the invalid input in addYellow()");
        }

        Box.restartNextInventoryNumber();
        list.clear();

        Box yellowBox2 = new Box(Color.BROWN);
        String expectMessage2 = "\nWARNING: The yellowBox's color is not equal to Color.YELLOW";
        try {
            list.addYellow(yellowBox2);//IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e) {
            if (!(e.getMessage().equals(expectMessage2))) {
                System.out.println("Problem detected: the error message was not as expected");
            }
            System.out.println("Successfully caught the invalid input in addYellow()");
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 2: add one Yellow box when the list is empty
        list.addYellow(new Box(Color.YELLOW));
        if (!(list.getYellowCount() == 1) || !(list.size() == 1) || !(list.get(0).getColor()
            .equals(Color.YELLOW))) {
            System.out.println("Problem detected: addYellow() doesn't add yellow box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 3: add one Yellow box when the list is not empty
        list.addBrown(new Box(Color.BROWN));
        list.addYellow(new Box(Color.YELLOW));
        list.addBlue(new Box(Color.BLUE));
        list.addYellow(new Box(Color.YELLOW));
        if (!(list.getYellowCount() == 2) || !(list.size() == 4) || !(list.get(0).getColor()
            .equals(Color.YELLOW)) || !(list.get(1).getColor().equals(Color.YELLOW))) {
            System.out.println("Problem detected: addYellow() doesn't add yellow box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Check addBrown()
        //Case 1: Catch Exception When Box is null or brownBox's color is not brown
        Box brownBox1 = null;
        String expectMessage3 = "\nWARNING: The brownBox doesn't contain any value";
        try {
            list.addBrown(brownBox1);//IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e) {
            if (!(e.getMessage().equals(expectMessage3))) {
                System.out.println("Problem detected: the error message was not as expected");
            }
            System.out.println("Successfully caught the invalid input in addBrown()");
        }

        Box.restartNextInventoryNumber();
        list.clear();

        Box brownBox2 = new Box(Color.YELLOW);
        String expectMessage4 = "\nWARNING: The brownBox's color is not equal to Color.BROWN";
        try {
            list.addBrown(brownBox2);//IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e) {
            if (!(e.getMessage().equals(expectMessage4))) {
                System.out.println("Problem detected: the error message was not as expected");
            }
            System.out.println("Successfully caught the invalid input in addBrown()");
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 2: add one Brown box when the list is empty
        list.addBrown(new Box(Color.BROWN));
        if (!(list.getBrownCount() == 1) || !(list.size() == 1) || !(list.get(0).getColor()
            .equals(Color.BROWN))) {
            System.out.println("Problem detected: addBrown() doesn't add brown box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 3: add one Brown box when the list is not empty
        list.addYellow(new Box(Color.YELLOW));
        list.addYellow(new Box(Color.YELLOW));
        list.addBrown(new Box(Color.BROWN));
        list.addBlue(new Box(Color.BLUE));
        list.addYellow(new Box(Color.YELLOW));
        if (!(list.getBrownCount() == 1) || !(list.size() == 5) || !(list.get(4).getColor()
            .equals(Color.BROWN))) {
            System.out.println("Problem detected: addBrown() doesn't add brown box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Check addBlue()
        //Case 1: Catch Exception When Box is null or blueBox's color is not blue
        Box blueBox1 = null;
        String expectMessage5 = "\nWARNING: The blueBox doesn't contain any value";
        try {
            list.addBlue(blueBox1);//IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e) {
            if (!(e.getMessage().equals(expectMessage5))) {
                System.out.println("Problem detected: the error message was not as expected");
            }
            System.out.println("Successfully caught the invalid input in addBlue()");
        }

        Box.restartNextInventoryNumber();
        list.clear();

        Box blueBox2 = new Box(Color.YELLOW);
        String expectMessage6 = "\nWARNING: The blueBox's color is not equal to Color.BLUE";
        try {
            list.addBlue(blueBox2);//IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e) {
            if (!(e.getMessage().equals(expectMessage6))) {
                System.out.println("Problem detected: the error message was not as expected");
            }
            System.out.println("Successfully caught the invalid input in addBlue()");
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 2: add one Blue box when the list is empty
        list.addBlue(new Box(Color.BLUE));
        if (!(list.getBlueCount() == 1) || !(list.size() == 1) || !(list.get(0).getColor()
            .equals(Color.BLUE))) {
            System.out.println("Problem detected: addBlue() doesn't add blue box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 3: add one Blue box when the list contains one Yellow box
        list.addYellow(new Box(Color.YELLOW));
        list.addBlue(new Box(Color.BLUE));
        if (!(list.getBlueCount() == 1) || !(list.size() == 2) || !(list.get(1).getColor()
            .equals(Color.BLUE))) {
            System.out.println("Problem detected: addBlue() doesn't add blue box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 4: add one Blue box when the list contains one Brown box
        list.addBrown(new Box(Color.BROWN));
        list.addBlue(new Box(Color.BLUE));
        if (!(list.getBlueCount() == 1) || !(list.size() == 2) || !(list.get(0).getColor()
            .equals(Color.BLUE))) {
            System.out.println("Problem detected: addBlue() doesn't add blue box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 5: add one Blue box when the list contains Yellow2, Brown1 box
        list.addBrown(new Box(Color.BROWN));
        list.addYellow(new Box(Color.YELLOW));
        list.addBlue(new Box(Color.BLUE));
        if (!(list.getBlueCount() == 1) || !(list.size() == 3) || !(list.get(1).getColor()
            .equals(Color.BLUE))) {
            System.out.println("Problem detected: addBlue() doesn't add blue box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 6: add one Blue box when the list contains Yellow3, Yellow2, Blue1 box
        list.addBlue(new Box(Color.BLUE));
        list.addYellow(new Box(Color.YELLOW));
        list.addYellow(new Box(Color.YELLOW));
        list.addBlue(new Box(Color.BLUE));
        if (!(list.getBlueCount() == 2) || !(list.size() == 4) || !(list.get(2).getColor()
            .equals(Color.BLUE)) || !(list.get(2).getInventoryNumber() == 4)) {
            System.out.println("Problem detected: addBlue() doesn't add blue box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 7: add one Blue box when the list contains Brown1, Brown2 box
        list.addBrown(new Box(Color.BROWN));
        list.addBrown(new Box(Color.BROWN));
        list.addBlue(new Box(Color.BLUE));
        if (!(list.getBlueCount() == 1) || !(list.size() == 3) || !(list.get(0).getColor()
            .equals(Color.BLUE))) {
            System.out.println("Problem detected: addBlue() doesn't add blue box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 8: add one Blue box when the list contains Yellow1, Yellow2 box
        list.addYellow(new Box(Color.YELLOW));
        list.addYellow(new Box(Color.YELLOW));
        list.addBlue(new Box(Color.BLUE));
        if (!(list.getBlueCount() == 1) || !(list.size() == 3) || !(list.get(2).getColor()
            .equals(Color.BLUE))) {
            System.out.println("Problem detected: addBlue() doesn't add blue box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 9: add one Blue box when the list contains Blue1 box
        list.addBlue(new Box(Color.BLUE));
        list.addBlue(new Box(Color.BLUE));
        if (!(list.getBlueCount() == 2) || !(list.size() == 2) || !(list.get(0).getColor()
            .equals(Color.BLUE))) {
            System.out.println("Problem detected: addBlue() doesn't add blue box Successfully");
            return false;
        }

        return true;// test passed without errors
    }

    /**
     * Checks for the correctness of the InventoryList.removeBox() InventoryList.removeYellow(),
     * and InventoryList.remove Brown() methods
     *
     * @return true if the expected behavior is satisfied and false otherwise.
     */
    public static boolean testRemoveBoxes() {
        Box.restartNextInventoryNumber();
        String expectMessage = "";
        InventoryList list = new InventoryList();

        //Check removeYellow()
        //Case 1: When the list contains no Yellow box
        list.addBlue(new Box(Color.BLUE));
        list.addBrown(new Box(Color.BROWN));
        expectMessage = "\nWARNING: list does not contain any yellow boxes";
        try {
            list.removeYellow();//NoSuchElementException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (NoSuchElementException e) {
            if (!(e.getMessage().equals(expectMessage))) {
                System.out.println("Problem detected: the error message was not as expected");
            }
            System.out.println("Successfully caught the invalid action in removeYellow()");
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 2: When the list contains one Yellow1
        list.addYellow(new Box(Color.YELLOW));
        Box removeBox = list.removeYellow();
        if (!(removeBox.getColor().equals(Color.YELLOW)) || !(removeBox.getInventoryNumber() == 1)
            || !(list.size() == 0)) {
            System.out.println(
                "Problem detected: removeYellow() doesn't remove yellow5 box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 3: When the list contains Yellow5, Yellow3, Blue2, Brown1, Brown4
        list.addBrown(new Box(Color.BROWN));
        list.addBlue(new Box(Color.BLUE));
        list.addYellow(new Box(Color.YELLOW));
        list.addBrown(new Box(Color.BROWN));
        list.addYellow(new Box(Color.YELLOW));
        Box removeBox2 = list.removeYellow();
        if (!(removeBox2.getColor().equals(Color.YELLOW)) || !(removeBox2.getInventoryNumber() == 5)
            || !(list.size() == 4)) {
            System.out.println(
                "Problem detected: removeYellow() doesn't remove yellow5 box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Check removeBrown()
        //Case 1: When the list contains no Brown box
        list.addBlue(new Box(Color.BLUE));
        list.addYellow(new Box(Color.YELLOW));
        expectMessage = "\nWARNING: list does not contain any brown boxes";
        try {
            list.removeBrown();//NoSuchElementException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (NoSuchElementException e) {
            if (!(e.getMessage().equals(expectMessage))) {
                System.out.println("Problem detected: the error message was not as expected");
            }
            System.out.println("Successfully caught the invalid action in removeBrown()");
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 2: When the list contains one Brown1
        list.addBrown(new Box(Color.BROWN));
        Box removeBox3 = list.removeBrown();
        if (!(removeBox3.getColor().equals(Color.BROWN)) || !(removeBox3.getInventoryNumber() == 1)
            || !(list.size() == 0)) {
            System.out
                .println("Problem detected: removeBrown() doesn't remove Brown1 box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 3: When the list contains Yellow5, Yellow3, Blue2, Brown1, Brown4
        list.addBrown(new Box(Color.BROWN));
        list.addBlue(new Box(Color.BLUE));
        list.addYellow(new Box(Color.YELLOW));
        list.addBrown(new Box(Color.BROWN));
        list.addYellow(new Box(Color.YELLOW));
        Box removeBox4 = list.removeBrown();// Remove Brown4
        if (!(removeBox4.getColor().equals(Color.BROWN)) || !(removeBox4.getInventoryNumber() == 4)
            || !(list.size() == 4)) {
            System.out
                .println("Problem detected: removeBrown() doesn't remove Brown4 box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Check removeBox()
        //Case 1: put invalid inventoryNumber
        list.addBrown(new Box(Color.BROWN));
        list.addBlue(new Box(Color.BLUE));
        list.addYellow(new Box(Color.YELLOW));
        list.addBrown(new Box(Color.BROWN));//list contains Yellow3, Blue2, Brown1, Brown4
        expectMessage = "Error to remove box. Box #6 not found!";
        try {
            list.removeBox(6);//NoSuchElementException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (NoSuchElementException e) {
            if (!(e.getMessage().equals(expectMessage))) {
                System.out.println("Problem detected: the error message was not as expected");
            }
            System.out.println("Successfully caught the invalid action in removeBox()");
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //When nothing in the list
        expectMessage = "Error to remove box. Box #77 not found!";
        try {
            list.removeBox(77);//NoSuchElementException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (NoSuchElementException e) {
            if (!(e.getMessage().equals(expectMessage))) {
                System.out.println("Problem detected: the error message was not as expected");
            }
            System.out.println("Successfully caught the invalid action in removeBox()");
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 2: remove Yellow 3
        list.addBrown(new Box(Color.BROWN));
        list.addBlue(new Box(Color.BLUE));
        list.addYellow(new Box(Color.YELLOW));
        list.addBrown(new Box(Color.BROWN));//list contains Yellow3, Blue2, Brown1, Brown4
        Box removeBox5 = list.removeBox(3);
        if (!(removeBox5.getColor().equals(Color.YELLOW)) || !(removeBox5.getInventoryNumber() == 3)
            || !(list.size() == 3)) {
            System.out
                .println("Problem detected: removeBox() doesn't remove Yellow3 box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 3: remove Blue 2
        list.addBrown(new Box(Color.BROWN));
        list.addBlue(new Box(Color.BLUE));
        list.addYellow(new Box(Color.YELLOW));
        list.addBrown(new Box(Color.BROWN));//list contains Yellow3, Blue2, Brown1, Brown4
        Box removeBox6 = list.removeBox(2);
        if (!(removeBox6.getColor().equals(Color.BLUE)) || !(removeBox6.getInventoryNumber() == 2)
            || !(list.size() == 3)) {
            System.out
                .println("Problem detected: removeBox() doesn't remove Blue2 box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 4: remove Brown4
        list.addBrown(new Box(Color.BROWN));
        list.addBlue(new Box(Color.BLUE));
        list.addYellow(new Box(Color.YELLOW));
        list.addBrown(new Box(Color.BROWN));//list contains Yellow3, Blue2, Brown1, Brown4
        Box removeBox7 = list.removeBox(4);
        if (!(removeBox7.getColor().equals(Color.BROWN)) || !(removeBox7.getInventoryNumber() == 4)
            || !(list.size() == 3)) {
            System.out
                .println("Problem detected: removeBox() doesn't remove Brown4 box Successfully");
            return false;
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //Case 4: remove Blue1
        list.addBlue(new Box(Color.BLUE));//list contains Blue1
        Box removeBox8 = list.removeBox(1);
        if (!(removeBox8.getColor().equals(Color.BLUE)) || !(removeBox8.getInventoryNumber() == 1)
            || !(list.size() == 0)) {
            System.out
                .println("Problem detected: removeBox() doesn't remove Blue1 box Successfully");
            return false;
        }

        return true;// test passed without errors
    }

    /**
     * Checks for the correctness of the InventoryList.get() method
     *
     * @return false if detect errors in code. Otherwise, return true.
     */
    public static boolean testGetBoxes() {
        Box.restartNextInventoryNumber();
        InventoryList list = new InventoryList();

        //Check invalid input
        list.addBlue(new Box(Color.BLUE));
        list.addBrown(new Box(Color.BROWN));
        String expectMessage1 = "\nWARNING: Index should be between 0 and 1 !";
        try {
            list.get(2);//IndexOutOfBoundsException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IndexOutOfBoundsException e) {
            if (!(e.getMessage().equals(expectMessage1))) {
                System.out.println("Problem detected: the error message was not as expected");
            }
            System.out.println("Successfully caught the invalid action in get()");
        }

        Box.restartNextInventoryNumber();
        list.clear();

        //When the list contains Yellow3, Blue1, Brown2, Brown4
        list.addBlue(new Box(Color.BLUE));
        list.addBrown(new Box(Color.BROWN));
        list.addYellow(new Box(Color.YELLOW));
        list.addBrown(new Box(Color.BROWN));
        Box getBox = list.get(1);
        if (!(getBox.getColor().equals(Color.BLUE)) || !(getBox.getInventoryNumber() == 1)) {
            System.out.println("Problem detected: get() doesn't get the right box Successfully");
            return false;
        }

        return true;// test passed without errors
    }

    // a test suite method to run all your test methods
    public static boolean runAllTests() {
        if (!testLinkedBox()) {
            System.out.println("Detect errors in testLinkedBox() method");
            return false;
        }
        else if (!testClear()) {
            System.out.println("Detect errors in testClear() method");
            return false;
        }
        else if (!testAddBoxes()) {
            System.out.println("Detect errors in testAddBoxes() method");
            return false;
        }
        else if (!testRemoveBoxes()) {
            System.out.println("Detect errors in testRemoveBoxes() method");
            return false;
        }
        else if (!testGetBoxes()) {
            System.out.println("Detect errors in testGetBoxes() method");
            return false;
        }

        return true; //test passed without errors
    }

    /**
     * This main method is to call the runAllTests method.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        runAllTests();
    }

}
