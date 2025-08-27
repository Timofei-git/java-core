package ru.mentee.power.methods.taskmanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;

/**
 * Класс для управления задачами
 */
public class TaskManager {
    private List<Task> tasks;
    private int nextId = 1;

    /**
     * Конструктор
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Добавление задачи с полным набором параметров
     */
    public Task addTask(String title, String description, Date dueDate, Task.Priority priority) {
        // TODO: Создать задачу с текущим ID, добавить в список и увеличить nextId
        Task task = new Task(nextId, title, description, dueDate, priority);

        nextId++;


        tasks.add(task);
        return task;
    }

    /**
     * Добавление задачи только с названием (перегрузка)
     */
    public Task addTask(String title) {
        // TODO: Вызвать более полный метод с дефолтными значениями
        Task task = new Task(nextId, title);

        nextId++;

        tasks.add(task);
        return task;
    }

    /**
     * Добавление задачи с названием и описанием (перегрузка)
     */
    public Task addTask(String title, String description) {
        // TODO: Вызвать более полный метод с дефолтными значениями
        Task task = new Task(nextId, title, description);

        nextId++;

        tasks.add(task);
        return task;
    }

    /**
     * Получение задачи по ID
     */
    public Task getTaskById(int id) {
        // TODO: Найти и вернуть задачу с указанным ID

        for (Task task:tasks) {
            if (id == task.getId()) return task;
        }
        return null;
    }

    /**
     * Удаление задачи по ID
     */
    public boolean removeTask(int id) {
        // TODO: Найти и удалить задачу с указанным ID
        Task task = getTaskById(id);
        if (task == null) return false;
        tasks.remove(task);
        return true;
    }

    /**
     * Маркировка задачи как выполненной
     */
    public boolean markTaskAsCompleted(int id) {
        // TODO: Найти задачу и вызвать её метод markAsCompleted()
        Task task = getTaskById(id);
        if (task == null) return false;
        task.markAsCompleted();
        return true;
    }

    /**
     * Получение всех задач
     */
    public List<Task> getAllTasks() {
        // TODO: Вернуть копию списка всех задач
        if (tasks == null) return null;
        return new ArrayList<>(tasks);
    }

    /**
     * Получение выполненных задач
     */
    public List<Task> getCompletedTasks() {
        // TODO: Вернуть список задач, где completed == true
        if (tasks == null) return null;
        List<Task> completed = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getIfCompleted()) {
                completed.add(task);
            }
        }
        return completed;
    }

    /**
     * Получение невыполненных задач
     */
    public List<Task> getIncompleteTasks() {
        // TODO: Вернуть список задач, где completed == false
        if (tasks == null) return null;
        List<Task> incompleted = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.getIfCompleted()) {
                incompleted.add(task);
            }
        }
        return incompleted;
    }

    /**
     * Получение просроченных задач
     */
    public List<Task> getOverdueTasks() {
        // TODO: Вернуть список задач, где isOverdue() == true
        if (tasks == null) return null;
        List<Task> overdue = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isOverdue()) {
                overdue.add(task);
            }
        }
        return overdue;
    }

    /**
     * Получение задач с заданным приоритетом
     */
    public List<Task> getTasksByPriority(Task.Priority priority) {
        // TODO: Вернуть список задач с указанным приоритетом
        if (tasks == null) return null;
        List<Task> tasksByPriority = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority()==priority) {
                tasksByPriority.add(task);
            }
        }
        return tasksByPriority;
    }

    /**
     * Поиск задач по фрагменту названия или описания
     */
    public List<Task> searchTasks(String query) {
        // TODO: Вернуть список задач, содержащих query в названии или описании
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

    /**
     * Сортировка задач по сроку выполнения
     * Использует алгоритм сортировки пузырьком из блока циклов
     */
    public List<Task> sortTasksByDueDate() {
        // TODO: Реализовать сортировку  по дате выполнения
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

    /**
     * Сортировка задач по приоритету
     * Использует алгоритм сортировки вставками из блока циклов
     */
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


    /**
     * Вывод всех задач в консоль
     */
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

    /**
     * Вывод задач с указанным заголовком
     */
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