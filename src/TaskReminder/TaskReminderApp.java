package TaskReminder;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TaskReminderApp {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final Timer timer = new Timer(true);
    private final String filePath = "D:\\Task Reminder\\storage\\tasksaver.txt"; // File to store tasks

    public TaskReminderApp() {
        loadTasks(); // Load tasks from file on startup
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public void addTask(String name, String timeString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime taskTime = LocalDateTime.parse(timeString, formatter);

            Task task = new Task(name, taskTime);
            tasks.add(task);

            // Schedule the reminder
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Reminder: Task \"" + task.getName() + "\" is due now!");
                }
            }, java.sql.Timestamp.valueOf(taskTime));

            saveTasks(); // Save tasks to file after adding
            System.out.println("Task added successfully: " + task);
        } catch (Exception e) {
            System.out.println("Error: Invalid date/time format. Task not added.");
        }
    }

    public void deleteTask(String taskName) {
        boolean taskFound = false;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getName().equalsIgnoreCase(taskName)) {
                tasks.remove(i);
                taskFound = true;
                saveTasks(); // Save tasks to file after deletion
                System.out.println("Task \"" + taskName + "\" has been deleted.");
                break;
            }
        }

        if (!taskFound) {
            System.out.println("Task \"" + taskName + "\" not found.");
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Your Tasks:");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    private void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadTasks() {
        File file = new File(filePath);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                ArrayList<Task> loadedTasks = (ArrayList<Task>) ois.readObject();
                tasks.addAll(loadedTasks);

                // Reschedule reminders for loaded tasks
                for (Task task : tasks) {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("Reminder: Task \"" + task.getName() + "\" is due now!");
                        }
                    }, java.sql.Timestamp.valueOf(task.getTime()));
                }

                System.out.println("Tasks loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }
        }
    }
}