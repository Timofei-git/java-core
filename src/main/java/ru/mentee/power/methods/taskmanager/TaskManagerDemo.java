package ru.mentee.power.methods.taskmanager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TaskManagerDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TaskManager taskManager = new TaskManager();

        taskManager.addTask("Do homework", "You should do maths", createDate(2025, 8, 14), Task.Priority.HIGH);
        taskManager.addTask("Wash the dishes", "You should wash the cup", createDate(2025, 8, 28), Task.Priority.LOW);
        taskManager.addTask("Do sport", "You should do exercises", createDate(2025, 6, 14), Task.Priority.MEDIUM);

        System.out.print("Enter task ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Task task = taskManager.getTaskById(id);
        if (task != null) {
           task.toString();
        } else {
            System.out.println("Task not found");
        }

        System.out.println("\nTasks sorted by due date:");
        printTaskList(taskManager.sortTasksByDueDate());

        System.out.println("\nTasks sorted by priority:");
        printTaskList(taskManager.sortTasksByPriority());

        taskManager.getTaskById(1).markAsCompleted();
        System.out.println("\nCompleted tasks:");
        printTaskList(taskManager.getCompletedTasks());
    }

    private static Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    // Метод для печати списка задач в нормальном виде
    private static void printTaskList(List<Task> tasks) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Task task : tasks) {
            System.out.println(
                    "ID=" + task.getId() +
                            ", Title='" + task.getTitle() + "'" +
                            ", Due=" + sdf.format(task.getDueDate()) +
                            ", Priority=" + task.getPriority() +
                            ", Completed=" + task.isCompleted()
            );
        }
    }
}
