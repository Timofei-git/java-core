package ru.mentee.power.methods.library;

public class Book {
    private String title;
    private String author;
    private int year;
    private boolean available;

    /**
     * Создает новую книгу
     * @param title название книги
     * @param author автор книги
     * @param year год издания
     */
    public Book(String title, String author, int year) {
        // TODO: Реализовать конструктор
        this.author = author;
        this.title = title;
        this.year = year;
        this.available = true;
    }

    // TODO: Добавить геттеры для всех полей

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setYear(int year){
        this.year = year;
    }

    /**
     * Устанавливает статус доступности книги
     * @param available true, если книга доступна, false, если выдана
     */
    public void setAvailable(boolean available) {
        // TODO: Реализовать метод
        this.available = available;
    }


    /**
     * Получает
     * @return состояние available
     */
    public boolean isAvailable() {
        // TODO: Реализовать метод
        return available;
    }

    /**
     * @return Строковое представление информации о книге
     */
    @Override
    public String toString() {
        // TODO: Реализовать метод
        StringBuilder sb = new StringBuilder();
        sb.append("Book: ").append(title).append("\n");
        sb.append("Author: ").append(author).append("\n");
        sb.append("Year: ").append(year).append("\n");
        sb.append("Is available: ").append(available).append("\n");

        return sb.toString();
    }


}

//@TODO остальные геттеры и сеттеры