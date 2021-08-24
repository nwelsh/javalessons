import java.util.Random;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: (Elastic Bank)
// Files: (Elastic Bank tester, coin)
// Course: (CS300 Spring 2020)
//
// Author: (Nicole Welsh)
// Email: (newelsh@wisc.edu)
// Lecturer's Name: (Gary Dahl)
//
//////////// PAIR PROGRAMMING (MAY SKIP WHEN WORKING INDIVIDUALLY) ////////////
//
// Partner Name: (Rowan Kulp)
// Partner Email: (rkulp@wisc.edu)
// Partner Lecturer's Name: (Gary Dahl)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understood the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
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
 * Elastic Bank is a class that makes a bank with two chances to fill the bank completely. The bank
 * expands space after 10 slots are used, twice. After 30 spaces are filled, the bank overflows and
 * everything is emptied.
 *
 * @Author Nicole Welsh and Rowan Kulp
 */
public class ElasticBank {
  // private instance variables
  private Coin[] coins;
  private int size;
  private int expansionsLeft;
  // random number for the remove
  private Random rand = new Random(100);
  // new variable because 10 is the max size of an array
  private final int expansionAmt = 10;

  /**
   * This is a class constructor that sets the array size to 10.
   */
  public ElasticBank() {
    // 10 is the size of the array
    this(10);
  }

  /**
   * The ElasticBank method takes in an int value initialCapacity and creates the coins array with
   * the size of the input value.
   *
   * @param initialCapacity the initial size of the array
   */
  public ElasticBank(int initialCapacity) {
    // starts the size over at 0
    size = 0;
    coins = new Coin[initialCapacity];
    // sets the expansions to 2
    expansionsLeft = 2;
  }

  /**
   * Calculates the capacity of the coins array. Subtracts the size from the length to calculate
   * capacity
   *
   * @return the capacity of the array
   */
  public int capacity() {
    // subtracts the size from the length
    return coins.length - size;
  }


  /**
   * Calls to the instance variable expansionsLeft to get the amount of expansions left.
   *
   * @return expansionsLeft
   */
  public int getExpansions() {
    // from an instance variable
    return expansionsLeft;
  }


  /**
   * getSize returns the instance variable size to know the size of the array.
   *
   * @return size
   */
  public int getSize() {
    // from an instance variable
    return size;
  }


  /**
   * getBalance loops through the coins array and uses getValue to get the balance of the bank.
   *
   * @return the balance in the bank
   */
  public int getBalance() {
    int total = 0;
    for (int i = 0; i < coins.length; i++) {
      // if coin is null, it will just keep looping through
      if (coins[i] != null) {
        // adds the value of i to the balance
        total += coins[i].getValue();
      }
    }
    return total;
  }


  /**
   * getCoins prints out a string with the name of the coin and the amount
   *
   * @return list of all the coins printed
   */
  public String getCoins() {
    String coinList = "";
    // enhanced for loop for the array
    for (Coin tempCoin : coins) {
      if (tempCoin != null) {
        // prints out ex: (PENNY, 1)
        coinList += "(" + tempCoin.getName() + ", " + tempCoin.getValue() + ") ";
      }
    }
    coinList = coinList.substring(0, coinList.length() - 1);// removes space from the end
    return coinList;
  }


  /**
   * removeCoin takes a random number, and removes the number index of the number. The coin removed
   * is set to null, and placed at the end of the array.
   *
   * @return the coin removed
   */
  public Coin removeCoin() {
    // sets index to the random int
    int index = rand.nextInt(coins.length);
    Coin coinCopy = coins[index];
    for (int i = index; i < size; i++) {
      coins[i] = coins[i + 1];
    }
    // does --size so it decrements before
    coins[--size] = null;
    return coinCopy;
  }

  /**
   * empty empties the coins array, setting every element to null and the size to 0.
   */
  public void empty() {
    for (int i = 0; i < coins.length; i++) {
      // sets every element to null
      coins[i] = null;
    }
    // sets the size back to start
    size = 0;
  }



  /**
   * addCoin adds a coin to the coin array and increments the size of the array.
   *
   * @param c the imported array
   */
  public void addCoin(Coin c) {
    // if the array is completely full, empties
    if (coins.length == size) {
      if (expansionsLeft == 0) {
        empty();
        size = 0;
      } else {
        // otherwise, uses an expansion
        Coin[] temp = new Coin[coins.length + expansionAmt];
        for (int i = 0; i < coins.length; i++) {
          temp[i] = coins[i];
        }
        coins = temp;
        expansionsLeft--;
      }
    }
    // sets the coin c into the coins array
    coins[size++] = c;
  }

}

