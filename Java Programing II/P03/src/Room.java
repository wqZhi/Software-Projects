//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Room
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

import java.util.ArrayList;

/**
 * This class is to implement room object.
 */
public class Room {
    private static ArrayList<String> names = new ArrayList<String>(); // containing the currently-used
    // name identifiers across all
    // instances of Room objects.
    private String name; // a String representing the name of the Room, which must be unique
    private Person[] occupants; // a perfect-size array of Persons currently in the Room
    private int currentOccupancy; // an int counting the current number of Persons in the Room

    /**
     * This method add value from ArrayList names to an array of Strings.
     *
     * @return the current list of Room names as an array of Strings
     */
    public static String[] getNames() {
        String[] name = new String[names.size()];

        for (int i = 0; i < names.size(); i++) { // add Arraylist value to an array.
            name[i] = names.get(i);
        }



        return name;
    }

    /**
     * A two-argument constructor, which initializes the instance variables for the object
     * <p>
     * If the provided capacity is 0 or less, or if the name is already in use by another Room object,
     * this constructor should throw an IllegalArgumentException with a descriptive error message.
     * If the Room is created successfully, add its name to the Room.names ArrayList.
     *
     * @param name     the name of the Room object
     * @param capacity is the maximum number of Persons who can occupy the room without social
     *                 distancing enforced
     */
    public Room(String name, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Error! The capacity can not be zero or less");
        } else {
            for (int i = 0; i < names.size(); i++) {
                if (name.equals(names.get(i))) {
                    throw new IllegalArgumentException("Error! Name already used by another Room");
                }
            }
        }

        this.name = name;
        names.add(name);
        this.occupants = new Person[capacity];
    }

    /**
     * To get the room name.
     *
     * @return the name of the Room
     */
    public String getName() {
        return this.name;
    }

    /**
     * To get current number of people in the Room
     *
     * @return the current number of people in the Room
     */
    public int getOccupancy() {
        return this.currentOccupancy;
    }

    /**
     * To get allowed number of people in the Room under COVID situation
     *
     * @return the number of people allowed in the Room under COVID protocols
     */
    public int getCOVIDCapacity() {
        return (this.occupants.length + 1) / 2;
    }

    /**
     * To get allowed number of people in the Room under normal situation
     *
     * @return the number of people allowed in the Room under normal conditions
     */
    public int getCapacity() {
        return this.occupants.length;
    }

    /**
     * This method is to check whether the person is in the Room or not.
     *
     * @param p an object of Person
     * @return true if and only if the provided Person is present in the Room’s occupants array
     */
    public boolean contains(Person p) {
        for (int i = 0; i < occupants.length; i = i + 2) {
            if (p.equals(occupants[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method is to add the Person to the Room.
     * <p>
     * If the currentOccupancy is equal to the COVID capacity, it won't check
     * the Person into the Room, and return false.
     * If the Person passed its name is null, or he/she already in the room,
     * then throw an IllegalArgumentException with a descriptive error message.
     * If the Person is added successfully, then increment currentOccupancy
     * and toggle their isWaiting field before return true.
     * Only add Person to the first available even-indexed element.
     *
     * @param in an object of Person.
     * @return true if and only if the providedPerson was successfully added to the room.
     */
    public boolean checkIn(Person in) {
        if (in == null) {
            throw new IllegalArgumentException("Error! There is a null value");
        } else if (contains(in)) {
            throw new IllegalArgumentException("Error! The person is already in the room");
        }

        if (this.currentOccupancy >= getCOVIDCapacity()) {
            return false;
        } else {
            for (int i = 0; i < occupants.length; i = i + 2) {
                // !(in.equals(occupants[i])) && (occupants[i] == null)
                if (occupants[i] == null) {
                    occupants[i] = in;
                    break;
                }
            }
            this.currentOccupancy++;
            in.toggleWaiting();
            return true;
        }
    }

    /**
     * This method is to remove the Person from the room.
     * <p>
     * If the Person passed as input to this method is null, throw an IllegalArgumentException
     * with a descriptive error message.
     * If the provided Person was NOT present in the Room, the occupants
     * array and currentOccupancy won't change and the method return false.
     * If the Person is located in the Room, their isWaiting value will
     * toggled to true, the currentOccupancy of the Room will decremented,
     * and their index in the occupants array will set to null before returning true.
     *
     * @param out an object of Person.
     * @return true if and only if the provided Person was successfully removed from the Room.
     */
    public boolean checkOut(Person out) {
        if (out == null) {
            throw new IllegalArgumentException("Error! There is a null value");
        }

        for (int i = 0; i < occupants.length; i = i + 2) {
            if (out.equals(occupants[i])) {
                out.toggleWaiting();
                occupants[i] = null;
                this.currentOccupancy--;
                return true;
            }
        }

        return false;
    }

    /**
     * This method returns a String representation of the Object.
     *
     * @return a String representation of this Room and its occupants
     */
    public String toString() {
        String result = this.name + "\n===\n";

        for (int i = 0; i < occupants.length; i++) {
            if (occupants[i] == null) {
                result += "-\n";
            } else {
                result += occupants[i].getName() + "\n";
            }
        }

        return result;
    }

}

