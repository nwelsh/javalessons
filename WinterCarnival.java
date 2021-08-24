import java.util.ArrayList;
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: (WinterCarnival)
// Files: (FrozenStatue, SimulationEngine, DancingBadger, StarshipRobot)
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
 * Winter Carnival class extends the SimulationEngine class, uses all its methods and makes it so
 * the winter carnival can display images.
 *
 * @author Nicole Welsh and Rowan Kulp
 */
public class WinterCarnival extends SimulationEngine {
  // only field
  private ArrayList<FrozenStatue> objects;

  /**
   * Main method calls the update to display all the images.
   *
   * @param args java command line arguments
   */
  public static void main(String[] args) {
    // displays the blue screen
    WinterCarnival display = new WinterCarnival();
  }

  /**
   * Overridden method from the SimulationEngine class that allows the program to draw an image.
   */
  @Override
  public void update() {
    // test given that draws bucky at the center of the screen
    // draw("images" + File.separator + "dancingBadger.png", 500, 300, false);
    // loop to update the images
    for (FrozenStatue s : this.objects) {
      s.update(this);
    }
  }

  /**
   * WinterCarnival constructor that adds images to the display
   */
  public WinterCarnival() {
    // created DanceStep variable to help assign moves to each index
    DanceStep left = DanceStep.valueOf("Left");
    DanceStep right = DanceStep.valueOf("Right");
    DanceStep up = DanceStep.valueOf("Up");
    DanceStep down = DanceStep.valueOf("Down");
    // dance moves
    DanceStep[] dance =
        new DanceStep[] {left, right, right, left, down, left, right, right, left, up};

    // adds frozen statue images
    this.objects = new ArrayList<FrozenStatue>();
    this.objects.add(new FrozenStatue(new float[] {600, 100}));
    this.objects.add(new FrozenStatue(new float[] {200, 500}));

    // adds moving starship images
    this.objects.add(new StarshipRobot(new float[][] {{0, 0}, {600, 100}}));
    this.objects.add(new StarshipRobot(new float[][] {{800, 300}, {200, 500}}));

    // adds moving dancing badgers
    this.objects.add(new DancingBadger(new float[] {304, 268}, dance));
    this.objects.add(new DancingBadger(new float[] {368, 268}, dance));
    this.objects.add(new DancingBadger(new float[] {432, 268}, dance));
    this.objects.add(new DancingBadger(new float[] {496, 268}, dance));

  }

}
