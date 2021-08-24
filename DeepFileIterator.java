//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: (DeepFileIterator)
// Files: (P07Tester, ShallowFileIterator,FilteredFileIterator)
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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Deep file iterator is a class that iterates through contents of a directory.
 *
 * @author Nicole Welsh
 *
 */
public class DeepFileIterator implements Iterator<File> {

  private File[] folderContents; // an array of File references which this iterator steps through

  private int nextIndex; // the int index specifying the next File within folderContents that this
  // iterators next() method will return
  private DeepFileIterator contentsIterator; // deepFileIterator that has what is in the file

  /**
   * Constructor that creates a directory
   *
   * @param file File parameter with directory input
   * @throws FileNotFoundException throws when there are no more files
   */
  public DeepFileIterator(File file) throws FileNotFoundException {
    // if the file exists
    if (!(file.isDirectory())) {
      throw new FileNotFoundException("This file is not a directory");
    } else {
      // sets the variables
      this.nextIndex = 0;
      Arrays.sort(this.folderContents = file.listFiles());
    }
  }

  /**
   * Returns true if the iteration has more elements.
   *
   * @return true if there are more elements
   */
  @Override
  public boolean hasNext() {
    if (this.nextIndex < this.folderContents.length) {
      if (this.folderContents[this.nextIndex].isDirectory()) {
        // checks if there are still files
        if (this.contentsIterator != null) {
          if (this.contentsIterator.hasNext()) {
            return true;
          }
        } else {
          return true;
        }
        // if there are still files
      } else {
        return true;
      }
    }
    // loops through and allows for the files to be printed
    for (int index = this.nextIndex + 1; index < this.folderContents.length; index++) {
      if (this.folderContents[index].isDirectory()) { // is directory
        try {
          if ((new DeepFileIterator(this.folderContents[index])).hasNext()) { // if file in
                                                                              // directory
            return true;
          }
        } catch (FileNotFoundException e) {
          System.out.println("DFI cannot find the file.");
        }
      } else { // is file
        return true;
      }
    }
    return false; // no files or no directories w/ files
  }

  /**
   * Returns the next element in the iteration
   *
   * @return the next element
   * @throws NoSuchElementException if the iteration has no more elements
   */
  @Override
  public File next() throws NoSuchElementException {
    // if the file is done
    if (!this.hasNext()) {
      throw new NoSuchElementException("There are no next elements. ");
    }
    // creates the new file
    File get = this.folderContents[this.nextIndex];

    if (get.isDirectory()) {
      try {
        // this.nextIndex++;
        if (this.contentsIterator == null) {
          this.contentsIterator = new DeepFileIterator(get);
          return get;
        }
        if (this.contentsIterator.hasNext()) {
          File temp = this.contentsIterator.next();
          if (!this.contentsIterator.hasNext()) {
            this.nextIndex++;
            this.contentsIterator = null; // next file in directory
          }
          return temp; // returns

        } else {
          this.nextIndex++;
          this.contentsIterator = null;
          return this.next();
        }

      } catch (NoSuchElementException | FileNotFoundException e) {
        // if there is no more files
        System.out.println("There are no files here.");
      }
    } else {
      this.nextIndex++;
    }
    // prints which file
    return get;
  }
}
