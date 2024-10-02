import java.util.Scanner;
public class DayOfTheWeek {
	// Method to check if a year is a leap year
    public static boolean isLeapYear(int year) {
        // A year is a leap year if it is divisible by 4 but not by 100, unless it's also divisible by 400
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        }
        return false;
    }

    // Method to validate the day for the given month and year
    public static boolean isValidDate(int day, int month, int year) {
        // Check if the month is valid
        if (month < 1 || month > 12) {
            return false; // Invalid month
        }

        // Check the number of days in the month
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // If it's a leap year and the month is February, set 29 days for February
        if (isLeapYear(year) && month == 2) {
            daysInMonth[1] = 29;
        }

        // Validate the day based on the month
        if (day < 1 || day > daysInMonth[month - 1]) {
            return false; // Invalid day
        }

        return true; // Valid date
    }

    // Zeller's Congruence algorithm to get the day of the week
    public static String getDayOfWeek(int day, int month, int year) {
        if (month == 1 || month == 2) {
            month += 12;  // Treat January and February as 13 and 14
            year -= 1;
        }

        int q = day;
        int m = month;
        int K = year % 100;  // Year of the century
        int J = year / 100;  // Zero-based century

        // Zeller's Congruence formula
        int h = (q + (13 * (m + 1)) / 5 + K + K / 4 + J / 4 - 2 * J) % 7;

        // Fix negative h values
        if (h < 0) {
            h += 7;
        }

        // Convert Zeller's output to a day string
        String[] days = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        return days[h];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int day, month, year;

        // Input validation for day, month, and year
        while (true) {
            // Get the month and validate it
            System.out.print("Enter the month (1-12): ");
            month = scanner.nextInt();
            if (month < 1 || month > 12) {
                System.out.println("Invalid month! Please enter a month between 1 and 12.");
                continue;
            }

            // Get the year
            System.out.print("Enter the year: ");
            year = scanner.nextInt();

            // Get the day and validate it based on the month and year
            System.out.print("Enter the day: ");
            day = scanner.nextInt();
            if (!isValidDate(day, month, year)) {
                System.out.println("Invalid day for the given month and year! Please enter a valid day.");
                continue;
            }

            // If valid inputs are provided, break the loop
            break;
        }

        // Calculate the day of the week
        String dayOfWeek = getDayOfWeek(day, month, year);

        // Output the result
        System.out.println("The day of the week is: " + dayOfWeek);
    }
}
