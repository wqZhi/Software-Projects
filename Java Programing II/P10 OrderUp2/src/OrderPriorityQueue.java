//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Order Priority Queue
// Course: CS 300 Fall 2020
//
// Author: Weiqian Zhi
// Email: wzhi3@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A max-heap implementation of a priority queue for Orders, where the Order with the LONGEST prep 
 * time is returned first instead of the strict first-in-first-out queue as in P08.
 *
 */
public class OrderPriorityQueue implements PriorityQueueADT<Order>{

  // Data fields; do not modify
  private Order[] queueHeap;//array-based max heap storing the data
  private int size;//size of this queue
  
  /**
   * Constructs a PriorityQueue for Orders with the given capacity
   * 
   * @param capacity the initial capacity for the queue
   * @throws IllegalArgumentException if the given capacity is 0 or negative
   */
  public OrderPriorityQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Error! the initial capacity for the queue cannot be zero or negative");
    }
    this.queueHeap = new Order[capacity];
    this.size = 0;
  }
  
  /**
   * Inserts a new Order into the queue in the appropriate position using a heap's add logic.
   * 
   * @param newOrder the Order to be added to the queue
   */
  @Override
  public void insert(Order newOrder) {
    if (isEmpty()) {//If the queue is empty
      queueHeap[0] = newOrder;// insert the new order at the root of the heap
    }
    else if (queueHeap.length == size){//If the queue is FULL
      //copy all elements of the current heap over
      queueHeap = Arrays.copyOf(queueHeap, size * 2);
      queueHeap[size] = newOrder;
      percolateUp(queueHeap, size - 1);
    }
    else{
      queueHeap[size] = newOrder;//add the newOrder to the end of the heap
      //percolate up to ensure a valid heap, where the Order with the LONGEST prep time is at the root of the heap
      percolateUp(queueHeap, size - 1);
    }

    this.size++;
  }
  
  /**
   * A utility method to percolate Order values UP through the heap
   * 
   * @param heap an array containing the Order values to be percolated into a valid heap
   * @param orderIndex the index of the Order to be percolated up
   */
  protected static void percolateUp(Order[] heap, int orderIndex) {
    int parentIndex;

    while (orderIndex > 0) {
      parentIndex = (orderIndex - 1) / 2;
      if (heap[orderIndex].compareTo(heap[parentIndex]) <= 0){
        return;
      }
      else {
        Order temp = heap[parentIndex];
        heap[parentIndex] = heap [orderIndex];
        heap [orderIndex] = temp;
        orderIndex = parentIndex;
      }
    }

  }
  
  /**
   * Return the Order with the longest prep time from the queue and adjust the queue accordingly
   * 
   * @return the Order with the current longest prep time from the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public Order removeBest() {
    if (isEmpty()) {//If the queue is empty, throw a NoSuchElementException
      throw new NoSuchElementException("Error! cannot implement removeBest() when queue is empty");
    }
    //Remove the root Order of the heap and re-structure the heap
    Order removed = queueHeap[0];
    queueHeap[0] = queueHeap[size - 1];
    queueHeap[size - 1] = null;
    this.size--;
    percolateDown(queueHeap, 0, this.size);

    return removed; // return the previous root
  }
  
  /**
   * A utility method to percolate Order values DOWN through the heap
   * 
   * @param heap an array containing the Order values to be percolated into a valid heap
   * @param orderIndex the index of the Order to be percolated down
   * @param size the number of initialized elements in the heap
   */
  protected static void percolateDown(Order[] heap, int orderIndex, int size) {
    int childIndex = 2 * orderIndex + 1;
    Order value = heap[orderIndex];
    Order maxValue;
    int maxIndex;

    while (childIndex < size) {
      // Find the max among the node and all the node's children
      maxValue = value;
      maxIndex = -1;
      for (int i = 0; i < 2 && i + childIndex < size; i++) {
        if (heap[i + childIndex].compareTo(maxValue)  > 0) {
          maxValue = heap[i + childIndex];
          maxIndex = i + childIndex;
        }
      }

      if (maxValue == value) {
        return;
      }
      else {
        Order temp = heap[maxIndex];
        heap[maxIndex] = heap [orderIndex];
        heap [orderIndex] = temp;
        orderIndex = maxIndex;
        childIndex = 2 * orderIndex + 1;
      }
    }
  }
  
  /**
   * Return the Order with the highest prep time from the queue without altering the queue
   * @return the Order with the current longest prep time from the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public Order peekBest() {
    if (isEmpty()) {//If the queue is empty, throw a NoSuchElementException
      throw new NoSuchElementException("Error! Unable to perform peek operation.");
    }
    else {//Return the Order with the longest prep time currently in the queue
      return this.queueHeap[0];
    }
  }
  
  /**
   * Returns true if the queue contains no Orders, false otherwise
   *
   * @return true if the queue contains no Orders, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }
  
  /**
   * Returns the number of elements currently in the queue
   *
   * @return the number of elements currently in the queue
   */
  public int size() {
    return this.size;
  }
  
  /**
   * Creates a String representation of this PriorityQueue. Do not modify this implementation; this
   * is the version that will be used by all provided OrderPriorityQueue implementations that your
   * tester code will be run against.
   * 
   * @return the String representation of this PriorityQueue, primarily for testing purposes
   */
  public String toString() {
    String toReturn = "";
    for (int i=0; i < this.size; i++) {
      toReturn += queueHeap[i].getID()+"("+queueHeap[i].getPrepTime()+")";
      if (i < this.size-1) toReturn += ", ";
    }
    return toReturn;
  }
  
}
