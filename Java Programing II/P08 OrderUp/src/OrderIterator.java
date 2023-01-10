//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Order Iterator
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
 * Implementation of the Iterable using a Order iterator
 *
 */
public class OrderIterator implements Iterator<Order> {
    private LinkedOrder current;//the LinkedOrder that it’s currently using

    /**
     * A constructor, initializes current to the provided starting LinkedOrder.
     *
     * @param start the object of LinkedOrder
     */
    public OrderIterator(LinkedOrder start){
        this.current = start;
    }

    /**
     * This method is to check whether the iteration has more elements or not.
     *
     * @return true if and only if the iteration has more orders
     */
    @Override
    public boolean hasNext() {
        if (this.current != null) {
            return true;
        }
        return false;
    }

    /**
     * This method is to check the next element in the iteration.
     *
     * @return the next Order and updates the current field appropriately.
     * @throws NoSuchElementException if the iteration does not have more orders to return
     */
    @Override
    public Order next() throws NoSuchElementException{
        if (!hasNext()) {
            throw new NoSuchElementException("\nWARNING: the iteration does not have more orders");
        }

        Order val = this.current.getOrder();//store current value in a temp variable
        this.current = this.current.getNext();//assign next value to current
        return val;//return stored current value
    }
}
