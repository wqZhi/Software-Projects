//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Occupancy Tester
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
 * This class contains tester to test Person.java and Room.java
 */
public class OccupancyTester {
    public static boolean testPerson() {
        Person p1 = new Person("Mike");
        Person p2 = new Person("Bob");
        String expect = null;

        // Check constructor, getName().
        if (p1.getName().equals(p2.getName())) {
            System.out
                .println("Problem detected: two Person return name should not equal to each other.");
            return false;
        }

        // Check getName().
        expect = "Mike";
        if (!(p1.getName().equals(expect))) {
            System.out.println("Problem detected: Person 1's return name was not as expected.");
            return false;
        }
        expect = "Bob";
        if (!(p2.getName().equals(expect))) {
            System.out.println("Problem detected: Person 2's return name was not as expected");
            return false;
        }

        // Check isWaiting(), constructor.
        if (p1.isWaiting() != true) {
            System.out.println("Problem detected: the initial isWaiting should return true. "
                + "But it was not the case.");
            return false;
        }
        if (p2.isWaiting() != true) {
            System.out.println("Problem detected: the initial isWaiting should return true. "
                + "But it was not the case.");
            return false;
        }

        // Check c, isWaiting(), constructor.
        p1.toggleWaiting();
        if (p1.isWaiting() != false) {
            System.out.println("Problem detected: FAILED toggleWaiting(). The toggleWaiting() needs "
                + "flips the isWaiting value from true to false since it is currently true.");
            return false;
        }
        p2.toggleWaiting();
        if (p2.isWaiting() != false) {
            System.out.println("Problem detected: FAILED toggleWaiting(). The toggleWaiting() needs "
                + "flips the isWaiting value from true to false since it is currently true.");
            return false;
        }
        p2.toggleWaiting();// flip again
        if (p2.isWaiting() != true) {
            System.out.println("Problem detected: FAILED toggleWaiting(). Once flip p2's "
                + "isWaiting again, its result should change from false to true.");
            return false;
        }

        // Check equals(Object o)
        if (p1.equals(p2)) { // two Person instances with different names are NOT considered equal
            System.out.println("Problem detected: two Person with different names "
                + "should not considered equal.");
            return false;
        }
        Person p20 = new Person("Mike");
        if (!(p1.equals(p20))) { // two Person instances with the same name ARE considered equal.
            System.out.println("Problem detected: two Person with same name should be equal.");
            return false;
        }
        Room room5 = new Room("Art 100", 7);
        if ((p1.equals(room5))) { // Person and any other object should NOT be considered equal.
            System.out.println("Problem detected: a Person object should not be euqal"
                + " with a Room object.");
            return false;
        }

        return true;
    }

    public static boolean testRoomConstructor() {
        Room r1 = new Room("CS 200", 6);
        Room r2 = new Room("CS 300", 9);
        String expectMessage = null;

        // Check getName(), constructor
        if (r1.getName().equals(r2.getName())) {
            System.out.println("Problem detected: two different Room return name should not be same.");
            return false;
        }

        // Check when room name already used by another room.
        expectMessage = "Error! Name already used by another Room";
        try {
            Room r3 = new Room("CS 300", 9); // IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e) {
            String[] expect = {"Art 100", "CS 200", "CS 300"};
            String[] returnVal = Room.getNames();
            int count = 0;
            for (int i = 0; i < Room.getNames().length; i++) { // Check name in ArrayList names. And there
                // should not contain two CS 300.
                if (returnVal[i] == expect[i]) {
                    count++;
                }
            }
            if (!(count == expect.length)) {
                System.out.println("Problem detected: the names stored in ArrayList names "
                    + "are not as expected");
                return false;
            }
            if (!(e.getMessage().equals(expectMessage))) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println(e.getMessage());
        }

        // Check when capacity is <= 0.
        expectMessage = "Error! The capacity can not be zero or less";
        try {
            Room r4 = new Room("CS 500", -6); // IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e) {
            if (!(e.getMessage().equals(expectMessage))) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println(e.getMessage());
        }

        return true;
    }

    public static boolean testRoomAccessors() {
        Room r5 = new Room("CS 240", 6);
        Room r6 = new Room("CS 600", 7);

        // Check String[] getNames(), use above created rooms
        String[] expect = {"Art 100", "CS 200", "CS 300", "CS 240", "CS 600"};
        String[] returnVal = Room.getNames();
        int count = 0;
        for (int i = 0; i < returnVal.length; i++) {
            expect[i] = returnVal[i];
            count++;
        }
        if (count != expect.length) {
            System.out.println("Problem detected: FAILED String[] getNames(), doesn't work as expected");
            return false;
        }

        // Check getName()
        if (!(r5.getName().equals("CS 240"))) {
            System.out.println("Problem detected: FAILED getName(), doesn't work as expected");
            return false;
        }
        if (!(r6.getName().equals("CS 600"))) {
            System.out.println("Problem detected: FAILED getName(), doesn't work as expected");
            return false;
        }
        if (r5.getName().equals(r6.getName())) {
            System.out.println(
                "Problem detected: FAILED getName(), two Room return name are not suppose to be same.");
            return false;
        }

        // Check getCapacity(), using Room r5 & r6
        int expectCapacity = 6;
        if (r5.getCapacity() != expectCapacity) {
            System.out.println("Problem detected: FAILED getCapacity(), doesn't work as expect");
            return false;
        }
        expectCapacity = 7;
        if (r6.getCapacity() != expectCapacity) {
            System.out.println("Problem detected: FAILED getCapacity(), doesn't work as expect");
            return false;
        }

        // Check getCOVIDCapacity(), using Room r5 & r6
        int expectCOVIDCapacity = 3;
        if (r5.getCOVIDCapacity() != expectCOVIDCapacity) {
            System.out.println("Problem detected: FAILED getCOVIDCapacity(), doesn't work as expect");
            return false;
        }
        expectCOVIDCapacity = 4;
        if (r6.getCOVIDCapacity() != expectCOVIDCapacity) {
            System.out.println("Problem detected: FAILED getCOVIDCapacity(), doesn't work as expect");
            return false;
        }

        // Check getOccupancy(), using Room r5 "CS 240"
        Person p6 = new Person("James");
        Person p7 = new Person("Harper");
        Person p8 = new Person("Mason");
        r5.checkIn(p6);
        r5.checkIn(p7);
        r5.checkIn(p8);
        int expectOccupancy = 3;
        if (r5.getOccupancy() != expectOccupancy) {
            System.out.println("Problem detected: FAILED getOccupancy(), doesn't work as expect");
            return false;
        }

        // Check contains(Person p), using above elements.
        if (!(r5.contains(p8))) {
            System.out.println("Problem detected: FAILED contains(), doesn't work as expect");
            return false;
        }
        Person p9 = new Person("Evelyn");
        if ((r5.contains(p9))) {// r5 doesn't have person Evelyn
            System.out.println("Problem detected: FAILED contains(), doesn't work as expect");
            return false;
        }

        return true;
    }

    public static boolean testRoomCheckIn() {
        Room r7 = new Room("Econ 101", 6);
        Room r8 = new Room("CS 252", 9);
        String expectMessage = null;

        // Check when person name is null.
        expectMessage = "Error! There is a null value";
        Person p10 = null;
        try {
            r7.checkIn(p10); // IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e) {
            if (!(e.getMessage().equals(expectMessage))) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println(e.getMessage());
        }

        // Check when person already in the room.
        expectMessage = "Error! The person is already in the room";
        Person p11 = new Person("Jay");
        r8.checkIn(p11);
        try {
            r8.checkIn(p11); // IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e) {
            if (!(e.getMessage().equals(expectMessage))) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println(e.getMessage());
        }

        // Check add person in the room.
        Room r9 = new Room("Music 500", 6);
        Person p12 = new Person("Jakie");
        Person p13 = new Person("William");
        Person p14 = new Person("Joey");
        r9.checkIn(p12);
        r9.checkIn(p13);
        if (r9.getOccupancy() != 2) { // Check currentOccupancy++ function in checkIn() method
            System.out.println("Problem detected: FAILED getOccupancy(), doesn't work as expect");
            return false;
        }
        r9.checkIn(p14);
        String expect = "Music 500\n===\nJakie\n-\nWilliam\n-\nJoey\n-\n";
        if (!(r9.toString().equals(expect))) {
            System.out.println("Problem detected: FAILED checkIn(), doesn't work as expect");
            return false;
        }
        if (p12.isWaiting() || p13.isWaiting() || p14.isWaiting()) {// Check toggleWaiting() function in
            // checkIn() method
            System.out.println("Problem detected: FAILED toggleWaiting(), doesn't work as expect");
            return false;
        }

        return true;
    }

    public static boolean testRoomCheckOut() {
        Room r10 = new Room("Music 113", 9);
        String expectMessage = null;

        // Check when person name is null.
        expectMessage = "Error! There is a null value";
        Person p15 = null;
        try {
            r10.checkIn(p15); // IllegalArgumentException expected to be thrown
            System.out.println("Problem detected: No exception was thrown");
            return false;
        } catch (IllegalArgumentException e) {
            if (!(e.getMessage().equals(expectMessage))) {
                System.out.println("Problem detected: the error message was not as expected");
                return false;
            }
            System.out.println(e.getMessage());
        }

        // Check remove
        Room r11 = new Room("Asian 100", 7);
        Person p16 = new Person("Ella");
        Person p17 = new Person("Jackson");
        Person p18 = new Person("Avery");
        Person p19 = new Person("Scarlett");
        r11.checkIn(p16);
        r11.checkIn(p17);
        r11.checkIn(p18);
        r11.checkIn(p19);
        r11.checkOut(p18); // remove "Avery"
        String expect = "Asian 100\n===\nElla\n-\nJackson\n-\n-\n-\nScarlett\n";
        if (!(r11.toString().equals(expect))) {
            System.out.println("Problem detected: FAILED checkOut(), doesn't work as expect");
            return false;
        }

        return true;
    }

    public static boolean testRoomToString() {
        Room r12 = new Room("ECE 500", 7);
        Person people1 = new Person("Mouna");
        Person people2 = new Person("Hobbes");
        Person people3 = new Person("Michelle");

        r12.checkIn(people1);
        r12.checkIn(people2);
        r12.checkIn(people3);
        String expect = "ECE 500\n===\nMouna\n-\nHobbes\n-\nMichelle\n-\n-\n";
        if (!(r12.toString().equals(expect))) {
            System.out.println("Problem detected: FAILED toString(), doesn't work as expect");
            return false;
        }

        return true;
    }

    /**
     * This main method implements test methods.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        System.out.println("testPerson()" + testPerson() + "\n");
        System.out.println("testRoomConstructor(): " + testRoomConstructor() + "\n");
        System.out.println("testRoomAccessors(): " + testRoomAccessors() + "\n");
        System.out.println("testRoomCheckIn(): " + testRoomCheckIn() + "\n");
        System.out.println("testRoomCheckOut(): " + testRoomCheckOut() + "\n");
        System.out.println("testRoomToString(): " + testRoomToString());

    }
}

