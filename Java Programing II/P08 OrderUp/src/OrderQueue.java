//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Order Queue
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the QueueADT and Iterable using a Queue
 *
 */
public class OrderQueue implements QueueADT<Order>, java.lang.Iterable<Order> {
    private LinkedOrder front;//a reference to the LinkedOrder at the front of the queue
    private LinkedOrder back;//a reference to the LinkedOrder at the back of the queue
    private int size;//the number of Orders currently in the queue

    /**
     * Creates a new OrderIterator beginning with the current value of front
     *
     * @return a new OrderIterator
     */
    @Override
    public Iterator<Order> iterator() {
        OrderIterator oi = new OrderIterator(this.front);

        return oi;
    }

    /**
     * Adds a new LinkedOrder containing newElement to the back of the queue
     *
     * @param newElement the element to be added to the rear of the queue.
     */
    @Override
    public void enqueue(Order newElement) {
        LinkedOrder newVal = new LinkedOrder(newElement);

        if (isEmpty()) {
            this.front = newVal;//if the queue is empty, assign newElement to front
        }
        else {
            this.back.setNext(newVal);//if the queue is not empty, set back link to newElement
        }
        this.back = newVal;//add newElement to the back

        this.size++;//size plus 1 since new element added
    }

    /**
     * Removes the next LinkedOrder from the front of the queue and returns its Order
     *
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public Order dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("\nWARNING: the queue is empty cannot dequeue");
        }

        Order result = front.getOrder();//store the value are going to remove
        if (this.size == 1) {
            //if queue only contain one element, then set front and back to null.
            this.front = null;
            this.back = null;
        }
        else {
            //if queue contain more than one elements, then set front next element as a new front
            this.front = this.front.getNext();
        }
        this.size--;//size minus 1 since an element removed

        return result;
    }

    /**
     * Returns the Order from the LinkedOrder at the front of the queue without removing
     * theLinkedOrder from the queue
     *
     * @return the front item in the queue.
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public Order peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("\nWARNING: the queue is empty which cannot peek");
        }

        return this.front.getOrder();
    }

    /**
     * This method is to check this queue contains elements or not.
     *
     * @return true if and only if the queue is empty
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Creates and returns a String representation of this OrderQueue using an enhanced-for loop.
     * <p>
     * For example, a queue with three Orders might look like this:
     * 1001: fries (2) -> 1002: shake (1) -> 1003: burger (3) -> END
     *
     * @return a String representation of this OrderQueue
     */
    @Override
    public String toString() {
        if (this.size == 0) {
            return "";
        }

        String qString = "";
        for (Order o : this) {
            qString += o.toString();
            qString += " -> ";
        }
        qString += "END";

        return qString;
    }

    /**
     * To is method is to get the number of orders in queue
     *
     * @return the number of orders in the queue
     */
    public int size() {
        return this.size;
    }
}
