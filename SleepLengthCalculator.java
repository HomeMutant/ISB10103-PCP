import java.util.Scanner;

public class SleepLengthCalculator {

    private int wakeUpHour;
    private int wakeUpMinute;

    // Default constructor
    public SleepLengthCalculator() {
        this.wakeUpHour = 0;
        this.wakeUpMinute = 0;
    }

    // Overloaded constructor
    public SleepLengthCalculator(int wakeUpHour, int wakeUpMinute) {
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

    // Calculates the time to go to bed based on wake-up time and sleep duration
    public String calculateSleepTime(String wakeUpTime, String sleepDuration) {
        String[] wakeUpTimeParts = wakeUpTime.split(":");
        int wakeUpHour = Integer.parseInt(wakeUpTimeParts[0]);
        int wakeUpMinute = Integer.parseInt(wakeUpTimeParts[1]);

        String[] sleepDurationParts = sleepDuration.split(":");
        int sleepHour = Integer.parseInt(sleepDurationParts[0]);
        int sleepMinute = Integer.parseInt(sleepDurationParts[1]);

        int bedTimeHour = (wakeUpHour - sleepHour + 24) % 24;
        int bedTimeMinute = (wakeUpMinute - sleepMinute + 60) % 60;

        if (wakeUpMinute < sleepMinute) {
            bedTimeHour = (bedTimeHour - 1 + 24) % 24;
        }

        return String.format("%02d:%02d", bedTimeHour, bedTimeMinute);
    }

    // Calculates the wake-up time based on sleep time and sleep duration
    public String calculateWakeUpTime(String sleepTime, String sleepDuration) {
        String[] sleepTimeParts = sleepTime.split(":");
        int sleepTimeHour = Integer.parseInt(sleepTimeParts[0]);
        int sleepTimeMinute = Integer.parseInt(sleepTimeParts[1]);

        String[] sleepDurationParts = sleepDuration.split(":");
        int sleepHour = Integer.parseInt(sleepDurationParts[0]);
        int sleepMinute = Integer.parseInt(sleepDurationParts[1]);

        int wakeUpHour = (sleepTimeHour + sleepHour) % 24;
        int wakeUpMinute = (sleepTimeMinute + sleepMinute) % 60;

        if (sleepTimeMinute + sleepMinute >= 60) {
            wakeUpHour = (wakeUpHour + 1) % 24;
        }

        return String.format("%02d:%02d", wakeUpHour, wakeUpMinute);
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
        SleepLengthCalculator calculator = new SleepLengthCalculator();

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