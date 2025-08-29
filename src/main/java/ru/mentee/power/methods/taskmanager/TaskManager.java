package ru.mentee.power.methods.taskmanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;

public class TaskManager {
    private List<Task> tasks;
    private int nextId = 1;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

   public Task addTask(String title, String description, Date dueDate, Task.Priority priority) {
        Task task = new Task(nextId, title, description, dueDate, priority);
        nextId++;
        tasks.add(task);
        return task;
    }

   public Task addTask(String title) {
        Task task = new Task(nextId, title);
        nextId++;
        tasks.add(task);
        return task;
    }

     public Task addTask(String title, String description) {
        Task task = new Task(nextId, title, description);
        nextId++;
        tasks.add(task);
        return task;
    }

   public Task getTaskById(int id) {
        for (Task task:tasks) {
            if (id == task.getId()) return task;
        }
        return null;
    }

    public boolean removeTask(int id) {
        Task task = getTaskById(id);
        if (task == null) return false;
        tasks.remove(task);
        return true;
    }

    public boolean markTaskAsCompleted(int id) {
        Task task = getTaskById(id);
        if (task == null) return false;
        task.markAsCompleted();
        return true;
    }

    public List<Task> getAllTasks() {
        if (tasks == null) return null;
        return new ArrayList<>(tasks);
    }

    public List<Task> getCompletedTasks() {
        if (tasks == null) return null;
        List<Task> completed = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getIfCompleted()) {
                completed.add(task);
            }
        }
        return completed;
    }

   public List<Task> getIncompleteTasks() {
        if (tasks == null) return null;
        List<Task> incompleted = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.getIfCompleted()) {
                incompleted.add(task);
            }
        }
        return incompleted;
    }

   public List<Task> getOverdueTasks() {
        if (tasks == null) return null;
        List<Task> overdue = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isOverdue()) {
                overdue.add(task);
            }
        }
        return overdue;
    }

   public List<Task> getTasksByPriority(Task.Priority priority) {
        if (tasks == null) return null;
        List<Task> tasksByPriority = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority()==priority) {
                tasksByPriority.add(task);
            }
        }
        return tasksByPriority;
    }

   public List<Task> searchTasks(String query) {
        if (tasks == null)  return Collections.emptyList();
        List<Task> searchTasks = new ArrayList<>();
        for (Task task : tasks) {
            if ((task.getTitle() != null && task.getTitle().contains(query)) ||
                    (task.getDescription() != null && task.getDescription().contains(query))) {
                searchTasks.add(task);
            }
        }
        return searchTasks;
    }

    public List<Task> sortTasksByDueDate() {
        if (tasks == null) return null;
        List<Task> sorted = new ArrayList<>();
        for (int i = 0;i < sorted.size() - 1;i++) {
            for (int j = 0;j < sorted.size() - i - 1;j++){
                Task t1 = sorted.get(j);
                Task t2 = sorted.get(j + 1);

                if (t1.getDueDate().after(t2.getDueDate())) {
                    sorted.set(j, t2);
                    sorted.set(j + 1, t1);
                }
            }
        }
        return sorted;
    }

    public List<Task> sortTasksByPriority() {
        List<Task> sortedTasks = new ArrayList<>(tasks);

        for (int i = 1; i < sortedTasks.size(); i++) {
            Task current = sortedTasks.get(i);
            int j = i - 1;

            while (j >= 0
                    && comparePriorities(sortedTasks.get(j).getPriority(), current.getPriority()) > 0) {
                sortedTasks.set(j + 1, sortedTasks.get(j));
                j--;
            }

            sortedTasks.set(j + 1, current);
        }

        return sortedTasks;
    }

    private int comparePriorities(Task.Priority p1, Task.Priority p2) {
        if (p1 == null && p2 == null) {
            return 0;
        }
        if (p1 == null) {
            return 1;
        }
        if (p2 == null) {
            return -1;
        }
        return p2.ordinal() - p1.ordinal();
    }


    public void printAllTasks() {
        // TODO: Вывести все задачи в консоль в читаемом формате
        if (tasks == null) {
            System.out.println("No tasks");
            return;
        }
        for (Task task:tasks) {
           System.out.println(task);
        }
    }

   public void printTasks(List<Task> taskList, String header) {
        // TODO: Вывести задачи из списка с заголовком
        if (tasks == null) {
            System.out.println("No tasks");
            return;
        }
        for (Task task:taskList) {
            if (task.getTitle().equals(header)) {
                System.out.println(task);
            }
        }
    }
}