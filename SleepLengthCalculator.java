/*import java.util.Scanner;

public class SleepLengthCalculator {

    public static String calculateSleepTime(String wakeUpTime, String sleepDuration) {
        String[] wakeUpTimeParts = wakeUpTime.split(":");
        int wakeUpHour = Integer.parseInt(wakeUpTimeParts[0]);
        int wakeUpMinute = Integer.parseInt(wakeUpTimeParts[1]);

        String[] sleepDurationParts = sleepDuration.split(":");
        int sleepHour = Integer.parseInt(sleepDurationParts[0]);
        int sleepMinute = Integer.parseInt(sleepDurationParts[1]);

        int bedTimeHour = (wakeUpHour - sleepHour + 24) % 24; // Ensures positive value for hour
        int bedTimeMinute = (wakeUpMinute - sleepMinute + 60) % 60; // Ensures positive value for minute

        return String.format("%02d:%02d", bedTimeHour, bedTimeMinute);
    }

    public static String calculateWakeUpTime(String sleepTime, String sleepDuration) {
        String[] sleepTimeParts = sleepTime.split(":");
        int sleepTimeHour = Integer.parseInt(sleepTimeParts[0]);
        int sleepTimeMinute = Integer.parseInt(sleepTimeParts[1]);

        String[] sleepDurationParts = sleepDuration.split(":");
        int sleepHour = Integer.parseInt(sleepDurationParts[0]);
        int sleepMinute = Integer.parseInt(sleepDurationParts[1]);

        int bedTimeHour = (sleepTimeHour + sleepHour + 24) % 24; // Ensures positive value for hour
        int bedTimeMinute = (sleepTimeMinute + sleepMinute + 60) % 60; // Ensures positive value for minute

        return String.format("%02d:%02d", bedTimeHour, bedTimeMinute);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSleep Length Calculator");
            System.out.println("1. Calculate Bedtime ");
            System.out.println("2. Calculate Wake-up Time ");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter time to wake up at(HH:MM): ");
                String bedTime = scanner.nextLine();
                System.out.print("Enter sleep duration (HH:MM): ");
                String sleepDuration = scanner.nextLine();
                System.out.println("You should sleep at: " + calculateSleepTime(bedTime, sleepDuration));
            } else if (choice.equals("2")) {
                System.out.print("Enter time to sleep at(HH:MM): ");
                String sleepTime = scanner.nextLine();
                System.out.print("Enter sleep duration (HH:MM): ");
                String sleepDuration = scanner.nextLine();
                System.out.println("You should wake up at: " + calculateWakeUpTime(sleepTime, sleepDuration));
            } else if (choice.equals("3")) {
                System.out.println("Thank You For Using Sleep Length Calculator!");
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}*/

import java.util.Scanner;

public class SleepLengthCalculator {

    // Constructor for the SleepLengthCalculator class
    public SleepLengthCalculator() {
        // Any initialization if needed
    }

    // Method to calculate the bedtime based on the wake-up time and sleep duration
    public static String calculateSleepTime(String wakeUpTime, String sleepDuration) {
        String[] wakeUpTimeParts = wakeUpTime.split(":");
        int wakeUpHour = Integer.parseInt(wakeUpTimeParts[0]);
        int wakeUpMinute = Integer.parseInt(wakeUpTimeParts[1]);

        String[] sleepDurationParts = sleepDuration.split(":");
        int sleepHour = Integer.parseInt(sleepDurationParts[0]);
        int sleepMinute = Integer.parseInt(sleepDurationParts[1]);

        int bedTimeHour = (wakeUpHour - sleepHour + 24) % 24; // Ensures positive value for hour
        int bedTimeMinute = (wakeUpMinute - sleepMinute + 60) % 60; // Ensures positive value for minute

        if (wakeUpMinute < sleepMinute) {
            bedTimeHour = (bedTimeHour - 1 + 24) % 24; // Adjust the hour if minutes were negative
        }

        return String.format("%02d:%02d", bedTimeHour, bedTimeMinute);
    }

    // Method to calculate the wake-up time based on the sleep time and sleep
    // duration
    public static String calculateWakeUpTime(String sleepTime, String sleepDuration) {
        String[] sleepTimeParts = sleepTime.split(":");
        int sleepTimeHour = Integer.parseInt(sleepTimeParts[0]);
        int sleepTimeMinute = Integer.parseInt(sleepTimeParts[1]);

        String[] sleepDurationParts = sleepDuration.split(":");
        int sleepHour = Integer.parseInt(sleepDurationParts[0]);
        int sleepMinute = Integer.parseInt(sleepDurationParts[1]);

        int wakeUpHour = (sleepTimeHour + sleepHour) % 24; // Ensures positive value for hour
        int wakeUpMinute = (sleepTimeMinute + sleepMinute) % 60; // Ensures positive value for minute

        if (sleepTimeMinute + sleepMinute >= 60) {
            wakeUpHour = (wakeUpHour + 1) % 24; // Adjust the hour if minutes exceed 60
        }

        return String.format("%02d:%02d", wakeUpHour, wakeUpMinute);
    }

    // Main method to run the application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SleepLengthCalculator calculator = new SleepLengthCalculator();

        while (true) {
            System.out.println("\nSleep Length Calculator");
            System.out.println("1. Calculate Bedtime ");
            System.out.println("2. Calculate Wake-up Time ");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter time to wake up at (HH:MM): ");
                String wakeUpTime = scanner.nextLine();
                System.out.print("Enter sleep duration (HH:MM): ");
                String sleepDuration = scanner.nextLine();
                System.out.println("You should sleep at: " + calculateSleepTime(wakeUpTime, sleepDuration));
            } else if (choice.equals("2")) {
                System.out.print("Enter time to sleep at (HH:MM): ");
                String sleepTime = scanner.nextLine();
                System.out.print("Enter sleep duration (HH:MM): ");
                String sleepDuration = scanner.nextLine();
                System.out.println("You should wake up at: " + calculateWakeUpTime(sleepTime, sleepDuration));
            } else if (choice.equals("3")) {
                System.out.println("Thank You For Using Sleep Length Calculator!");
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}