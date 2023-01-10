//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Order Priority Queue Tester
// Course: CS 300 Fall 2020
//
// Author: Weiqian Zhi
// Email: wzhi3@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////


import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * OrderPriorityQueue.
 * <p>
 * You MAY add additional public static boolean methods to this class if you like, and any private
 * static helper methods you need.
 */
public class OrderPriorityQueueTester {

    /**
     * Checks the correctness of the isEmpty method of OrderPriorityQueue.
     * <p>
     * You should, at least:
     * (1) create a new OrderPriorityQueue and verify that it is empty
     * (2) add a new Order to the queue and verify that it is NOT empty
     * (3) remove that Order from the queue and verify that it is empty again
     *
     * @return true if and only if ALL tests pass
     */
    public static boolean testIsEmpty() {
        Order.resetIDGenerator();

        //1, then go write the constructor and isEmpty methods in your OrderPriorityQueue class so that they pass the tests
        try {
            OrderPriorityQueue o1 = new OrderPriorityQueue(5);
            if (!o1.isEmpty()) {
                System.out.println("Error! isEmpty() works failed in OrderPriorityQueue class");
                return false;
            }

            //scenario 2, then go write enough of insert() to pass the tests
            o1.insert(new Order("Egg", 3));
            if (o1.isEmpty() || o1.size() != 1) {
                System.out.println("Error! isEmpty() works failed in OrderPriorityQueue class");
                return false;
            }

            //scenario 3, then go write enough of remove() to pass the tests
            o1.removeBest();
            if (!o1.isEmpty() || o1.size() != 0) {
                System.out.println("Error! isEmpty() works failed in OrderPriorityQueue class");
                return false;
            }
        } catch (Exception e) { // an unexpected exception was thrown
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Checks the correctness of the insert method of OrderPriorityQueue.
     * <p>
     * You should, at least:
     * (1) create a new OrderPriorityQueue and add a single order with a large prepTime to it
     * (2) use the OrderPriorityQueue toString method to verify that the queue's internal structure
     * is a valid heap
     * (3) add at least three more orders with DECREASING prepTimes to the queue and repeat step 2.
     *
     * @return true if and only if ALL tests pass
     */
    public static boolean testInsertBasic() {
        Order.resetIDGenerator();
        String expect;

        try {
            //create a new OrderPriorityQueue and add a single order with a large prepTime to it
            OrderPriorityQueue o2 = new OrderPriorityQueue(5);
            o2.insert(new Order("Lobster", 20));
            expect = "1001(20)";
            if (!o2.toString().equals(expect)) {
                System.out.println("Error! insert() works failed in OrderPriorityQueue class");
                return false;
            }

            Order.resetIDGenerator();

            //use the OrderPriorityQueue toString method to verify that the queue's internal structure is a valid heap
            OrderPriorityQueue o3 = new OrderPriorityQueue(5);
            o3.insert(new Order("Lobster", 20));
            o3.insert(new Order("Salad", 10));
            o3.insert(new Order("Egg", 12));
            o3.insert(new Order("Sandwich", 2));
            o3.insert(new Order("Reuben", 19));
            expect = "1001(20), 1005(19), 1003(12), 1004(2), 1002(10)";
            if (!o3.toString().equals(expect)) {
                System.out.println("Error! insert() works failed in OrderPriorityQueue class");
                return false;
            }

            //add at least three more orders with DECREASING prepTimes to the queue and repeat step 2.
            o3.insert(new Order("Beef", 18));
            o3.insert(new Order("Chicken", 17));
            o3.insert(new Order("Fries", 16));
            expect =
                "1001(20), 1005(19), 1006(18), 1008(16), 1002(10), 1003(12), 1007(17), 1004(2)";
            if (!o3.toString().equals(expect)) {
                System.out.println("Error! insert() works failed in OrderPriorityQueue class");
                return false;
            }
        } catch (Exception e) { // an unexpected exception was thrown
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Checks the correctness of the insert method of OrderPriorityQueue.
     * <p>
     * You should, at least:
     * (1) create an array of at least four Orders that represents a valid heap
     * (2) add a fifth order at the next available index that is NOT in a valid heap position
     * (3) pass this array to OrderPriorityQueue.percolateUp()
     * (4) verify that the resulting array is a valid heap
     *
     * @return true if and only if ALL tests pass
     */
    public static boolean testPercolateUp() {
        Order.resetIDGenerator();

        try {
            Order[] queue = new Order[5];
            queue[0] = new Order("Beef", 20);
            queue[1] = new Order("Chicken", 18);
            queue[2] = new Order("Fries", 17);
            queue[3] = new Order("Burger", 16);
            queue[4] = new Order("Egg", 21);
            OrderPriorityQueue.percolateUp(queue, 4);
            if (queue[0].getPrepTime() != 21 || queue[1].getPrepTime() != 20
                || queue[2].getPrepTime() != 17 || queue[3].getPrepTime() != 16
                || queue[4].getPrepTime() != 18) {
                System.out.println("Error! percolateUp() works failed in OrderPriorityQueue class");
                return false;
            }
        } catch (Exception e) { // an unexpected exception was thrown
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Checks the correctness of the insert method of OrderPriorityQueue.
     * <p>
     * You should, at least:
     * (1) create a new OrderPriorityQueue with at least 6 orders of varying prepTimes, adding them
     * to the queue OUT of order
     * (2) use the OrderPriorityQueue toString method to verify that the queue's internal structure
     * is a valid heap
     *
     * @return true if and only if ALL tests pass
     */
    public static boolean testInsertAdvanced() {
        Order.resetIDGenerator();

        try {
            OrderPriorityQueue o4 = new OrderPriorityQueue(6);
            o4.insert(new Order("Beef", 20));
            o4.insert(new Order("Chicken", 35));
            o4.insert(new Order("Fries", 7));
            o4.insert(new Order("Burger", 2));
            o4.insert(new Order("Egg", 29));
            o4.insert(new Order("Sandwich", 21));
            String expect = "1002(35), 1005(29), 1006(21), 1004(2), 1001(20), 1003(7)";
            if (!o4.toString().equals(expect)) {
                System.out.println("Error! insert() works failed in OrderPriorityQueue class");
                return false;
            }
        } catch (Exception e) { // an unexpected exception was thrown
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Checks the correctness of the insert method of OrderPriorityQueue.
     * <p>
     * You should, at least:
     * (1) create an array of at least five Orders where the Order at index 0 is NOT in valid heap
     * position
     * (2) pass this array to OrderPriorityQueue.percolateDown()
     * (3) verify that the resulting array is a valid heap
     *
     * @return true if and only if ALL tests pass
     */
    public static boolean testPercolateDown() {
        Order.resetIDGenerator();

        try {
            Order[] queue = new Order[10];
            int size = 0;
            queue[0] = new Order("Beef", 2);
            queue[1] = new Order("Chicken", 18);
            queue[2] = new Order("Fries", 17);
            queue[3] = new Order("Burger", 16);
            queue[4] = new Order("Egg", 6);
            for (int i = 0; i < queue.length; i++) {
                if (queue[i] != null) {
                    size++;
                }
            }
            OrderPriorityQueue.percolateDown(queue, 0, size);
            if (queue[0].getPrepTime() != 18 || queue[1].getPrepTime() != 16
                || queue[2].getPrepTime() != 17 || queue[3].getPrepTime() != 2
                || queue[4].getPrepTime() != 6) {
                System.out
                    .println("Error! percolateDown() works failed in OrderPriorityQueue class");
                return false;
            }
        } catch (Exception e) { // an unexpected exception was thrown
            e.printStackTrace();
            return false;
        }

        return true; // included to prevent compiler errors
    }

    /**
     * Checks the correctness of the removeBest and peekBest methods of OrderPriorityQueue.
     * <p>
     * You should, at least:
     * (1) create a new OrderPriorityQueue with at least 6 orders of varying prepTimes, adding them
     * to the queue in whatever order you like
     * (2) remove all but one of the orders, verifying that each order has a SHORTER prepTime than
     * the previously-removed order
     * (3) peek to see that the only order left is the one with the SHORTEST prepTime
     * (4) check isEmpty to verify that the queue has NOT been emptied
     * (5) remove the last order and check isEmpty to verify that the queue HAS been emptied
     *
     * @return true if and only if ALL tests pass
     */
    public static boolean testPeekRemove() {
        Order.resetIDGenerator();
        try {
            Order result;
            String expect;

            OrderPriorityQueue o5 = new OrderPriorityQueue(6);
            o5.insert(new Order("Beef", 20));
            o5.insert(new Order("Chicken", 35));
            o5.insert(new Order("Fries", 7));
            o5.insert(new Order("Burger", 2));
            o5.insert(new Order("Egg", 29));
            o5.insert(new Order("Sandwich", 21));

            result = o5.removeBest();
            expect = "1005(29), 1001(20), 1006(21), 1004(2), 1003(7)";
            if (result.getPrepTime() != 35 || !o5.toString().equals(expect)) {
                System.out.println("Error! removeBest() works failed in OrderPriorityQueue class");
                return false;
            }

            result = o5.removeBest();
            expect = "1006(21), 1001(20), 1003(7), 1004(2)";
            if (result.getPrepTime() != 29 || !o5.toString().equals(expect)) {
                System.out.println("Error! removeBest() works failed in OrderPriorityQueue class");
                return false;
            }

            result = o5.removeBest();
            expect = "1001(20), 1004(2), 1003(7)";
            if (result.getPrepTime() != 21 || !o5.toString().equals(expect)) {
                System.out.println("Error! removeBest() works failed in OrderPriorityQueue class");
                return false;
            }

            result = o5.removeBest();
            expect = "1003(7), 1004(2)";
            if (result.getPrepTime() != 20 || !o5.toString().equals(expect)) {
                System.out.println("Error! removeBest() works failed in OrderPriorityQueue class");
                return false;
            }

            result = o5.removeBest();
            expect = "1004(2)";
            if (result.getPrepTime() != 7 || !o5.toString().equals(expect)) {
                System.out.println("Error! removeBest() works failed in OrderPriorityQueue class");
                return false;
            }

            result = o5.peekBest();
            if (result.getPrepTime() != 2) {
                System.out.println("Error! removeBest() works failed in OrderPriorityQueue class");
                return false;
            }

            if (o5.isEmpty()) {
                System.out.println("Error! the queue should not be emptied");
                return false;
            }

            result = o5.removeBest();
            expect = "";
            if (result.getPrepTime() != 2 || !o5.toString().equals(expect)) {
                System.out.println("Error! removeBest() works failed in OrderPriorityQueue class");
                return false;
            }

            if (!o5.isEmpty()) {
                System.out.println("Error! the queue should be been emptied");
                return false;
            }

        } catch (Exception e) { // an unexpected exception was thrown
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Checks the correctness of the removeBest and peekBest methods, as well as the constructor of
     * the OrderPriorityQueue class for erroneous inputs and/or states
     * <p>
     * You should, at least:
     * (1) create a new OrderPriorityQueue with an invalid capacity argument, and verify that the
     * correct exception is thrown
     * (2) call peekBest() on an OrderPriorityQueue with an invalid state for peeking, and verify that
     * the correct exception is thrown
     * (3) call removeBest() on an OrderPriorityQueue with an invalid state for removing, and verify
     * that the correct exception is thrown
     *
     * @return true if and only if ALL tests pass
     */
    public static boolean testErrors() {
        Order.resetIDGenerator();

        try {
            OrderPriorityQueue o6;

            try {
                o6 = new OrderPriorityQueue(-12);// IllegalArgumentException expected to be thrown
                System.out.println("Problem detected: No exception was thrown");
                return false;
            } catch (IllegalArgumentException e) {
                if (e.getMessage() == null
                    || e.getMessage().length() == 0) { // exception with no error message
                    System.out.println(
                        "Error! OrderPriorityQueue constructor thrown exception in an unexpected way");
                    return false;
                }
            }

            try {
                o6 = new OrderPriorityQueue(10);
                o6.peekBest();// NoSuchElementException expected to be thrown
                System.out.println("Problem detected: No exception was thrown");
                return false;
            } catch (NoSuchElementException e) {
                if (e.getMessage() == null
                    || e.getMessage().length() == 0) { // exception with no error message
                    System.out.println("Error! peekBest() thrown exception in an unexpected way");
                    return false;
                }
            }

            try {
                o6 = new OrderPriorityQueue(10);
                o6.removeBest();// NoSuchElementException expected to be thrown
                System.out.println("Problem detected: No exception was thrown");
                return false;
            } catch (NoSuchElementException e) {
                if (e.getMessage() == null
                    || e.getMessage().length() == 0) { // exception with no error message
                    System.out.println("Error! removeBest() thrown exception in an unexpected way");
                    return false;
                }
            }
        } catch (Exception e) { // an unexpected exception was thrown
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Calls the test methods individually and displays their output
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("isEmpty: " + testIsEmpty());
        System.out.println("insert basic: " + testInsertBasic());
        System.out.println("percolate UP: " + testPercolateUp());
        System.out.println("insert advanced: " + testInsertAdvanced());
        System.out.println("percolate DOWN: " + testPercolateDown());
        System.out.println("peek/remove valid: " + testPeekRemove());
        System.out.println("error: " + testErrors());
    }

}
