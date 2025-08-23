package ru.mentee.power.methods.library;

public class Library {
    // обратите внимание тут books - это не список а массив
    private Book[] books;
    private int bookCount;

    /**
     * Создает новую библиотеку с заданной вместимостью
     * @param capacity максимальное количество книг
     */
    public Library(int capacity) {
        // TODO: Реализовать конструктор
        books = new Book[capacity];
        bookCount = 0;
    }

    /**
     * Добавляет книгу в библиотеку
     * @param book книга для добавления
     * @return true, если книга добавлена успешно, false, если библиотека переполнена
     */
    public boolean addBook(Book book) {
        // TODO: Реализовать метод
        if (bookCount < books.length) {
            books[bookCount] = book;
            System.out.println("Book was added successfully.");
            bookCount++;
            return true;
        } else {
             return false;
        }
    }

    /**
     * Ищет книгу по названию
     * @param title название книги
     * @return найденная книга или null, если книга не найдена
     */
    public Book findBookByTitle(String title) {
        // TODO: Реализовать метод
        if (books!=null) {
            for (int i = 0; i < bookCount;i++) {
                if (books[i].getTitle().equals(title)) {
                    return books[i];
                }
            }
        }
        return null;
    }

    /**
     * Выдает книгу читателю
     * @param title название книги
     * @return true, если книга успешно выдана, false, если книга не найдена или уже выдана
     */
    public boolean checkoutBook(String title) {
        // TODO: Реализовать метод
        Book book = findBookByTitle(title);

        if (book == null) {
            System.out.println("Unknown Book");
            return false;
        }

        if (!book.isAvailable()) {
            return false;
        }

        book.setAvailable(false);
        return true;
    }

    /**
     * Возвращает книгу в библиотеку
     * @param title название книги
     * @return true, если книга успешно возвращена, false, если книга не найдена или уже доступна
     */
    public boolean returnBook(String title) {
        // TODO: Реализовать метод
        Book book = findBookByTitle(title);

        if (book == null) {
            return false;
        }

        if (book.isAvailable()) {
            return false;
        }

        book.setAvailable(true);
        return true;
    }

    /**
     * Возвращает массив доступных книг
     * @return массив доступных книг
     */
    public Book[] listAvailableBooks() {
        // TODO: Реализовать метод
        if (bookCount == 0) return null;
        int availableCount = 0;
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].isAvailable()) {
                availableCount++;
            }
        }

        Book[] availableBooks = new Book[availableCount];
        int index = 0;

        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].isAvailable()) {
                availableBooks[index] = books[i];
                index++;
            }
        }

        return availableBooks;
    }

    /**
     * Возвращает массив выданных книг
     * @return массив выданных книг
     */
    public Book[] listCheckedOutBooks() {
        // TODO: Реализовать метод
        int checkedOutCount = 0;
        for (int i = 0; i < bookCount; i++) {
            if (!books[i].isAvailable()) {
                checkedOutCount++;
            }
        }

        Book[] unavailableBooks = new Book[checkedOutCount];
        int index = 0;

        for (int i = 0; i < bookCount; i++) {
            if (!books[i].isAvailable()) {
                unavailableBooks[index] = books[i];
                index++;
            }
        }

        return unavailableBooks;
    }
}