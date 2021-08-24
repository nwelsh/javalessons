//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: (PatientRecordTree)
// Files: (PatientRecordNode, PatientRecordTree, PatientRecord, PatientRecordTreeTester)
// Course: (CS300 Spring 2020)
//
// Author: (Nicole Welsh)
// Email: (newelsh@wisc.edu)
// Lecturer's Name: (Gary Dahl)
//
//////////// PAIR PROGRAMMING (MAY SKIP WHEN WORKING INDIVIDUALLY) ////////////
//
// Partner Name: (NA)
// Partner Email: (NA)
// Partner Lecturer's Name: (NA)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __ Write-up states that pair programming is allowed for this assignment.
// __ We have both read and understood the course Pair Programming Policy.
// __ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course
// staff must fully acknowledge and credit those sources here. If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons: none
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

/**
 * This class implements a binary search tree (BST) which stores a set of patient records. The left
 * subtree contains the patient records of all the patients who are older than the patient who's
 * PatientRecord is stored at a parent node. The right subtree contains the patient records of all
 * the patients who are younger than the patient who's PatientRecord is stored at a parent node.
 *
 * @author Nicole Welsh
 */
public class PatientRecordTree {
  private PatientRecordNode root; // root of this binary search tree
  private int size; // total number of patient records stored in this tree.

  /**
   * Checks whether this binary search tree (BST) is empty
   *
   * @return true if this PatientRecordTree is empty, false otherwise
   */
  public boolean isEmpty() {
    return this.root == null;
    // if there is no root, there cannot be a tree
  }

  /**
   * Returns the number of patient records stored in this BST.
   *
   * @return the size of this PatientRecordTree
   */
  public int size() {
    // to be called to the helper method
    // size is set to the size of the root
    return this.size = this.size(this.root);

  }

  /**
   * My helper method to help me recursivly find the size.
   *
   * @param node root node of the tree
   * @return size of the PatientRecordTree
   */
  private int size(PatientRecordNode node) {
    // if the node is empty, it does not have a size
    if (node == null) {
      return 0;
    } else {
      // recursively counts the left and right and adds one for the root
      return (this.size(node.getLeftChild()) + this.size(node.getRightChild())) + 1;
    }
  }

  /**
   * Recursive helper method to add a new PatientRecord to a PatientRecordTree rooted at current.
   *
   * @param current   The "root" of the subtree we are inserting newRecord into.
   * @param newRecord The PatientRecord to be added to a BST rooted at current.
   * @return true if the newRecord was successfully added to this PatientRecordTree, false otherwise
   */
  public static boolean addPatientRecordHelper(PatientRecord newRecord, PatientRecordNode current) {

    if (current.getPatientRecord() != null) {
      // if both aspects of the node are the same, would be false
      if (newRecord.getName().equals(current.getPatientRecord().getName())
          && newRecord.getDateOfBirth().equals(current.getPatientRecord().getDateOfBirth())) {
        return false;
        // if the node is bigger, is a left child
      } else if (current.getPatientRecord().getDateOfBirth()
          .compareTo(newRecord.getDateOfBirth()) > 0) {
        if (current.getLeftChild() == null) {
          current.setLeftChild(new PatientRecordNode(newRecord));
          return true;
        }
        return PatientRecordTree.addPatientRecordHelper(newRecord, current.getLeftChild());
        // if node is smaller, would be a right child
      } else if (current.getPatientRecord().getDateOfBirth()
          .compareTo(newRecord.getDateOfBirth()) < 0) {
        if (current.getRightChild() == null) {
          current.setRightChild(new PatientRecordNode(newRecord));
          return true;
        }
        // recursive statement to keep adding
        return PatientRecordTree.addPatientRecordHelper(newRecord, current.getRightChild());
      }
    }
    return false;
    // otherwise false
  }

  /**
   * Adds a new PatientRecord to this PatientRecordTree
   *
   * @param newRecord a new PatientRecord to add to this BST.
   * @return true if the newRecord was successfully added to this BST, and returns false if there is
   *         a match with this PatientRecord already already stored in this BST.
   */
  public boolean addPatientRecord(PatientRecord newRecord) {
    // if node is empty, make the root the new node
    if (this.isEmpty()) {
      this.root = new PatientRecordNode(newRecord);
      return true;
    } else {
      // if it is not empty, add with add method
      if (PatientRecordTree.addPatientRecordHelper(newRecord, this.root)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a PatientRecordTree is provided in the
   * description of the above toString() method.
   *
   * @param current reference to the current PatientRecordNode within this BST.
   * @return a String representation of all the PatientRecords stored in the sub-tree
   *         PatientRecordTree rooted at current in increasing order with respect to the patients
   *         dates of birth. Returns an empty String "" if current is null.
   */
  public static String toStringHelper(PatientRecordNode current) {
    String print = "";
    // returns nothing if there is nothing in the tree
    if (current == null) {
      return print;
    }
    // starts with the left and adds the biggest to smallest
    print += PatientRecordTree.toStringHelper(current.getLeftChild());
    // adds the node information to the print statement
    print += current.getPatientRecord().toString() + "\n";
    print += PatientRecordTree.toStringHelper(current.getRightChild());

    return print;
  }

  /**
   * Returns a String representation of all the PatientRecords stored within this BST in the
   * increasing order, separated by a newline "\n". For instance: "Sarah(1/2/1935)" + "\n" +
   * "George(5/27/1943)" + "\n" + "Adam(8/12/1972)" + "\n" + "Norah(11/23/1985)" + "\n" +
   * "William(6/4/1998)" + "\n" + "Nancy(9/12/2003)" + "\n" + "Sam(4/20/2019)" + "\n"
   *
   * @return a String representation of all the PatientRecords stored within this BST sorted in an
   *         increasing order with respect to the dates of birth of the patients (i.e. from the
   *         oldest patient to the youngest patient). Returns an empty string "" if this BST is
   *         empty.
   */
  @Override
  public String toString() {
    // calls to the toString method
    return PatientRecordTree.toStringHelper(this.root);
  }

  /**
   * Search for a patient record (PatientRecord) given the date of birth as lookup key.
   *
   * @param date a String representation of the date of birth of a patient in the format mm/dd/yyyy
   * @return the PatientRecord of the patient born on date.
   * @throws a NoSuchElementException with a descriptive error message if there is no PatientRecord
   *           found in this BST having the provided date of birth
   */
  public PatientRecord lookup(String date) {
    // calls to the look up helper
    PatientRecord findRecord = new PatientRecord("", date);
    return this.lookupHelper(findRecord, this.root);
  }

  /**
   * Recursive helper method to lookup a PatientRecord given a reference PatientRecord with the same
   * date of birth in the subtree rooted at current
   *
   * @param findRecord a reference to a PatientRecord target we are lookup for a match in the BST
   *                   rooted at current.
   * @param current    "root" of the subtree we are looking for a match to findRecord within it.
   * @return reference to the PatientRecord stored in this BST which matches findRecord.
   * @throws NoSuchElementException with a descriptive error message if there is no patient record
   *                                whose date of birth matches date, stored in this BST.
   */
  private PatientRecord lookupHelper(PatientRecord findRecord, PatientRecordNode current) {
    // if the tree is empty
    if (this.root == null) {
      throw new NoSuchElementException("This tree is empty.");
    }
    if (current != null) {
      // if the current node is the node to be found
      if (findRecord.getDateOfBirth().equals(current.getPatientRecord().getDateOfBirth())) {
        return current.getPatientRecord();
        // if the node is smaller
      } else if (current.getPatientRecord().getDateOfBirth()
          .compareTo(findRecord.getDateOfBirth()) > 0) {
        return this.lookupHelper(findRecord, current.getLeftChild());
        // if the node is bigger
      } else if (current.getPatientRecord().getDateOfBirth()
          .compareTo(findRecord.getDateOfBirth()) < 0) {
        return this.lookupHelper(findRecord, current.getRightChild());
      }
    } else {
      // if it doesnt find the date
      throw new NoSuchElementException("No search results.");
    }
    // default
    return null;

  }

  /**
   * Computes and returns the height of this BST, counting the number of nodes (PatientRecordNodes)
   * from root to the deepest leaf.
   *
   * @return the height of this Binary Search Tree
   */
  public int height() {
    // calls to the height helper
    return PatientRecordTree.heightHelper(this.root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   *
   * @param current pointer to the current PatientRecordNode within a PatientRecordTree
   * @return height of the subtree rooted at current, counting the number of PatientRecordNodes
   */
  public static int heightHelper(PatientRecordNode current) {
    // if the root is null, there is 0 height
    if (current == null) {
      return 0;
    } else {
      // adds one for the root
      return 1 + Math.max(PatientRecordTree.heightHelper(current.getLeftChild()),
          PatientRecordTree.heightHelper(current.getRightChild()));
    }
  }


  /**
   * Returns the PatientRecord of the youngest patient in this BST.
   *
   * @return the PatientRecord of the youngest patient in this BST and null if this tree is empty.
   */
  public PatientRecord getRecordOfYoungestPatient() {
    // find the right most
    return this.getRecordOfYoungestPatientHelper(this.root);
  }

  /**
   * Helper method so the youngest patient can be found recursively from the
   * getRecordOfYoungestPatient method.
   *
   * @param helper the nodes that start at the root
   * @return the youngest patient
   */
  private PatientRecord getRecordOfYoungestPatientHelper(PatientRecordNode helper) {
    // uses current to find the right most
    PatientRecordNode current = helper;
    while (current.getRightChild() != null) {
      current = current.getRightChild();
    }
    return current.getPatientRecord();
  }

  /**
   * Returns the PatientRecord of the oldest patient in this BST.
   *
   * @return the PatientRecord of the oldest patient in this BST, and null if this tree is empty.
   */
  public PatientRecord getRecordOfOldestPatient() {
    // find the left most
    return this.getRecordOfOldestPatientHelper(this.root);
  }

  /**
   * Helper method so the oldest patient can be found recursively from the getRecordOfOldestPatient
   * method.
   *
   * @param helper the nodes that start at the root
   * @return the oldest patient
   */
  private PatientRecord getRecordOfOldestPatientHelper(PatientRecordNode helper) {
    // finds the left most child
    PatientRecordNode current = helper;
    while (current.getLeftChild() != null) {
      current = current.getLeftChild();
    }
    return current.getPatientRecord();

  }

}
