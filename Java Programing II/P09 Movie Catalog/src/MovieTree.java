//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Movie Tree
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
 * This class models a movie tree
 *
 */
public class MovieTree {
  private BSTNode<Movie> root; // root of this movie BST
  private int size; // size of this movie tree

  /**
   * Checks whether this binary search tree (BST) is empty
   *
   * @return true if this MovieTree is empty, false otherwise
   */
  public boolean isEmpty() {
    return this.root == null;
  }

  /**
   * Returns the number of movies stored in this BST.
   *
   * @return the size of this MovieTree
   */
  public int size() {
    return this.size;
  }

  /**
   * Adds a new movie to this MovieTree
   *
   * @param newMovie a new movie to add to this BST.
   * @return true if the newMovie was successfully added to this BST, and returns false if there is
   *         a match with this movie already stored in this BST.
   */
  public boolean addMovie(Movie newMovie) {
    if (isEmpty()) {// tree is empty
      this.root = new BSTNode<Movie>(newMovie);// add item at root position of this BST
      this.size++;
      return true;
    }
    else {// make call to addMovieHelper to recursively add item to this BST
      if (addMovieHelper(newMovie, this.root)) {
        this.size++;
        return true;
      }
      return false;
    }
  }

  /**
   * Recursive helper method to add a new movie to a MovieTree rooted at current.
   *
   * @param current  The "root" of the subtree we are inserting new movie into.
   * @param newMovie The movie to be added to a BST rooted at current.
   * @return true if the newMovie was successfully added to this MovieTree, false otherwise
   */
  protected static boolean addMovieHelper(Movie newMovie, BSTNode<Movie> current) {
    if (newMovie.compareTo(current.getData()) == 0) {
      return false;// Value already exists
    }

    if (newMovie.compareTo(current.getData()) < 0) {// newMovie < current
      if (current.getLeft() == null) {
        current.setLeft(new BSTNode<Movie>(newMovie));
        return true;
      }
      return addMovieHelper(newMovie, current.getLeft());
    }
    else if (newMovie.compareTo(current.getData()) > 0) {// newMovie > current
      if (current.getRight() == null) {
        current.setRight(new BSTNode<Movie>(newMovie));
        return true;
      }
      return addMovieHelper(newMovie, current.getRight());
    }

    return false;
  }

  /**
   * Returns a String representation of all the movies stored within this BST in the increasing
   * order, separating by a newline "\n".
   * <p>
   * For instance
   * "[(Year: 1988) (Rate: 7.0) (Name: Light Years)]" + "\n" +
   * "[(Year: 2015) (Rate: 6.5) (Name: Cinderella)]" + "\n"
   *
   * @return a String representation of all the movies stored within this BST sorted in an
   *         increasing order with respect to the result of Movie.compareTo() method (1year, rating,
   *         name). Returns an empty string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root);//make call to toStringHelper
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current.
   * <p>
   * An example of the String representation of the contents of a MovieTree is provided in the
   * description of the above toString() method.
   *
   * @param current reference to the current movie within this BST (root of a subtree)
   * @return a String representation of all the movies stored in the sub-tree rooted at current in
   *         increasing order with respect to the result of Movie.compareTo() method (year, rating,
   *         name). Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Movie> current) {
    if (current == null) {
      return "";
    } else {// use in-order traversal
      String result = "";

      // recur on the left sub-tree if currentNode has a left child
      result = toStringHelper(current.getLeft());

      result += current.getData().toString() + "\n";

      // recur on the right sub-tree if the currentNode has a right child
      result += toStringHelper(current.getRight());

      return result;
    }
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   *
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);//make call to heightHelper
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current counting the
   * number of nodes and NOT the number of edges from current to the deepest leaf
   *
   * @param current pointer to the current BSTNode within a MovieTree (root of a subtree)
   * @return height of the subtree rooted at current
   */
  protected static int heightHelper(BSTNode<Movie> current) {
    if (current == null) {// Base case
      return 0;
    } else {// Recursive case
      int leftHeight = heightHelper(current.getLeft());
      int rightHeight = heightHelper(current.getRight());

      // if left height < rightHeight, return (rightHeight + 1). Vice versa
      if (leftHeight < rightHeight) {
        return rightHeight + 1;
      } else {
        return leftHeight + 1;
      }
    }
  }

  /**
   * Checks whether this MovieTree contains a movie given its name, production year, and rating.
   *
   * @param year   year of production of the movie to search
   * @param rating rating of the movie to search
   * @param name   name of the movie to search
   * @return true if there is a match with this movie in this BST, and false otherwise
   */
  public boolean contains(int year, double rating, String name) {
    Movie movie = new Movie(year, rating, name);
    return containsHelper(movie, this.root);//make call to containsHelper
  }

  /**
   * Recursive helper method to search whether there is a match with a given movie in the subtree
   * rooted at current
   *
   * @param target  a reference to a movie we are searching for a match in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return true if match found and false otherwise
   */
  protected boolean containsHelper(Movie target, BSTNode<Movie> current) {
    if (current == null) {// Base case
      return false;
    }
    else if (target.compareTo(current.getData()) == 0) {// item found -> successful search
      return true;
    }
    else if (target.compareTo(current.getData()) < 0) {// newMovie < current
      // recurse on the left subtree rooted at current.getLeft()
      return containsHelper(target, current.getLeft());
    }
    else if (target.compareTo(current.getData()) > 0) {// newMovie > current
      // recurse on the right subtree rooted at current.getRight()
      return containsHelper(target, current.getRight());
    }

    return false;
  }

  /**
   * Gets the best (maximum) movie in this BST
   *
   * @return the best (largest) movie (the most recent, highest rated, and having the largest name)
   *         in this MovieTree, and null if this tree is empty.
   */
  public Movie getBestMovie() {
    if (isEmpty()) {
      return null;
    }
    else {
      BSTNode<Movie> current = this.root;

      // use iterative to find the best movie
      while (current.getRight() != null) {
        current = current.getRight();
      }

      return current.getData();
    }
  }

  /**
   * Search for movies given the year and minimum rating as lookup key.
   *
   * @param year   production year of a movie
   * @param rating rating value of a movie
   * @return a list of movies whose year equals our lookup year key and having a rating greater or
   *         equal to a given rating.
   * @throws NoSuchElementException with a descriptive error message if there is no Movie found in
   *           this BST having the provided year and a rating greater or equal to the provided
   *           rating
   */
  public ArrayList<Movie> lookup(int year, double rating) {
    ArrayList<Movie> movieList = new ArrayList<>();

    // Call lookupHelper given the year, rating, the root of this MovieTree and an empty arrayList
    lookupHelper(year, rating, this.root, movieList);

    if (movieList.isEmpty()) {// If no match found, throw Exception
      throw new NoSuchElementException("Error! No match found by lookupHelper");
    }
    else {// else return the list of movies
      return movieList;
    }

  }

  /**
   * Recursive helper method to lookup the list of movies given their year of production and a
   * minimum value of rating
   *
   * @param year      the year we would like to search for a movie
   * @param rating    the minimum rating we would like to search for a movie
   * @param movieList an arraylist which stores the list of movies matching our search criteria
   *                  (exact year of production and having at least the provided rating) within the
   *                  subtree rooted at current
   * @param current   "root" of the subtree we are looking for a match to find within it.
   */
  protected void lookupHelper(int year, double rating, BSTNode<Movie> current,
      ArrayList<Movie> movieList) {

    if (current == null) {// If the BST rooted at current is null, do nothing and return
      return;
    }
    else {// Perform a pre-order traversal of the subtree
      if (current.getData().getYear() == year && current.getData().getRating() >= rating) {
        movieList.add(current.getData());// add item to the movieList
      }

      // recurse on the left subtree rooted at current.getLeft()
      lookupHelper(year, rating, current.getLeft(), movieList);

      // recurse on the left subtree rooted at current.getRight()
      lookupHelper(year, rating, current.getRight(), movieList);
    }
  }
}
