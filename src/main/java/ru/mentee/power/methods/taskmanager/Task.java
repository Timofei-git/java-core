package ru.mentee.power.methods.taskmanager;

import java.util.Date;

/**
 * Класс, представляющий задачу
 */
public class Task {
    private int id;             // Уникальный идентификатор
    private String title;       // Название задачи
    private String description; // Описание задачи
    private Date dueDate;       // Срок выполнения
    private Priority priority;  // Приоритет
    private boolean completed = false;  // Статус выполнения

    /**
     * Приоритет задачи
     */
    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    /**
     * Конструктор с полным набором параметров
     */
    public Task(int id, String title, String description, Date dueDate, Priority priority) {
        // TODO: Реализовать конструктор
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    /**
     * Конструктор с минимальным набором параметров (перегрузка)
     */
    public Task(int id, String title) {
        // TODO: Реализовать конструктор с установкой значений по умолчанию
        this.id = id;
        this.title = title;
    }

    /**
     * Конструктор с частичным набором параметров (перегрузка)
     */
    public Task(int id, String title, String description) {
        // TODO: Реализовать конструктор с установкой значений по умолчанию
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // TODO: Реализовать геттеры и сеттеры для всех полей

    /**
     * Метод для получения ID задачи
     */
    public int getId() {
        // TODO: Реализовать метод
        return id;
    }

    public boolean isCompleted(){
        return completed;
    }
    /**
     * Метод для получения названия задачи
     */
    public String getTitle() {
        // TODO: Реализовать метод
        return title;
    }

    public String getDescription() {
        // TODO: Реализовать метод
        return description;
    }

    public Date getDueDate() {
        // TODO: Реализовать метод
        return dueDate;
    }

    public Priority getPriority() {
        // TODO: Реализовать метод
        return priority;
    }

    public boolean getIfCompleted() {
        // TODO: Реализовать метод
        return completed;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setDescription(String description) {
        this.description = description;

    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    // TODO: Реализовать остальные геттеры и сеттеры

    /**
     * Метод для маркировки задачи как выполненной
     */
    public void markAsCompleted() {
        // TODO: Реализовать метод
            setCompleted(true);
    }

    /**
     * Метод для проверки, просрочена ли задача
     */
    public boolean isOverdue() {
        // TODO: Сравнить текущую дату с датой выполнения
        // Если дата выполнения раньше текущей, задача просрочена

        if (dueDate.before(new Date())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Переопределение метода toString для удобного отображения задачи
     */
    @Override
    public String toString() {
        // TODO: Реализовать метод
        StringBuilder str = new StringBuilder();
        str.append("Id: " + id + "\n");
        str.append("Title: " + title + "\n");
        str.append("Description: " + description + "\n");
        str.append("Date of end: " + dueDate + "\n");
        str.append("Is completed: " + completed + "\n");
        return str.toString();
    }
}