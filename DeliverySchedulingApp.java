import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This application help manage the scheduling of food delivery orders. It tracks students waiting
 * for orders to be delivered and robots available to make those deliveries, and prioritizes the
 * scheduling of deliveries between students and robots that are near by one another.
 */
public class DeliverySchedulingApp {

  private DeliveryQueue distances; // used to prioritize and schedule deliveries for fulfillment
  private List<Student> students; // list of students waiting for an order to be delivered
  private List<FoodRobot> foodRobots; // list of robots that are available to make deliveries

  /**
   * In addition to the prioritizing DeliveryQueue, this constructor initializes empty lists of
   * waiting students and available robots.
   */
  public DeliverySchedulingApp() {
    this.distances = new DeliveryQueue();
    this.students = new ArrayList<>();
    this.foodRobots = new ArrayList<>();
  }

  /**
   * Adds the specified student to the collection of waiting students, and also adds possible
   * deliveries from every available robot to this student to the DeliveryQueue.
   *
   * @param newStudent that is being added
   */
  public void newStudentOrder(Student newStudent) {
    this.students.add(newStudent);
    for (FoodRobot robot : this.foodRobots) {
      this.distances.offerDelivery(new Delivery(newStudent, robot));
    }
    System.out.println("Adding Student: " + newStudent.getID());
  }

  /**
   * Adds the specified robot the the collection of available robots, and also adds possible
   * deliveries from this robot to every waiting student to the DeliveryQueue.
   *
   * @param newRobot that is being added
   */
  public void newRobotAvailable(FoodRobot newRobot) {
    this.foodRobots.add(newRobot);
    for (Student student : this.students) {
      this.distances.offerDelivery(new Delivery(student, newRobot));
    }
    System.out.println("Adding Robot: " + newRobot.getName());
  }

  /**
   * Fulfills the highest priority delivery within the delivery queue. This removes the student and
   * foodRobot associated with this delivery from their respective ArrayLists, and also removes all
   * deliveries associated with either from the DeliveryQueue.
   */
  public void fulfillOrder() {
    Delivery nextOrder = this.distances.pollBestDelivery();
    // Note that in order for this to work, the Delivery.equals() method must be defined to
    // take Student and FoodRobot arguments and return true when the have a matching id/name.
    this.students.remove(nextOrder);
    this.foodRobots.remove(nextOrder);
    System.out.println("Fulfilling Order: " + nextOrder.toString());
  }

  /**
   * Processes a single command based on the first character in the command string. Valid command
   * options include: Add [S]tudent: s <x> <y> <id> Add Food[R]obot: r <x> <y> <name> Fulfill [N]ext
   * Delivery: n Fulfill [A]ll Possible Deliveries: a [P]rint waiting students and available robots:
   * p [Q]uit: q
   *
   * @param command is the command to process
   */
  public void processCommand(String command) {
    try {
      switch (command.charAt(0)) {
        case 's': // Add [S]tudent: s <x> <y> <id>
        case 'S': {
          String[] parts = command.substring(1).trim().split(" ");
          int x = Integer.parseInt(parts[0]);
          int y = Integer.parseInt(parts[1]);
          int id = Integer.parseInt(parts[2]);
          this.newStudentOrder(new Student(x, y, id));
          break;
        }
        case 'r': // Add Food[R]obot: r <x> <y> <name>
        case 'R': {
          String[] parts = command.substring(1).trim().split(" ");
          int x = Integer.parseInt(parts[0]);
          int y = Integer.parseInt(parts[1]);
          String name = parts[2];
          this.newRobotAvailable(new FoodRobot(x, y, name));
          break;
        }
        case 'n': // Fulfill [N]ext Delivery: n
        case 'N':
          this.fulfillOrder();
          break;
        case 'a': // Fulfill [A]ll Possible Deliveries: a
        case 'A':
          while (!this.distances.isEmpty()) {
            this.fulfillOrder();
          }
          break;
        case 'p': // [P]rint waiting students and available robots: p
        case 'P':
          System.out.println("Students waiting: " + this.students);
          System.out.println("Robots available: " + this.foodRobots);
          break;
        case 'Q': // [Q]uit: q
        case 'q':
          break;
        default:
          System.out.println("Warning - Command not recognized: " + command);
          break;
      }
    } catch (RuntimeException e) {
      System.out.println("Warning - Unable to process one or more command arguments: " + command);
      e.printStackTrace();
    }
  }

  /**
   * Reads through and process commands stored on each line in the provided text file. Empty lines
   * are ignored, and no prompts are printed out between commands.
   *
   * @param file references the text file that commands are read from
   */
  public void processFileOfCommands(File file) {
    Scanner fin = null;
    try {
      fin = new Scanner(file);
      while (fin.hasNextLine()) {
        String nextLine = fin.nextLine().trim();
        if (nextLine.length() > 0) {
          this.processCommand(nextLine);
        }
      }
      fin.close();
    } catch (FileNotFoundException e) {
      System.out.println("Warning - Unable to read from file: " + file);
    } finally {
      if (fin != null) {
        fin.close();
      }
    }
  }

  /**
   * Repeatedly processes commands, until the quit command is encountered. In addition to the
   * commands that processCommand can handle, this method can also load and run a preset sequence of
   * commands from a file: Process [F]ile Commands: f <filename>
   *
   * @param in is input source that commands are read from, one line at a time
   */
  public void run(Scanner in) {
    String command = "not Q";
    while (command.charAt(0) != 'q' && command.charAt(0) != 'Q') { // quit command ends this loop
      this.displayOptionsMenu();
      command = in.nextLine().trim(); // read command

      // process file of commands
      if (command.charAt(0) == 'f' || command.charAt(0) == 'F') {
        File commandFile = new File(command.substring(1).trim());
        this.processFileOfCommands(commandFile);
      } else {
        this.processCommand(command);
      }
    }
  }

  /**
   * Displays command options to System.out.
   */
  private void displayOptionsMenu() {
    String display = "Command options:\n" + "  Add [S]tudent: s <x> <y> <id>\n"
        + "  Add Food[R]obot: r <x> <y> <name>\n" + "  Fulfill [N]ext Delivery: n\n"
        + "  Fulfill [A]ll Possible Deliveries: a\n"
        + "  [P]rint waiting students and available robots: p\n"
        + "  Process [F]ile Commands: f <filename>\n" + "  [Q]uit: q\n" + "Please enter command: ";

    System.out.print(display);
  }

  /**
   * Entry point for this application: creates and runs a new DeliverySchedulingApp, with a Scanner
   * that is reading from System.in.
   *
   * @param args is not used by this program
   */
  public static void main(String[] args) {
    new DeliverySchedulingApp().run(new Scanner(System.in));
  }

}
