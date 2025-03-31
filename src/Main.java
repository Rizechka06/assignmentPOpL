import java.util.*;
class Date implements Comparable<Date> {
    int day;
    int month;
    int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public boolean isValidDate() {
        if (year < 1 || month < 1 || month > 12 || day < 1) {
            return false;
        }
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)) {
            daysInMonth[2] = 29;
        }
        return day <= daysInMonth[month];
    }

    public void updateDate(int d, int m, int y) {
        Date tempDate = new Date(d, m, y);
        if (tempDate.isValidDate()) {
            this.day = d;
            this.month = m;
            this.year = y;
        }
    }

    public String getDayOfWeek() {
        // A very simple way, does not accurately account for leap years before 1970
        if (!isValidDate()) {
            return "Invalid Date";
        }
        int totalDays = 0;
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int y = 1970; y < year; y++) {
            totalDays += isLeapYear(y) ? 366 : 365;
        }
        if (isLeapYear(year)) {
            daysInMonth[2] = 29;
        }
        for (int m = 1; m < month; m++) {
            totalDays += daysInMonth[m];
        }
        totalDays += day - 1; // Strting from January 1, 1970 (Thursday.)
        String[] days = {"Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday"};
        return days[totalDays % 7];
    }

    public long calculateDifference(Date otherDate) {
        if (!isValidDate() || !otherDate.isValidDate()) {
            System.out.println("Error: One or both dates are invalid.");
            return -1;
        }
        long days1 = toDays();
        long days2 = otherDate.toDays();
        return Math.abs(days1 - days2);
    }

    private long toDays() {
        long totalDays = 0;
        for (int y = 1; y < year; y++) {
            totalDays += isLeapYear(y) ? 366 : 365;
        }
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)) {
            daysInMonth[2] = 29;
        }
        for (int m = 1; m < month; m++) {
            totalDays += daysInMonth[m];
        }
        totalDays += day;
        return totalDays;
    }

    public void printDate() {
        if (isValidDate()) {
            String[] monthNames = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            System.out.println(monthNames[month] + " " + day + ", " + year);
        }
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public int compareTo(Date other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        }
        if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        }
        return Integer.compare(this.day, other.day);
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating Date objects
        Date date1 = new Date(15, 10, 2023);
        Date date2 = new Date(29, 2, 2024); // Leap year
        Date date3 = new Date(30, 2, 2023); // Invalid date
        Date date4 = new Date(1, 1, 2023);
        Date date5 = new Date(31, 12, 2022);

        // Checking isValidDate()
        System.out.println("Date 1 is: " + date1.isValidDate()); // true
        System.out.println("Date 2 is: " + date2.isValidDate()); // true
        System.out.println("Date 3 is: " + date3.isValidDate()); // false

        // Updating date!!
        date1.updateDate(20, 10, 2023);
        date3.updateDate(28, 2, 2023);
        date3.printDate();

        // Getting the day of the week
        System.out.println("Day of the week for Date 1: " + date1.getDayOfWeek());
        System.out.println("Day of the week for Date 2: " + date2.getDayOfWeek());
        System.out.println("Day of the week for Date 3: " + date3.getDayOfWeek());

        // Calculating the difference between dates
        long difference = date1.calculateDifference(date2);
        if (difference != -1) {
            System.out.println("Difference between Date 1 and Date 2: " + difference + " days.");
        }

        // Printing dates
        System.out.print("Date 1: ");
        date1.printDate();
        System.out.print("Date 2: ");
        date2.printDate();
        System.out.print("Date 3: ");
        date3.printDate();

        // Creating a list of dates
        ArrayList<Date> dateList = new ArrayList<>();
        dateList.add(new Date(5, 5, 2024));
        dateList.add(new Date(1, 1, 2023));
        dateList.add(new Date(10, 3, 2023));
        dateList.add(new Date(20, 12, 2022));

        System.out.println("\nDate list before sorting:");
        for (Date date : dateList) {
            date.printDate();
        }

        // Sorting the list of dates
        Collections.sort(dateList);

        System.out.println("\nDate list after sorting:");
        for (Date date : dateList) {
            date.printDate();
        }

        Date invalidDate1 = new Date(32, 1, 2023);
        invalidDate1.printDate(); // Will output "Invalid date."
    }
}