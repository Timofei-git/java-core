package ru.mentee.power.methods.taskmanager;

import java.util.Date;

public class Task {
    private int id;
    private String title;
    private String description;
    private Date dueDate;
    private Priority priority;
    private boolean completed = false;

   public enum Priority {
        LOW, MEDIUM, HIGH
    }

    public Task(int id, String title, String description, Date dueDate, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
    }

   public Task(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }


    /**
     * Метод для получения ID задачи
     */
    public int getId() {
        return id;
    }

    public boolean isCompleted(){
        return completed;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
       return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean getIfCompleted() {
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

    public void markAsCompleted() {
            setCompleted(true);
    }

   public boolean isOverdue() {
        if (dueDate.before(new Date())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Id: " + id + "\n");
        str.append("Title: " + title + "\n");
        str.append("Description: " + description + "\n");
        str.append("Date of end: " + dueDate + "\n");
        str.append("Is completed: " + completed + "\n");
        return str.toString();
    }
}