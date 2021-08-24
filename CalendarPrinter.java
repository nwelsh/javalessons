//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: (Calendar Printer)
// Files: (Calendar Printer and Calendar Tester)
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
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Calendar Printer is a program that prints the contents of a calendar. It can show days, months,
 * and years.
 *
 * @author Nicole Welsh and Rowan Kulp
 */
public class CalendarPrinter {

  /**
   * * Driver for the CalendarPrinter Application. This program asks the user to enter * an initial
   * month, an initial year, and the total number of months to create and * display in calendar
   * form. * @param args is not used by this program
   */
  public static void main(String[] args) { // print welcome message
    Scanner in = new Scanner(System.in);
    System.out.println("Welcome to the Calendar Printer.");
    System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");


    // read input from the user S
    System.out.print("Enter the month to begin calendar: ");

    String monthString = in.nextLine().trim();
    System.out.print("Enter the year to begin calendar: ");
    String yearString = in.nextLine().trim();
    System.out.print("Enter the number of months to include in this calendar: ");
    String countString = in.nextLine().trim();

    // convert user input into usable form
    monthString = monthString.substring(0, 3).toUpperCase();
    MonthOfYear month = null;
    for (int i = 0; i < MonthOfYear.values().length; i++) {
      if (monthString.equals(MonthOfYear.values()[i].name().substring(0, 3).toUpperCase())) {
        month = MonthOfYear.values()[i];
      }
    }
    Year year = new Year(Integer.parseInt(yearString.trim()));
    int count = Integer.parseInt(countString.trim());

    // create months and display them in calendar form
    System.out.println();

    createAndPrintMonths(month, year, count);

    // display thank you message
    System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
    System.out.println("Thanks, and have a nice day.");
    in.close();
  }

  /**
   * Calculates the number of centuries (rounded down) between year 0 and the specified year. For
   * example, the year 2034 has the century index of 20 (as do the other years 2000-2099).
   *
   * @param year to compute the century offset for
   * @return number of centuries between year 0 and the specified year
   */
  public static int fullCenturiesContained(Year year) {
    // uses .intValue from Year class to call the number of years
    int centuries = year.intValue() / 100;
    return centuries;
  } // Test method: True



  /**
   * Calculates the number of years between the specified year and the first year of its century.
   * For example, the year 2034 has the offset within its century of 34 (as do 1234 and 9999934).
   *
   * @param year to compute the offset within century for
   * @return number of years between the specified year and the first year of century
   */
  public static int yearOffsetWithinCentury(Year year) {
    int yearsToCentury = year.intValue() % 100;
    // uses modulo to find the remaining years of the century
    return yearsToCentury;
  }



  /**
   * This method computes whether the specified year is a leap year or not.
   *
   * @param year is the year is being checked for leap-year-ness
   * @return true when the specified year is a leap year, and false otherwise
   */
  public static boolean isLeapYear(Year year) {
    // leap years are divisible by 4, 100, and 400. If it passes all the parameters it is true.
    if (year.intValue() % 4 == 0 && year.intValue() % 100 != 0) {
      return true;
    }
    if (year.intValue() % 100 == 0 && year.intValue() % 400 == 0) {
      return true;
    }
    return false;
  }

  /**
   * Calculates the number of days in the specified month, while taking into consideration whether
   * or not the specified month is in a leap year.
   *
   * @param month to determine the number of days within (within its year)
   * @return the number of days in the specified month (between 28-31)
   */
  public static int numberOfDaysInMonth(Month month) {
    int numDays;
    // months that have 30 days
    if (month.getMonthOfYear() == MonthOfYear.April || month.getMonthOfYear() == MonthOfYear.June
        || month.getMonthOfYear() == MonthOfYear.September
        || month.getMonthOfYear() == MonthOfYear.November) {
      numDays = 30;
      // tests if the year is leap year
    } else if (isLeapYear(month.getYear()) == true
        && month.getMonthOfYear() == MonthOfYear.February) {
      numDays = 29;
    } else if (MonthOfYear.February == month.getMonthOfYear()) {
      numDays = 28;
    } else {
      numDays = 31;
    }
    return numDays;
  }



  /**
   * Calculates which day of the week the first day of the specified month falls on. Note: that this
   * is calculated based on the month's monthOfYear and year, and is NOT retrieved from the month's
   * getDayByDate(1) day. This is because this method is used to generate the day objects that are
   * stored within each month.
   *
   * @param month within which we are calculate the day of week for the first day
   * @return DayOfWeek corresponding to the first day within the specified month
   */
  public static DayOfWeek calcFirstDayOfWeekInMonth(Month month) {
    // new variables for clarity in equation
    int dayIndex;
    int year = month.getYear().intValue();
    int q = 0;
    int m = monthNumber(month.getMonthOfYear());
    if (m <= 2) {
      m += 12;
      year--;
    }
    Year year2 = new Year(year);
    int k = yearOffsetWithinCentury(year2);
    int j = fullCenturiesContained(year2);
    // equation from write up
    dayIndex = (q + 13 * (m + 1) / 5 + k + k / 4 + j / 4 + 5 * j) % 7;
    return intToDay(dayIndex);
  }

  /**
   * A helper method that converts the month of the year from a month to a number corresponding. Ex:
   * January = 0, Feb = 1;
   *
   * @param monthString the month of the year
   * @return number of the month
   */
  private static int monthNumber(MonthOfYear monthString) {
    MonthOfYear[] months = MonthOfYear.values();
    // converts each month to the number
    for (int i = 0; i < months.length; i++) {
      if (months[i] == monthString) {
        return i + 1;
      }
    }
    return -1;
    // as default

  }

  /**
   * A helper method that converts the day of the week from an int back to the day.
   *
   * @param num the day of the week in int
   * @return the day of the week
   */
  private static DayOfWeek intToDay(int num) {
    // array to put the days into

    DayOfWeek[] days = DayOfWeek.values();
    if (num == 0) {
      return days[6]; // Sunday
    }
    if (num == 1) {
      return days[0]; // Monday
    }
    if (num == 2) {
      return days[1]; // Tuesday
    }
    if (num == 3) {
      return days[2]; // Wednesday
    }
    if (num == 4) {
      return days[3]; // Thursday
    }
    if (num == 5) {
      return days[4]; // Friday
    }
    if (num == 6) {
      return days[5]; // Saturday
    }
    return null;
  } // To be used in calcFirstDayOfWeekInMonth

  /**
   * Calculates the day of the week that follows the specified day of week. For example, Thursday
   * comes after Wednesday, and Monday comes after Sunday.
   *
   * @param dayBefore is the day of week that comes before the day of week returned
   * @return day of week that comes after dayBefore
   */
  public static DayOfWeek dayOfWeekAfter(DayOfWeek dayBefore) {
    int index = 0;
    DayOfWeek[] days = DayOfWeek.values();
    // calculates the day after
    for (int i = 0; i < days.length; i++) {
      if (days[i] == dayBefore) {
        index = i;
      }
    }
    // if the day is sunday, loops to monday
    if (index == days.length - 1) {
      index = 0;
    } else {
      index++;
    }
    return days[index];
  }



  /**
   * Calculates the month of the year that follows the specified month. For example, July comes
   * after June, and January comes after December.
   *
   * @param monthBefore is the month that comes before the month that is returned
   * @return month of year that comes after monthBefore
   */
  public static MonthOfYear monthOfYearAfter(MonthOfYear monthBefore) {
    int index = 0;
    MonthOfYear[] months = MonthOfYear.values();
    // calculates the month after
    for (int i = 0; i < months.length; i++) {
      if (months[i] == monthBefore) {
        index = i;
      }
    }
    // if December, loops to January
    if (index == months.length - 1) {
      index = 0;

    } else {
      index++;
    }

    return months[index];
  }



  /**
   * Creates a new month object and fully initializes with its corresponding days.
   *
   * @param monthOfYear which month of the year this new month represents
   * @param year        in which this month is a part
   * @return reference to the newly created month object
   */
  public static Month createNewMonth(MonthOfYear monthOfYear, Year year) {
    // new objects to create the new month
    Month mon = new Month(monthOfYear, year);
    DayOfWeek dow = calcFirstDayOfWeekInMonth(mon);
    // puts in all the parameters to create a new month
    int day = 0;
    for (int i = 0; i < numberOfDaysInMonth(mon); i++) {
      mon.addDay(new Day(dow, day, mon));
      day++;
      dow = dayOfWeekAfter(dow);
    }
    return mon;
  }



  /**
   * Prints the contents of the specified month object in calendar form. This printout should begin
   * with the Month an year of the month. The next line should contain the three letter
   * abbreviations for the seven days of the week. And then the dates of each day of that month
   * should follow: one week per line, with periods in positions of days that are a part of the
   * month before or after. For example, January 2020 should be printed as follows:
   *
   * January 2020 MON TUE WED THU FRI SAT SUN . . 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
   * 21 22 23 24 25 26 27 28 29 30 31 . .
   *
   * @param month which is to be printed by this method
   */
  public static void printMonth(Month month) {
    // aligns the dates with day of week
    System.out.println(month.toString());
    System.out.println("MON TUE WED THU FRI SAT SUN");
    int dayOfMonth = 1;
    DayOfWeek[] days = DayOfWeek.values();
    Boolean passed = false;
    // First Week
    for (int i = 0; i < 7; i++) {
      if (calcFirstDayOfWeekInMonth(month) == days[i]) {
        passed = true;
      }
      if (calcFirstDayOfWeekInMonth(month) != days[i] && !passed) {
        System.out.print(". ");
      } else if (passed) {
        passed = true;
        System.out.print(dayOfMonth + " ");
        dayOfMonth++;
      }
    }

    // Weeks after the first week
    int limit = ((numberOfDaysInMonth(month) - dayOfMonth) / 7);
    System.out.println();
    for (int i = 0; i < limit; i++) {
      for (int j = 0; j < 7; j++) {
        System.out.print(dayOfMonth + " ");
        dayOfMonth++;
      }
      System.out.println();
    }

    // Last week
    for (int i = 0; i < 7; i++) {
      if (numberOfDaysInMonth(month) - dayOfMonth >= 0) {
        System.out.print(dayOfMonth + " ");
      } else {
        System.out.print(". ");
      }
      dayOfMonth++;
    }
    System.out.println();
  }


  /**
   * Creates an array list of months that are initialized with their full complement of days. Prints
   * out each of these months in calendar form, and then returns the resulting ArrayList.
   *
   * @param month of the year for the first month that is created and printed
   * @param year  that the first month created and printed is a part of
   * @param count is the total number of sequential months created and printed
   */
  public static ArrayList<Month> createAndPrintMonths(MonthOfYear month, Year year, int count) {
    ArrayList<Month> months = new ArrayList<Month>();
    int yearVal = year.intValue();
    // creates the month to print
    for (int i = 0; i < count; i++) {
      months.add(createNewMonth(month, year));
      if (month == MonthOfYear.December) {
        yearVal++;
        Year newYear = new Year(yearVal);
        year = newYear;
      }
      // calls printMonth
      printMonth(months.get(i));
      month = monthOfYearAfter(month);

    }

    return months;
  }
}
