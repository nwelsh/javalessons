//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: (BoardingGroup)
// Files: (BGNode,RideQueue,QueueADT,ThemeParkApp)
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
/**
 * This class represents groups entering the ride line.
 *
 * @author Nicole Welsh
 *
 */
public class BoardingGroup {

  private String groupName; // name of the group of people
  private int numPeople; // amount of people in the group
  private boolean vip; // if a user is a VIP


  /**
   * Constructor that creates the group name and the amount of people in the group.
   *
   * @param groupName name of the group
   * @param numPeople amount of people
   */
  public BoardingGroup(String groupName, int numPeople) {
    // sets all the elements to their parameter and VIP to false
    this.groupName = groupName;
    this.numPeople = numPeople;
    this.vip = false;
  }

  /**
   * Accessor for group name.
   *
   * @return the name of the group
   */
  public String getGroupName() {
    // returns the reference
    return this.groupName;
  }

  /**
   * Accessor for the number of people.
   *
   * @return the number of people in a group
   */
  public int numPeople() {
    // returns the reference
    return this.numPeople;
  }

  /**
   * Accessor for VIP.
   *
   * @return if the group is VIP
   */
  public boolean isVip() {
    // returns the reference
    return this.vip;
  }

  /**
   * Mutator for VIP.
   *
   */
  public void setVip() {
    // sets VIP to true
    this.vip = true;
  }

}
