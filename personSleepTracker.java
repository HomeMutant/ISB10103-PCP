import java.util.Scanner;

public class personSleepTracker {
    private int wakeUpHour;
    private int wakeUpMinute;

    // Default constructor
    public personSleepTracker() {
        this.wakeUpHour = 0;
        this.wakeUpMinute = 0;
    }

    // Overloaded constructor
    public personSleepTracker(int wakeUpHour, int wakeUpMinute) {
        this.wakeUpHour = wakeUpHour;
        this.wakeUpMinute = wakeUpMinute;
    }

    // Accessor for wakeUpHour
    public int getWakeUpHour() {
        return wakeUpHour;
    }

    // Mutator for wakeUpHour
    public void setWakeUpHour(int wakeUpHour) {
        this.wakeUpHour = wakeUpHour;
    }

    // Accessor for wakeUpMinute
    public int getWakeUpMinute() {
        return wakeUpMinute;
    }

    // Mutator for wakeUpMinute
    public void setWakeUpMinute(int wakeUpMinute) {
        this.wakeUpMinute = wakeUpMinute;
    }

    // Splits a time string into hours and minutes
    private int[] splitTimeString(String timeString) {
        String[] timeParts = timeString.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        return new int[] { hour, minute };
    }

    // Calculates the time to go to bed based on wake-up time and sleep duration
    public String calculateSleepTime(String wakeUpTime, String sleepDuration) {
        try {
            int[] wakeUpTimeParts = splitTimeString(wakeUpTime);
            int wakeUpHour = wakeUpTimeParts[0];
            int wakeUpMinute = wakeUpTimeParts[1];

            int[] sleepDurationParts = splitTimeString(sleepDuration);
            int sleepHour = sleepDurationParts[0];
            int sleepMinute = sleepDurationParts[1];

            int bedTimeHour = (wakeUpHour - sleepHour + 24) % 24;
            int bedTimeMinute = (wakeUpMinute - sleepMinute + 60) % 60;

            if (wakeUpMinute < sleepMinute) {
                bedTimeHour = (bedTimeHour - 1 + 24) % 24;
            }

            return String.format("%02d:%02d", bedTimeHour, bedTimeMinute);
        } catch (Exception e) {
            return "Invalid input. Please enter the time in HH:M format.";
        }
    }

    // Calculates the wake-up time based on sleep time and sleep duration
    public String calculateWakeUpTime(String sleepTime, String sleepDuration) {
        try {
            int[] sleepTimeParts = splitTimeString(sleepTime);
            int sleepTimeHour = sleepTimeParts[0];
            int sleepTimeMinute = sleepTimeParts[1];

            int[] sleepDurationParts = splitTimeString(sleepDuration);
            int sleepHour = sleepDurationParts[0];
            int sleepMinute = sleepDurationParts[1];

            int wakeUpHour = (sleepTimeHour + sleepHour) % 24;
            int wakeUpMinute = (sleepTimeMinute + sleepMinute) % 60;

            if (sleepTimeMinute + sleepMinute >= 60) {
                wakeUpHour = (wakeUpHour + 1) % 24;
            }

            return String.format("%02d:%02d", wakeUpHour, wakeUpMinute);
        } catch (Exception e) {
            return "Invalid input. Please enter the time in HH:MM format.";
        }
    }

    // Void method to display the menu
    public void displayMenu() {
        System.out.println("\nSleep Length Calculator (24-Hour Format)");
        System.out.println("1. Calculate Bedtime");
        System.out.println("2. Calculate Wake-up Time");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    // Void method to handle invalid input
    public void handleInvalidInput() {
        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        personSleepTracker calculator = new personSleepTracker();

        while (true) {
            calculator.displayMenu();
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter time to wake up at (HH:MM): ");
                String wakeUpTime = scanner.nextLine();
                System.out.print("Enter sleep duration (HH:MM): ");
                String sleepDuration = scanner.nextLine();
                System.out.println("You should sleep at: " + calculator.calculateSleepTime(wakeUpTime, sleepDuration));
            } else if (choice.equals("2")) {
                System.out.print("Enter time to sleep at (HH:MM): ");
                String sleepTime = scanner.nextLine();
                System.out.print("Enter sleep duration (HH:MM): ");
                String sleepDuration = scanner.nextLine();
                System.out
                        .println("You should wake up at: " + calculator.calculateWakeUpTime(sleepTime, sleepDuration));
            } else if (choice.equals("3")) {
                System.out.println("Thank you for using the Sleep Length Calculator!");
                break;
            } else {
                calculator.handleInvalidInput();
            }
        }

        scanner.close();
    }
}