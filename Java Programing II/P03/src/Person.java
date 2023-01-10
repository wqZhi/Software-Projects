//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Person
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
 * This class is to implement person object.
 */
public class Person {
    private String name; // a String representing the Person’s name
    private boolean isWaiting; // a boolean has the value true if and only if the Person is NOT
    // currently in a Room

    /**
     * A one-argument constructor, which initializes the instance variables for the object
     *
     * @param name the name of the Person object
     */
    public Person(String name) {
        this.name = name;
        this.isWaiting = true;
    }

    /**
     * To get the person name.
     *
     * @return person's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * To get the waiting status.
     *
     * @return waiting status results, true or false.
     */
    public boolean isWaiting() {
        return this.isWaiting;
    }

    /**
     * Sets isWaiting to true if it is currently false, and to false if it is currently true
     */
    public void toggleWaiting() {
        if (this.isWaiting) {
            this.isWaiting = false;
        } else {
            this.isWaiting = true;
        }
    }

    /**
     * This method compares an object to other object.
     * <p>
     * Check a object's specified type. If a object is an instance of Person and if the name is
     * correct, then return ture. Otherwise, return false.
     *
     * @return true if an object's name is correct. false if a object is not an instance of Person or
     *         name is not right one.
     */
    public boolean equals(Object o) {
        if (o instanceof Person) {
            return this.name.equals(((Person) o).name);
        }
        return false;
    }

}

