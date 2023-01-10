//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: LinkedBox
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


/**
 * This class is models a linked box node which can be used to create a singly linked list of boxes.
 *
 */
public class LinkedBox {
  private Box box;// It represents the data carried by this linked box node.
  private LinkedBox next;// It represents the link to the next linkedBox.

  /**
   * Creates a new LinkedBox with a specified box and null as next field
   *
   * @param box the box carried by this linked box
   */
  public LinkedBox(Box box) {
    this.box = box;
  }

  /**
   * Creates a new LinkedBox node with given box and next fields
   *
   * @param box  the box carried by this linked box
   * @param next reference to the next linked box in the list
   */
  public LinkedBox(Box box, LinkedBox next) {
    this.box = box;
    this.next = next;
  }

  /**
   * This method sets the link to the next linked box node
   *
   * @param next the next to set
   */
  public void setNext(LinkedBox next) {
    this.next = next;
  }

  /**
   * This method returns the next linked box node.
   *
   * @return the next
   */
  public LinkedBox getNext() {
    return this.next;
  }

  /**
   * This method returns the data field box
   *
   * @return the box
   */
  public Box getBox() {
    return this.box;
  }

  /**
   * This method returns a String representation of this Linked box.
   *
   * @return a String representation of this Linked MegaBlock object
   */
  @Override
  public String toString() {
    if (next != null) {
      return box.toString() + " -> ";
    }

    return box.toString() + " -> END";
  }
}
