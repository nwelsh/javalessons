//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: (AlphabetList)
// Files: (SortedListADT,Cart,LinkedCart,AlphabetTester)
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
 * Alphabet list creates, adds, gets, and changes a linked cart.
 *
 * @author Nicole Welsh and Rowan Kulp
 *
 */
public class AlphabetList implements SortedListADT<Cart> {

  private static final Cart MIN_CART = new Cart("A"); // The smallest cart that can be added to this
                                                      // sorted list
  private static final Cart MAX_CART = new Cart("Z"); // The largest cart that can be added to this
                                                      // sorted list
  private LinkedCart head; // head of this doubly linked list
  private LinkedCart tail; // tail of this doubly linked list
  private int size; // size of this list
  private int capacity; // maximum number of carts which can be stored in this list


  /**
   * Creates an empty doubly linked list of carts with a given capacity
   *
   * @param capacity maximum number of carts to be connected in this list of carts
   * @throws java.lang.IllegalArgumentException if capacity is zero or negative
   */
  public AlphabetList(int capacity) {
    // exception if the capacity is less than 1
    if (capacity <= 0) {
      throw new IllegalArgumentException(
          "The capacity of this list must be a non-zero a positive integer.");
    }
    // creates a new list
    this.capacity = capacity;
  }

  /**
   * Creates an empty doubly linked list of carts with a capacity equals to 26
   */
  public AlphabetList() {
    // creates the list
    this(26);
  }

  /**
   * Returns the capacity of this list in terms of maximum number of carts which can be stored in
   * it.
   *
   * @return the capacity of this list
   */
  public int capacity() {
    // returns the capacity
    return this.capacity;
  }

  /**
   * isEmpty() Checks whether this list is empty.
   *
   * @return true if the list is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    // checks if empty
    return this.size == 0;
  }

  /**
   * Adds a new cart to this sorted list.
   *
   * @param newCart to add to this list
   * @throws java.lang.IllegalStateException    if the list reached its capacity
   * @throws java.lang.IllegalArgumentException if newCart does not carry one upper-case letter in
   *                                            the range "A" .. "Z"
   */
  @Override
  public void add(Cart newObject) {
    if (this.size >= 26) {
      throw new IllegalStateException("This list is full. Cannot add another cart.");
    }
    // if tries to add null, skips over everything
    if (newObject == null) {
      return;
    }
    String stringObject = newObject.toString();
    LinkedCart current = this.head;
    // checks if the cart is an uppercase letter
    // could make it a char and see if its an uppercase character
    char firstLetter = stringObject.charAt(0);
    // !stringObject.matches("^[A-Z]*$")
    if (stringObject.length() != 1 || firstLetter < 65 || firstLetter > 90) {
      throw new IllegalArgumentException(
          "Can only add carts carrying one upper-case alphabetic letter in the range A .. Z.");
    }
    // if there is nothing in the list
    LinkedCart newCart = new LinkedCart(newObject);
    if (this.head == null || this.size == 0) {
      this.head = newCart;
      this.tail = newCart;
      this.size = 1;
      return;
    }
    // if adding to the head, it is smaller than the head
    if (newObject.compareTo(this.head.getCart()) < 0) {
      current = this.head;
      this.head = newCart;
      newCart.setNext​(current);
      this.size++;
      return;
      // if bigger than the head
      // }
    }
    if (this.size == 1) {
      if (newObject.compareTo(this.head.getCart()) > 0) {
        // System.out.println("140");
        this.head.setNext​(newCart);
        newCart.setPrevious​(this.head);
        this.tail = newCart;
        this.size = 2;
        return;
      }
    }

    // if it is already there
    if (newObject.compareTo(current.getCart()) == 0) {
      throw new IllegalArgumentException("Cannot duplicate letters or carts in this list.");
    }

    // if already contains that letter
    LinkedCart previous = current; // starts at head
    current = previous.getNext();

    while (current != null) {
      // if the list is full
      if (current == this.tail.getNext()) {
        throw new IllegalStateException("This list is full. Cannot add another cart.");
      }
      // tries to add an existing letter
      if (newObject.compareTo(current.getCart()) == 0) {
        throw new IllegalArgumentException("Cannot duplicate letters or carts in this list.");
      }
      // checks where the object will go
      if (newObject.getCargo().compareTo(current.getCart().getCargo()) < 0) {
        previous.setNext​(newCart);
        newCart.setPrevious​(previous);
        newCart.setNext​(current);
        current.setPrevious​(newCart);
        this.size++;
        return;
      }
      if (current.getNext() == null) {
        // add to tail
        current.setNext​(newCart);
        newCart.setPrevious​(current);
        this.tail = newCart;
        this.size++;
        return;
      }
      previous = current;
      current = current.getNext();
    }
  }

  /**
   * Returns the size of this list
   *
   * @return the number of carts linked in this list
   */
  @Override
  public int size() {
    // returns size
    return this.size;
  }

  /**
   * Removes all the carts from this list. This list must be empty after this method returns.
   */
  @Override
  public void clear() {
    // sets all to null
    this.head = null;
    this.tail = null;
    this.size = 0;

  }

  /**
   * Returns the cart at position index of this list without removing it
   *
   * @param index of the cart to return
   * @return the cart of this sorted list at the given index
   * @throws java.lang.IndexOutOfBoundsException if index is less than 0 or index is greater or
   *                                             equal to size()
   */
  @Override
  public Cart get(int index) {
    // if the index is invalid
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Invalid index.");
    }
    // gets the cart
    LinkedCart current = this.head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    return current.getCart();
  }

  /**
   * Returns the index of the cart carrying data within this list
   *
   * @param findCart cart to find in this list
   * @return the index of findCart within this list or -1 if this list does not contain that cart.
   */
  @Override
  public int indexOf(Cart findObject) {
    // finds the index
    LinkedCart newCart = this.head;
    int index = -1;
    while (newCart != null) {
      index++;
      if (newCart.toString().equals(findObject.toString())) {
        break;
      }
      // gets the next cart
      newCart = newCart.getNext();
    }
    return index;
  }

  /**
   * Returns and removes the cart from this sorted list at the given index position
   *
   * @param index of the cart to be removed
   * @return the removed cart
   * @throws java.lang.IndexOutOfBoundsException if index is less than 0 or index is larger or equal
   *                                             to size()
   */
  @Override
  public Cart remove(int index) {
    // checks if the index is invalid
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Invalid index.");
    }
    LinkedCart current = this.head;

    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }

    if (current.getPrevious() != null) {
      current.getPrevious().setNext​(current.getNext());
      // if delete first node
    } else {
      this.head = current.getNext();
      if (this.head != null) {
        this.head.setPrevious​(null);
      } else {
        this.tail = null;
      }
    }

    if (current.getNext() != null) {
      current.getNext().setPrevious​(current.getPrevious());
    } else {// if delete last node
      this.tail = current.getPrevious();
      if (this.tail != null) {
        this.tail.setNext​(null);
      } else {
        this.head = null;
      }
    }
    this.size--;
    return current.getCart();


  }

  /**
   * Returns a String representation of this list. Note that the implementation details of this
   * method is provided in the assignment's specification.
   *
   * @return a String representation of this list
   */
  @Override
  public String toString() {
    // given to us, toString method
    String string = "This list contains " + this.size + " cart(s)";
    if (this.size == 0) {
      return string;
    }
    string += ": [ ";
    // creates the appropriate string
    LinkedCart currentCart = this.head;
    while (currentCart != null) {
      string += currentCart.getCart().toString() + " ";
      currentCart = currentCart.getNext();
    }
    string += "]";
    return string;

  }

  /**
   * Reads the contents of this list in the forward direction starting from its head.
   *
   * @return a String representation of the contents of this list read in the forward direction or
   *         an empty string if this list is empty.
   */

  public String readForward() {

    String forward = "";
    LinkedCart loop = this.head;
    // loops from beginning to end and adds to the forward variable
    while (loop != null) {
      forward += loop.getCart().getCargo();
      loop = loop.getNext();
    }
    return forward;

  }

  /**
   * Reads the contents of this list in the backward direction starting from its tail
   *
   * @return a String representation of the contents of this list read in the backward direction or
   *         an empty string if this list is empty
   */
  public String readBackward() {
    String backward = "";
    LinkedCart loop = this.tail;
    if (this.size == 0) {
      return backward;
    }
    // starts from the end and appends to the backward variable
    for (int i = 0; i < this.size - 1; i++) {
      backward += loop.getCart().getCargo();
      loop = loop.getPrevious();
    }
    // adds the head at the end
    backward += this.head.getCart().getCargo();

    // returns previous
    return backward;

  }

}
