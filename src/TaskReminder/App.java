package TaskReminder;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        TaskReminderApp taskReminderApp = new TaskReminderApp();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Task Reminder!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter task time (yyyy-MM-dd HH:mm): ");
                    String timeString = scanner.nextLine();

                    taskReminderApp.addTask(name, timeString);
                    break;

                case 2:
                    taskReminderApp.viewTasks();
                    break;
                
                case 3:
                    System.out.println("Enter task to be deleted");
                    String taskName = scanner.nextLine();
                    taskReminderApp.deleteTask(taskName);
                    break;

                case 4:
                    System.out.println("Exiting Task Reminder. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}