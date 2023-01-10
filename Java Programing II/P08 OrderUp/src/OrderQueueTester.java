//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Order Queue Tester
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
 * This class test methods that defined in the OrderIterator class, OrderQueue class and
 * LinkedOrder class.
 *
 */
public class OrderQueueTester {

    /**
     * Checks for the methods correctness in LinkedOrders.java and OrderIterator.java
     *
     * @return true if the expected behavior is satisfied and false otherwise.
     */
    public static boolean testOrderIterator() {
        LinkedOrder linked1 = new LinkedOrder(new Order("fries", 2));
        LinkedOrder linked2 = new LinkedOrder(new Order("shake", 1));
        LinkedOrder linked3 = new LinkedOrder(new Order("burger", 3));
        linked1.setNext(linked2);
        linked1.getNext().setNext(linked3);
        OrderIterator iterator = new OrderIterator(linked1);
        //test hasNext()
        if(!iterator.hasNext()) {
            System.out.println("Problem detected in OrderIterator.java");
        }
        //test next()
        try {
            if (linked1.getOrder() != iterator.next()){//No Exception expected to be thrown
                System.out.println("Problem detected: methods in OrderIterator.java works failed");
                return false;
            }
        }catch (NoSuchElementException e){
            System.out.println("Problem detected: No Exception expected to be thrown");
            return false;
        }
        //test next()
        try {
            if (linked2.getOrder() != iterator.next()){//No Exception expected to be thrown
                System.out.println("Problem detected: methods in OrderIterator.java works failed");
                return false;
            }
        }catch (NoSuchElementException e){
            System.out.println("Problem detected: No Exception expected to be thrown");
            return false;
        }
        //test next()
        try {
            if (linked3.getOrder() != iterator.next()){//No Exception expected to be thrown
                System.out.println("Problem detected: methods in OrderIterator.java works failed");
                return false;
            }
        }catch (NoSuchElementException e){
            System.out.println("Problem detected: No Exception expected to be thrown");
            return false;
        }
        //test next()
        try {
            iterator.next();//NoSuchElementException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        }catch (NoSuchElementException e){
            System.out.println("Successfully caught: call next() when current contains nothing");
        }

        return true;// test passed without errors
    }

    /**
     * Checks for the correctness of the OrderQueue.enqueue()
     *
     * @return true if the expected behavior is satisfied and false otherwise.
     */
    public static boolean testEnqueue() {
        Order.resetIDGenerator();

        //Check enqueue by adding one element
        OrderQueue oq1 = new OrderQueue();
        Order firstAdd = new Order("fries", 2);
        oq1.enqueue(firstAdd);
        if (!(oq1.peek().equals(firstAdd))) {
            System.out.println("Problem detected: The enqueue() works failed");
            return false;
        }

        //Check enqueue by adding elements
        oq1.enqueue(new Order("shake", 1));
        oq1.enqueue(new Order("burger", 3));
        String expect = "1001: fries (2) -> 1002: shake (1) -> 1003: burger (3) -> END";
        if (oq1.size() != 3 || !oq1.toString().equals(expect) || !(oq1.peek().equals(firstAdd))) {
            System.out.println("Problem detected: The enqueue() works failed");
            return false;
        }

        return true;// test passed without errors
    }

    /**
     * Checks for the correctness of the OrderQueue.dequeue()
     *
     * @return true if the expected behavior is satisfied and false otherwise.
     */
    public static boolean testDequeue() {
        Order.resetIDGenerator();
        String expect = "";

        //Case 2: When queue contain nothing
        OrderQueue o4 = new OrderQueue();
        try {
            o4.dequeue();//NoSuchElementException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        }catch (NoSuchElementException e){
            System.out.println("Successfully caught: call dequeue() when queue is empty");
        }

        //Case 2: When queue only contain one item
        OrderQueue o3 = new OrderQueue();
        Order order2 = new Order("fries", 2);
        o3.enqueue(order2);
        o3.dequeue();// remove firstAdd(fries)
        expect = "";
        if (!o3.toString().equals(expect) || o3.size() != 0) {
            System.out.println("Problem detected: The enqueue() works failed");
            return false;
        }

        Order.resetIDGenerator();

        //Case 3: When queue has more than one items
        OrderQueue o2 = new OrderQueue();
        Order firstAdd = new Order("fries", 2);
        Order secondAdd = new Order("shake", 1);
        Order thirdAdd = new Order("burger", 3);
        o2.enqueue(firstAdd);
        o2.enqueue(secondAdd);
        o2.enqueue(thirdAdd);
        o2.dequeue();// remove firstAdd(fries)
        expect = "1002: shake (1) -> 1003: burger (3) -> END";
        if (!(o2.peek().equals(secondAdd)) || !o2.toString().equals(expect) || o2.size() != 2) {
            System.out.println("Problem detected: The enqueue() works failed");
            return false;
        }
        o2.dequeue();//remove second time(shake)
        expect = "1003: burger (3) -> END";
        if (!(o2.peek().equals(thirdAdd)) || !o2.toString().equals(expect) || o2.size() != 1) {
            System.out.println("Problem detected: The enqueue() works failed");
            return false;
        }
        o2.dequeue();//remove third time(burger)
        expect = "";
        if (!o2.toString().equals(expect) || o2.size() != 0) {
            System.out.println("Problem detected: The enqueue() works failed");
            return false;
        }
        try {//Test peak() when queue is empty
            o2.peek();//NoSuchElementException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        }catch (NoSuchElementException e){
            System.out.println("Successfully caught: call peak() when queue is empty");
        }

        return true;// test passed without errors
    }

    /**
     * Checks for the correctness of the OrderQueue.peek()
     *
     * @return true if the expected behavior is satisfied and false otherwise.
     */
    public static boolean testPeek() {
        Order.resetIDGenerator();

        //Case 1: Test peek() when queue is empty
        OrderQueue o5 = new OrderQueue();
        try {
            o5.peek();//NoSuchElementException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        }catch (NoSuchElementException e){
            System.out.println("Successfully caught: call peak() when queue is empty");
        }

        //Case 2: Test peek() when queue contains items
        OrderQueue o6 = new OrderQueue();
        Order fries = new Order("fries", 2);
        Order shake = new Order("shake", 1);
        Order burger = new Order("burger", 3);
        o6.enqueue(fries);//add fries
        if (!(o6.peek().equals(fries))) {
            System.out.println("Problem detected: The peek() works failed");
            return false;
        }
        o6.dequeue();// remove fries
        o6.enqueue(shake);//add shake
        if (!(o6.peek().equals(shake))) {
            System.out.println("Problem detected: The peek() works failed");
            return false;
        }
        o6.dequeue();// remove shake
        o6.enqueue(burger);//add burger
        if (!(o6.peek().equals(burger))) {
            System.out.println("Problem detected: The peek() works failed");
            return false;
        }

        return true;// test passed without errors
    }

    /**
     * A test suite method to run all your test methods
     *
     * @return returns true if and only if all test methods succeed; false otherwise
     */
    public static boolean runAllTests(){
        boolean error = true;

        if (!testOrderIterator()) {
            System.out.println("Detect errors in testOrderIterator() method");
             error = false;
        }
        else if (!testEnqueue()) {
            System.out.println("Detect errors in testEnqueue() method");
            error = false;
        }
        else if (!testDequeue()) {
            System.out.println("Detect errors in testDequeue() method");
            error = false;
        }
        else if (!testPeek()) {
            System.out.println("Detect errors in testPeek() method");
            error = false;
        }

        return error; //test passed without errors
    }

    /**
     * This main method is to call runAllTests() method
     *
     * @param args unused
     */
    public static void main(String[] args){
        if (runAllTests()) {
            System.out.println("\n******\nAll tests passed --> Program Terminate");
        }
        else {
            System.out.println("\n******\nTests failed --> Program Terminate");
        }
    }
}
