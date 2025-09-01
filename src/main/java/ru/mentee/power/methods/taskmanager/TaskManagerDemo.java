package ru.mentee.power.methods.taskmanager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import ru.mentee.power.methods.taskmanager.Task;
import ru.mentee.power.methods.taskmanager.TaskManager;

import java.util.InputMismatchException;

public class TaskManagerDemo {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {

        initializeTasks();

        boolean running = true;
        System.out.println("Welcome to Task Manager (with basic error handling)!");

        while (running) {
            printMenu();
            int choice = -1;

            try {
                System.out.print("Choose an action (1–5): ");
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input error: Please enter a number for the action.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    addTaskUI();
                    break;
                case 2:
                    markTaskAsCompletedUI();
                    break;
                case 3:
                    removeTaskUI();
                    break;
                case 4:
                    taskManager.printAllTasks();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select between 1 and 5.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void addTaskUI() {
        try {
            System.out.print("Enter task title: ");
            String title = scanner.nextLine();
            Task newTask = taskManager.addTask(title);
            if (newTask != null) {
                System.out.println("Task '" + newTask.getTitle() + "' (ID: " + newTask.getId() + ") successfully added.");
            } else {
                System.out.println("Failed to add task.");
            }
        } catch (Exception e) {
            System.out.println("Unexpected error while adding task: " + e.getMessage());
        }
    }

    private static void markTaskAsCompletedUI() {
        try {
            System.out.print("Enter task ID to mark as completed: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (id <= 0) {
                throw new IllegalArgumentException("Task ID cannot be negative or zero.");
            }

            boolean success = taskManager.markTaskAsCompleted(id);

            if (success) {
                System.out.println("Task with ID " + id + " marked as completed.");
            } else {
                System.out.println("Task with ID " + id + " not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error: Please enter a valid task ID (integer).");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
           } catch (Exception e) {

            System.out.println("Unexpected error while marking task: " + e.getMessage());
        }
    }

    private static void removeTaskUI() {
        try {
            System.out.print("Enter task ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (id <= 0) {
                throw new IllegalArgumentException("Task ID cannot be negative or zero.");
            }

            boolean success = taskManager.removeTask(id);

            if (success) {
                System.out.println("Task with ID " + id + " deleted.");
            } else {
                System.out.println("Task with ID " + id + " not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error: Please enter a valid task ID (integer).");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error while deleting task: " + e.getMessage());
        }
    }

//    private static Date createDate(int year, int month, int day) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, month - 1, day);
//        return calendar.getTime();
//    }
//
//    private static void printTaskList(List<Task> tasks) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        for (Task task : tasks) {
//            System.out.println(
//                    "ID=" + task.getId() +
//                            ", Title='" + task.getTitle() + "'" +
//                            ", Due=" + sdf.format(task.getDueDate()) +
//                            ", Priority=" + task.getPriority() +
//                            ", Completed=" + task.isCompleted()
//            );
//        }
//    }

    private static void initializeTasks() {
        // Let's add a couple of tasks to start with
        taskManager.addTask("Study exceptions", "Understand try-catch-finally", null, Task.Priority.HIGH);
        taskManager.addTask("Practice with TaskManager");
        System.out.println("Initial tasks added.");
        taskManager.printAllTasks();
        System.out.println();
    }

    private static void printMenu() {
        System.out.println("===== MENU =====");
        System.out.println("1. Add task (title only)");
        System.out.println("2. Mark task as completed");
        System.out.println("3. Delete task");
        System.out.println("4. Show all tasks");
        System.out.println("5. Exit");
        System.out.println("==============");
    }

}
