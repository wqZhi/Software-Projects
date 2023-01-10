//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Inventory List
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
 * This class models a singly linked list of boxes which can be used to store and manage inventory.
 */
public class InventoryList {
    private LinkedBox head;// represents the head of this list.
    private LinkedBox tail;// represents tail of this list.
    private int size;// keeps track of the total number of boxes stored in this list.
    private int yellowCount;// keeps track of the total number of YELLOW boxes stored in this list.
    private int blueCount;//keeps track of the total number of BLUE boxes stored in this list.
    private int brownCount;//keeps track of the total number of BROWN boxes stored in this list.

    /**
     * This method returns the size of this list
     *
     * @return the size of this LinkedBoxList
     */
    public int size() {
        return this.size;
//        int currNode = 0;
//        LinkedBox runner = head;
//
//        //if list not empty, traverse the list starting from head to count stored items
//        while (runner != null) {
//            currNode++;
//            runner = runner.getNext();
//        }
//
//        return currNode;
    }

    /**
     * This method checks whether this LinkedBoxList is empty
     *
     * @return true if this LinkedBoxList is empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * This method adds a new yellow box at the head of this list
     * <p>
     * YELLOW boxes can be added only at the head of the list.
     * YELLOW boxes can be removed only from the head of the list.
     *
     * @param yellowBox new yellow box to be added to this list
     * @throws IllegalArgumentException if yellowBox is null or if its color does not equal to Color.YELLOW
     */
    public void addYellow(Box yellowBox) {
        if (yellowBox == null) {
            throw new IllegalArgumentException("\nWARNING: The yellowBox doesn't contain any value");
        }
        else if (!(yellowBox.getColor().equals(Color.YELLOW))) {
            throw new IllegalArgumentException("\nWARNING: The yellowBox's color is not equal to Color.YELLOW");
        }

        LinkedBox newYellow = new LinkedBox(yellowBox);
        if (isEmpty()) {//if list is empty
            head = newYellow;//add newNode at the head of this list
            tail = newYellow;//add newNode at the end of this list
            tail.setNext(null);
        }
        else {
            newYellow.setNext(head);//set newNode's link to current head
            head = newYellow;//add newNode at the head of this list
        }

        size++;
        yellowCount++;
    }

    /**
     * This method adds a brown box at the end of this inventory list
     * <p>
     * BROWN boxes can be added only at the end (tail) of the list.
     * BROWN boxes can be removed only from the end (tail) of the list.
     * BROWN boxes can be added to an empty list.
     *
     * @param brownBox new brown box to be added to this list
     * @throws IllegalArgumentException if brownBox is null or if the color of the specific brownBox is not equal to Color.BROWN
     */
    public void addBrown(Box brownBox) {
        if (brownBox == null) {
            throw new IllegalArgumentException("\nWARNING: The brownBox doesn't contain any value");
        }
        else if (!(brownBox.getColor().equals(Color.BROWN))) {
            throw new IllegalArgumentException("\nWARNING: The brownBox's color is not equal to Color.BROWN");
        }

        LinkedBox newBrown = new LinkedBox(brownBox);
        if (isEmpty()) {//if list is empty
            head = newBrown;//add newNode at the head of this list
            tail = newBrown;//add newNode at the end of this list
            tail.setNext(null);
        }
        else {
            newBrown.setNext(null);//set newNode's link to null
            tail.setNext(newBrown);//set current tail link to newNode
            tail = newBrown;//add newNode at the tail of this list
        }

        size++;
        brownCount++;
    }

    /**
     * This method adds a new blue box at the top of blue boxes if the list contains any blue box.
     * Blue boxes must be added at the buttom of yellow boxes and at the top of all the brown boxes
     * if any. This means that a new blue box must be added at index yellowCount.
     * <p>
     * BLUE boxes can be added at the head of the list if no YELLOW boxes are present in the list.
     * BLUE boxes can be added to an empty list.
     *
     * @param blueBox new blue box to be added to this list
     * @throws IllegalArgumentException if blueBox is null or if it does not have a Color.BLUE color
     */
    public void addBlue(Box blueBox) {
        if (blueBox == null) {
            throw new IllegalArgumentException("\nWARNING: The blueBox doesn't contain any value");
        }
        else if (!(blueBox.getColor().equals(Color.BLUE))) {
            throw new IllegalArgumentException("\nWARNING: The blueBox's color is not equal to Color.BLUE");
        }

        LinkedBox newBlue = new LinkedBox(blueBox);
        if (isEmpty()) {//if list is empty
            head = newBlue;//add newNode at the head of this list
            tail = newBlue;//add newNode at the tail of this list
            tail.setNext(null);
        }
        else if (size == 1) {//if there is only one box in the list
            if (yellowCount == 1) {//if the box is yellowBox
                newBlue.setNext(null);//set newNode's link to null
                head.setNext(newBlue);//set current head link to newNode
                tail = newBlue;//add newNode at the tail of this list
            }
            else{//if the box is brownBox or blueBox
                newBlue.setNext(head);//set newNode's link to head
                head = newBlue;//add newNode at the head of this list
            }

        }
        else if (size > 1) {//if more than one box in the list
            if (yellowCount == 0) {//if there is no yellowBox
                newBlue.setNext(head);//set newNode's link to head
                head = newBlue;//add newNode at the head of this list
            }
            else {//if there have yellow box
                LinkedBox runner = head;
                //traverse the list starting from head to get the last yellow box
                for (int i = 1; i < yellowCount; i++) {
                    runner = runner.getNext();
                }

                //set newNode's link to last yellow box's next link
                newBlue.setNext(runner.getNext());

                if (runner.getNext() == null) {//if the last yellow box's next link is null
                    tail = newBlue;//add newNode at the tail of this list
                }

                runner.setNext(newBlue);//set the last yellow box's link to newNode
            }
        }

        size++;
        blueCount++;
}

    /**
     * This method returns the element stored at position index of this list without removing it.
     *
     * @param index position within this list
     * @return the box stored at position index of this list
     * @throws IndexOutOfBoundsException if the index is out of bounds (index < 0 || index >= size())
     */
    public Box get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("\nWARNING: Index should be between 0 and " + (size() - 1) + " !");
        }

        //traverse the list starting from index 0 to find the specify items
        LinkedBox runner = head;
        for (int i = 0; i < index; i++) {
            runner = runner.getNext();
        }

        return runner.getBox();
    }

    /**
     * This method removes and returns the box at the head of this list if its color is yellow
     *
     * @return a reference to the removed box
     * @throws NoSuchElementException if this list does not contain any yellow boxes
     */
    public Box removeYellow() {
        if (yellowCount == 0) {
            throw new NoSuchElementException("\nWARNING: list does not contain any yellow boxes");
        }
        else {
            LinkedBox remove = head;//store node which are going to be removed
            if (size == 1) {// if the list only contains one node
                clear();//remove all the element in the list
            }
            else {
                head = remove.getNext();//add remove node's next node at the head of this list
                yellowCount--;
                size--;
            }

            return remove.getBox();
        }
    }

    /**
     * This method removes and returns the element at the tail of this list if it has a brown color
     *
     * @return a reference to the removed element
     * @throws NoSuchElementException if this list does not contain any brown box (brownCount == 0)
     */
    public Box removeBrown() {
        if (brownCount == 0) {
            throw new NoSuchElementException("\nWARNING: list does not contain any brown boxes");
        }
        else {
            LinkedBox remove = tail;//store node which are going to be removed
            if (size == 1){// if the list only contains one node
                clear();//remove all the element in the list
            }
            else {
                LinkedBox runner = head;
                //traverse the list in limit times to find the last brown box's previous node
                for (int i = 1; i < size - 1; i++) {
                    runner = runner.getNext();
                }
                runner.setNext(null);//set previous node link to null
                tail = runner;//add previous node at the tail of this list
                brownCount--;
                size--;
            }

            return remove.getBox();
        }
    }

    /**
     * This method removes and returns a box given its inventory number from this list
     * <p>
     * Any box stored in the inventory list can be removed given their inventory number.
     *
     * @param inventoryNumber inventory number of the box to be removed from this list
     * @return a reference to the removed element
     * @throws NoSuchElementException if no box is found with the provided inventory number in the list.
     */
    public Box removeBox(int inventoryNumber) {
        if (size == 0) {// When the list is empty to find the specify box, throw Exception
            throw new NoSuchElementException("Error to remove box. Box #" + inventoryNumber + " not found!");
        }else {
            int index;
            //traverse the list starting from index 0 to determine the existence of the box with that inventoryNumber
            for (index = 0; index < size; index++) {

                // if the specify box in the list, terminated the for loop
                if (inventoryNumber == this.get(index).getInventoryNumber()) {
                    break;
                }
            }

            // if the box with that inventoryNumber is not find in the list, throw Exception
            if (index == size) {
                throw new NoSuchElementException("Error to remove box. Box #" + inventoryNumber + " not found!");
            }
        }

        LinkedBox prevNode = head;
        LinkedBox node = prevNode.getNext();
        int position = 0;
        //traverse the list in limit times to get a nodes and its previous node to find the specify box
        for (int i = 0; i < size; i++) {

            //if previous node is the specify box, set position to 0 and terminated the loop.
            if (prevNode.getBox().getInventoryNumber() == inventoryNumber) {
                position = 0;
                break;
            }
            //if previous node is the specify box, position plus 1 and terminated the loop
            else if (node.getBox().getInventoryNumber() == inventoryNumber) {
                position++;
                break;
            }

            //if neither previous node or node is not the specify box, continue the loop
            position++;
            node = node.getNext();//assign node's next node to node.
            prevNode = prevNode.getNext();//assign previous node's next node to prevNode.
        }

        LinkedBox remove = null;
        if (size == 1) {// if the list only has one box
            if (position == 0) {// if position is zero
                remove = prevNode;//store previous node in remove
                clear();// clear all element in the list
            }
        }
        else {//if the list has more than one box
            if (position == 0) {
                remove = prevNode;//store previous node in remove
                head = prevNode.getNext();//add previous node's next node at the head of this list
            }
            else if (position == size - 1) {
                prevNode.setNext(null);//set previous node link to null
                remove = node;//store node in remove
                tail = prevNode;//add previous node at the tail of this list
            }
            else {
                prevNode.setNext(node.getNext());//set previous node link to next node
                remove = node;//store node in remove
            }

            //decrement the specify remove box numbers base on its color
            if (remove.getBox().getColor().equals(Color.BLUE)) {
                blueCount--;
            }
            else if (remove.getBox().getColor().equals(Color.BROWN)) {
                brownCount--;
            }
            else if (remove.getBox().getColor().equals(Color.YELLOW)) {
                yellowCount--;
            }

            size--;
        }

        return remove.getBox();
    }

    /**
     * This method removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
        yellowCount = 0;
        blueCount = 0;
        brownCount = 0;
    }

    /**
     * This method returns the number of brown boxes stored in this list
     *
     * @return the brownCount
     */
    public int getBrownCount() {
        return brownCount;
    }

    /**
     * This method returns the number of yellow boxes stored in this list
     *
     * @return the brownCount
     */
    public int getYellowCount() {
        return yellowCount;
    }

    /**
     * This method returns the number of blue boxes stored in this list
     *
     * @return the brownCount
     */
    public int getBlueCount() {
        return blueCount;
    }

    /**
     * This method returns a String representation of the contents of this list
     *
     * @return a String representation of the content of this list.
     * If this list is empty, an empty String ("") will be returned.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }

        String temp = "";
        for (int i = 0; i < size; i++) {
            temp += this.get(i).toString() + " -> ";
        }

        return temp + "END";
    }
}
