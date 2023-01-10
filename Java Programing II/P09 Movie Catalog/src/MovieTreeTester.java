//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Movie Tree Tester
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
import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * MovieTree.
 *
 */
public class MovieTreeTester {

  /**
   * Checks the correctness of the implementation of both addMovie() and toString() methods
   * implemented in the MovieTree class.
   * <p>
   * This unit test considers at least the following scenarios. (1) Create a new empty MovieTree,
   * and check that its size is 0, it is empty, and that its string representation is an empty
   * string "". (2) try adding one movie and then check that the addMovie() method call returns
   * true, the tree is not empty, its size is 1, and the .toString() called on the tree returns the
   * expected output. (3) Try adding another movie which is smaller that the movie at the root, (4)
   * Try adding a third movie which is greater than the one at the root, (5) Try adding at least two
   * further movies such that one must be added at the left subtree, and the other at the right
   * subtree. For all the above scenarios, and more, double check each time that size() method
   * returns the expected value, the add method call returns true, and that the .toString() method
   * returns the expected string representation of the contents of the binary search tree in an
   * increasing order from the smallest to the greatest movie with respect to year, rating, and then
   * name. (6) Try adding a movie already stored in the tree. Make sure that the addMovie() method
   * call returned false, and that the size of the tree did not change.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddMovieToStringSize() {
    try {
      // Check a new empty MovieTree
      MovieTree mt = new MovieTree();
      if (mt.size() != 0 || !mt.isEmpty() || !mt.toString().equals("")) {
        System.out.println("Error to create a new empty MovieTree");
        return false;
      }

      // Check when add one movie
      boolean result1 = mt.addMovie(new Movie(2019, 8.5, "Avengers Endgame"));
      String expected1 = "[(Year: 2019) (Rate: 8.5) (Name: Avengers Endgame)]" + "\n";
      if (!result1 || mt.isEmpty() || mt.size() != 1 || !mt.toString().equals(expected1)) {
        System.out.println("Error! addMovie() method failed");
        return false;
      }

      // add smaller than the root
      boolean result2 = mt.addMovie(new Movie(2015, 7.7, "Ant Man"));
      String expected2 = "[(Year: 2015) (Rate: 7.7) (Name: Ant Man)]" + "\n"
          + "[(Year: 2019) (Rate: 8.5) (Name: Avengers Endgame)]" + "\n";
      if (!result2 || mt.isEmpty() || mt.size() != 2 || !mt.toString().equals(expected2)) {
        System.out.println("Error! addMovie() method failed");
        return false;
      }

      // add greater than the root
      boolean result3 = mt.addMovie(new Movie(2019, 8.6, "Captain Marvel"));
      String expected3 = "[(Year: 2015) (Rate: 7.7) (Name: Ant Man)]" + "\n"
          + "[(Year: 2019) (Rate: 8.5) (Name: Avengers Endgame)]" + "\n"
          + "[(Year: 2019) (Rate: 8.6) (Name: Captain Marvel)]" + "\n";
      if (!result3 || mt.isEmpty() || mt.size() != 3 || !mt.toString().equals(expected3)) {
        System.out.println("Error! addMovie() method failed");
        return false;
      }

      // add at the left subtree
      boolean result4 = mt.addMovie(new Movie(2014, 8.1, "Guardians of the Galaxy"));
      String expected4 = "[(Year: 2014) (Rate: 8.1) (Name: Guardians of the Galaxy)]" + "\n"
          + "[(Year: 2015) (Rate: 7.7) (Name: Ant Man)]" + "\n"
          + "[(Year: 2019) (Rate: 8.5) (Name: Avengers Endgame)]" + "\n"
          + "[(Year: 2019) (Rate: 8.6) (Name: Captain Marvel)]" + "\n";
      if (!result4 || mt.isEmpty() || mt.size() != 4 || !mt.toString().equals(expected4)) {
        System.out.println("Error! addMovie() method failed");
        return false;
      }

      // add at the right subtree
      boolean result5 = mt.addMovie(new Movie(2021, 9.0, "WandaVision"));
      String expected5 = "[(Year: 2014) (Rate: 8.1) (Name: Guardians of the Galaxy)]" + "\n"
          + "[(Year: 2015) (Rate: 7.7) (Name: Ant Man)]" + "\n"
          + "[(Year: 2019) (Rate: 8.5) (Name: Avengers Endgame)]" + "\n"
          + "[(Year: 2019) (Rate: 8.6) (Name: Captain Marvel)]" + "\n"
          + "[(Year: 2021) (Rate: 9.0) (Name: WandaVision)]" + "\n";
      if (!result5 || mt.isEmpty() || mt.size() != 5 || !mt.toString().equals(expected5)) {
        System.out.println("Error! addMovie() method failed");
        return false;
      }

      //adding a movie already stored in the tree.
      boolean result6 = mt.addMovie(new Movie(2021, 9.0, "WandaVision"));
      if (result6 != false || mt.isEmpty() || mt.size() != 5 || !mt.toString().equals(expected5)) {
        System.out.println("Error! addMovie() method failed");
        return false;
      }
    }catch (Exception e){// an unexpected exception was thrown
      e.printStackTrace();
      return false;
    }

    return true;//passed all the above tester methods without errors
  }

  /**
   * This method checks mainly for the correctness of the MovieTree.contains() method.
   * <p>
   * It must consider at least the following test scenarios. (1) Create a new MovieTree. Then, check
   * that calling the contains() method on an empty MovieTree returns false. (2) Consider a
   * MovieTree of height 3 which contains at least 5 movies. Then, try to call contains() method to
   * search for the movie having a match at the root of the tree. (3) Then, search for a movie at
   * the right and left subtrees at different levels considering successful and unsuccessful search
   * operations. Make sure that the contains() method returns the expected output for every method
   * call.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testContains() {
    try {
      // Create a new empty MovieTree
      MovieTree mt1 = new MovieTree();
      boolean result1 = mt1.contains(2014, 8.1, "Guardians of the Galaxy");
      if (result1) {
        System.out.println("Error! contains() method failed");
        return false;
      }
      // add 5 movies and height is 3
      mt1.addMovie(new Movie(2019, 8.5, "Avengers Endgame"));
      mt1.addMovie(new Movie(2015, 7.7, "Ant Man"));
      mt1.addMovie(new Movie(2019, 8.8, "Captain Marvel"));
      mt1.addMovie(new Movie(2014, 8.1, "Guardians of the Galaxy"));
      mt1.addMovie(new Movie(2015, 8.4, "Agents of SHIELD 3"));

      //successful search operations.
      boolean result2 = mt1.contains(2019, 8.5, "Avengers Endgame");
      if (!result2) {
        System.out.println("Error! contains() method failed");
        return false;
      }

      boolean result3 = mt1.contains(2015, 7.7, "Ant Man");
      if (!result3) {
        System.out.println("Error! contains() method failed");
        return false;
      }

      boolean result4 = mt1.contains(2019, 8.8, "Captain Marvel");
      if (!result4) {
        System.out.println("Error! contains() method failed");
        return false;
      }

      boolean result5 = mt1.contains(2014, 8.1, "Guardians of the Galaxy");
      if (!result5) {
        System.out.println("Error! contains() method failed");
        return false;
      }

      boolean result6 = mt1.contains(2015, 8.4, "Agents of SHIELD 3");
      if (!result6) {
        System.out.println("Error! contains() method failed");
        return false;
      }

      //unsuccessful search operations.
      boolean result7 = mt1.contains(2014, 8.0, "Apple");
      if (result7) {
        System.out.println("Error! contains() method failed");
        return false;
      }

      boolean result8 = mt1.contains(2019, 8.6, "Peach");
      if (result8) {
        System.out.println("Error! contains() method failed");
        return false;
      }

      boolean result9 = mt1.contains(2019, 9.0, "Flight");
      if (result9) {
        System.out.println("Error! contains() method failed");
        return false;
      }

      boolean result10 = mt1.contains(2015, 8.5, "Bear");
      if (result10) {
        System.out.println("Error! contains() method failed");
        return false;
      }
    }catch (Exception e){// an unexpected exception was thrown
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of MovieTree.height() method.
   * <p>
   * This test must consider several scenarios.
   * (1) ensures that the height of an empty movie tree is zero.
   * (2) ensures that the height of a tree which consists of only one node is 1.
   * (3) ensures that the height of a MovieTree with the following structure for 
   * instance, is 4.
   *
   *       (*)
   *     /    \
   *  (*)      (*)
   *    \     /  \
   *    (*) (*)  (*)
   *             /
   *           (*)
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    try {
      // Check a new empty MovieTree's height
      MovieTree mt2 = new MovieTree();
      int result1 = mt2.height();
      if (result1 != 0) {
        System.out.println("Error! height() method failed");
        return false;
      }

      // Check a tree which consists of only one node is 1.
      mt2.addMovie(new Movie(2019, 8.5, "Avengers Endgame"));
      int result2 = mt2.height();
      if (result2 != 1) {
        System.out.println("Error! height() method failed");
        return false;
      }

      // Check a MovieTree height is 4
      mt2.addMovie(new Movie(2015, 7.7, "Ant Man"));
      mt2.addMovie(new Movie(2019, 8.8, "Captain Marvel"));
      mt2.addMovie(new Movie(2019, 8.6, "Avenger Endgame"));
      mt2.addMovie(new Movie(2019, 9.0, "Agents of SHIELD 2"));
      mt2.addMovie(new Movie(2015, 8.4, "Agents of SHIELD 3"));
      mt2.addMovie(new Movie(2019, 8.9, "Agents of SHIELD 4"));
      int result3 = mt2.height();
      if (result3 != 4) {
        System.out.println("Error! height() method failed");
        return false;
      }
    }catch (Exception e){// an unexpected exception was thrown
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of MovieTree.getBestMovie() method.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestMovie() {
    try {
      MovieTree mt3 = new MovieTree();

      Movie result1 = mt3.getBestMovie();
      if (result1 != null) {
        System.out.println("Error! getBestMovie() method failed");
      }

      mt3.addMovie(new Movie(2019, 8.5, "Avengers Endgame"));
      mt3.addMovie(new Movie(2015, 7.7, "Ant Man"));
      mt3.addMovie(new Movie(2019, 8.8, "Captain Marvel"));
      mt3.addMovie(new Movie(2019, 8.6, "Avenger Endgame"));
      mt3.addMovie(new Movie(2019, 9.0, "Agents of SHIELD 2"));
      mt3.addMovie(new Movie(2015, 8.4, "Agents of SHIELD 3"));
      mt3.addMovie(new Movie(2019, 8.9, "Agents of SHIELD 4"));
      Movie expected = new Movie(2019, 9.0, "Agents of SHIELD 2");
      Movie result2 = mt3.getBestMovie();
      if (!result2.equals(expected)) {
        System.out.println("Error! getBestMovie() method failed");
        return false;
      }

      // Case 2
      MovieTree mt4 = new MovieTree();
      mt4.addMovie(new Movie(2019, 8.5, "Avengers Endgame"));
      mt4.addMovie(new Movie(2015, 7.7, "Ant Man"));
      mt4.addMovie(new Movie(2021, 8.9, "Agents of SHIELD 4"));
      Movie expected2 = new Movie(2021, 8.9, "Agents of SHIELD 4");
      Movie result3 = mt4.getBestMovie();
      if (!result3.equals(expected2)) {
        System.out.println("Error! getBestMovie() method failed");
        return false;
      }

    }catch (Exception e){// an unexpected exception was thrown
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of MovieTree.lookup() method.
   * <p>
   * This test must consider at least 3 test scenarios. (1) Ensures that the MovieTree.lookup()
   * method throws a NoSuchElementException when called on an empty tree. (2) Ensures that the
   * MovieTree.lookup() method returns an array list which contains all the movies satisfying the
   * search criteria of year and rating, when called on a non empty movie tree with one match, and
   * two matches and more. Vary your search criteria such that the lookup() method must check in
   * left and right subtrees. (3) Ensures that the MovieTree.lookup() method throws a
   * NoSuchElementException when called on a non-empty movie tree with no search results found.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    ArrayList<Movie> resultList = new ArrayList<>();
    ArrayList<Movie> expectList = new ArrayList<>();

    try {
      MovieTree mt4 = new MovieTree();// Tree is empty
      try {
        mt4.lookup(2019, 8.5);// NoSuchElementException expected to be thrown
        System.out.println("Problem detected: No exception was thrown");
        return false;
      } catch (NoSuchElementException e) {
        if (e.getMessage() == null || e.getMessage().length() == 0) {
          System.out.println("Error! lookup() method failed");
          return false;
        }
        System.out.println("Successfully caught: call lookup() when tree empty");
      }

      mt4.addMovie(new Movie(2019, 8.5, "Avengers Endgame"));
      mt4.addMovie(new Movie(2015, 7.7, "Ant Man"));
      mt4.addMovie(new Movie(2019, 8.8, "Captain Marvel"));
      mt4.addMovie(new Movie(2019, 8.6, "Avenger Endgame"));
      mt4.addMovie(new Movie(2019, 9.0, "Agents of SHIELD 2"));
      mt4.addMovie(new Movie(2015, 8.4, "Agents of SHIELD 3"));
      mt4.addMovie(new Movie(2019, 8.9, "Agents of SHIELD 4"));

      resultList = mt4.lookup(2019, 9.0);
      expectList.add(new Movie(2019, 9.0, "Agents of SHIELD 2"));
      if (!resultList.equals(expectList) || resultList.size() != 1) {
        System.out.println("Error! lookup() method failed");
        return false;
      }

      expectList = new ArrayList<>();// renew list

      resultList = mt4.lookup(2019, 8.9);
      expectList.add(new Movie(2019, 9.0, "Agents of SHIELD 2"));
      expectList.add(new Movie(2019, 8.9, "Agents of SHIELD 4"));
      if (!resultList.equals(expectList) || resultList.size() != 2) {
        System.out.println("Error! lookup() method failed");
        return false;
      }

      expectList = new ArrayList<>();// renew list

      resultList = mt4.lookup(2019, 8.5);
      expectList.add(new Movie(2019, 8.5, "Avengers Endgame"));
      expectList.add(new Movie(2019, 8.8, "Captain Marvel"));
      expectList.add(new Movie(2019, 8.6, "Avenger Endgame"));
      expectList.add(new Movie(2019, 9.0, "Agents of SHIELD 2"));
      expectList.add(new Movie(2019, 8.9, "Agents of SHIELD 4"));
      if (!resultList.equals(expectList) || resultList.size() != 5) {
        System.out.println("Error! lookup() method failed");
        return false;
      }

      expectList = new ArrayList<>();// renew list
      resultList = new ArrayList<>();// renew list

      //search item that doesn't exist
      try {
        resultList = mt4.lookup(2020, 8.5);// NoSuchElementException expected to be thrown
        System.out.println("Problem detected: No exception was thrown");
        return false;
      } catch (NoSuchElementException e) {
        System.out.println("Successfully caught: call lookup() when tree empty");
        if (!resultList.equals(expectList) || resultList.size() != 0 || e.getMessage() == null || e.getMessage().length() == 0) {
          System.out.println("Error! lookup() method failed");
          return false;
        }
      }

    }catch (Exception e){// an unexpected exception was thrown
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Calls the test methods
   *
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    boolean result = true;

    if (!testAddMovieToStringSize()) {
      System.out.println("Detect errors in testAddMovieToStringSize() method");
      result = false;
    }

    if (!testContains()) {
      System.out.println("Detect errors in testContains() method");
      result = false;
    }

    if (!testHeight()) {
      System.out.println("Detect errors in testHeight() method");
      result = false;
    }

    if (!testGetBestMovie()) {
      System.out.println("Detect errors in testGetBestMovie() method");
      result = false;
    }

    if (!testLookup()) {
      System.out.println("Detect errors in testLookup() method");
      result = false;
    }

    if (result) {
      System.out.println("\n******\nAll tests passed --> Program Terminate");
    }
    else {
      System.out.println("\n******\nTests failed --> Program Terminate");
    }

  }
}
