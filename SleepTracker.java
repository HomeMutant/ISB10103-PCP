
// importing libraries
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Scanner;

// personSleepTracker class definition
class personSleepTracker {
    // instance variables
    private int wakeUpHour;
    private int wakeUpMinute;
    private String lastBedTime;
    private String lastWakeUpTime;

    // default constructor
    public personSleepTracker() {
        this.wakeUpHour = 0;
        this.wakeUpMinute = 0;
        this.lastBedTime = "Not set";
        this.lastWakeUpTime = "Not set";
    }

    // overloaded constructor
    public personSleepTracker(int wakeUpHour, int wakeUpMinute) {
        this.wakeUpHour = wakeUpHour;
        this.wakeUpMinute = wakeUpMinute;
        this.lastBedTime = "Not set";
        this.lastWakeUpTime = "Not set";
    }

    // accessor for wakeUpHour
    public int getWakeUpHour() {
        return wakeUpHour;
    }

    // autator for wakeUpHour
    public void setWakeUpHour(int wakeUpHour) {
        this.wakeUpHour = wakeUpHour;
    }

    // accessor for wakeUpMinute
    public int getWakeUpMinute() {
        return wakeUpMinute;
    }

    // mutator for wakeUpMinute
    public void setWakeUpMinute(int wakeUpMinute) {
        this.wakeUpMinute = wakeUpMinute;
    }

    // accessor for lastBedTime
    public String getLastBedTime() {
        return lastBedTime;
    }

    // mutator for lastBedTime
    public void setLastBed(String lastBedTime) {
        this.lastBedTime = lastBedTime;
    }

    // accessor for lastWakeUpTime
    public String getLastWakeUpTime() {
        return lastWakeUpTime;
    }

    // mutator for lastWakeUpTime
    public void setLastWakeUp(String lastWakeUpTime) {
        this.lastWakeUpTime = lastWakeUpTime;
    }

    // splits a time string into hours and minutes
    private int[] splitTimeString(String timeString) {
        String[] timeParts = timeString.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        return new int[] { hour, minute };
    }

    // calculates the time to go to bed based on wake-up time and sleep duration
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

            String bedTime = String.format("%02d:%02d", bedTimeHour, bedTimeMinute);
            setLastBed(bedTime);
            return bedTime;
        } catch (Exception e) {
            return "Invalid input. Please enter the time in HH:MM format.";
        }
    }

    // calculates the wake-up time based on sleep time and sleep duration
    public String calculateWakeUp(String sleepTime, String sleepDuration) {
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

            String wakeUpTime = String.format("%02d:%02d", wakeUpHour, wakeUpMinute);
            setLastWakeUp(wakeUpTime);
            return wakeUpTime;
        } catch (Exception e) {
            return "Invalid input. Please enter the time in HH:MM format.";
        }
    }

    // void method to display the menu
    public void displayMenu() {
        System.out.println("\nSleep Length Calculator (24-Hour Format)");
        System.out.println("1. Calculate Bedtime");
        System.out.println("2. Calculate Wake-up Time");
        System.out.println("3. Show Last Bedtime and Wake-up Time");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // void method to handle invalid input
    public void handleInvalidInput() {
        System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
    }
}

// main class for the Sleep Tracker application
public class SleepTracker {
    public static void main(String[] args) {
        // dictionary to store personSleepTracker objects for each user
        Dictionary<String, personSleepTracker> personsDictionary = new Hashtable<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Create a user using the overloaded constructor
        personSleepTracker defaultUser = new personSleepTracker(6, 30);
        defaultUser.setLastBed("22:00");
        defaultUser.setLastWakeUp("6:30");
        personsDictionary.put("DefaultUser", defaultUser);

        // main loop to keep the application running
        while (running) {
            personSleepTracker user = null;
            Enumeration<String> personNames = personsDictionary.keys();
            System.out.println("Available Users:");
            // display all available users
            while (personNames.hasMoreElements()) {
                String key = personNames.nextElement();
                System.out.println("- " + key);
            }
            // create new username or use existing username
            System.out.print("Key in a username, a new one will be created if it does not exist: ");
            String username = scanner.nextLine();
            user = personsDictionary.get(username);
            if (user == null) {
                System.out.println(username + " not found, creating it now...");
                user = new personSleepTracker();
                personsDictionary.put(username, user);
            }
            user.displayMenu();
            String choice = scanner.nextLine();

            // switch case to handle user choices
            switch (choice) {
                case "1": {
                    System.out.print("Enter time to wake up at (HH:MM): ");
                    String wakeUpTime = scanner.nextLine();
                    System.out.print("Enter sleep duration (HH:MM): ");
                    String sleepDuration = scanner.nextLine();
                    System.out.println("You should sleep at: " + user.calculateSleepTime(wakeUpTime, sleepDuration));
                    break;
                }
                case "2": {
                    System.out.print("Enter time to sleep at (HH:MM): ");
                    String sleepTime = scanner.nextLine();
                    System.out.print("Enter sleep duration (HH:MM): ");
                    String sleepDuration = scanner.nextLine();
                    System.out.println("You should wake up at: " + user.calculateWakeUp(sleepTime, sleepDuration));
                    break;
                }
                case "3": {
                    System.out.println("Last Bedtime: " + user.getLastBedTime());
                    System.out.println("Last Wake-up Time: " + user.getLastWakeUpTime());
                    break;
                }
                case "4":
                    System.out.println("Thank you for using the Sleep Length Calculator!");
                    scanner.close();
                    return;
                default:
                    user.handleInvalidInput();
                    break;
            }
        }
        scanner.close();
    }
}
