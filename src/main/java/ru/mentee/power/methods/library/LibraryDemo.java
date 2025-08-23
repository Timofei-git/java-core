package ru.mentee.power.methods.library;

public class LibraryDemo {
    public static void main(String[] args) {
        Library library = new Library(5);

        Book book1 = new Book("War and Peace", "Leo Tolstoy", 1869);
        Book book2 = new Book("Crime and Punishment", "Fyodor Dostoevsky", 1866);
        Book book3 = new Book("The Master and Margarita", "Mikhail Bulgakov", 1967);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        System.out.println("Available books:\n");
        for (Book b : library.listAvailableBooks()) {
            System.out.println(b.toString());
        }

        library.checkoutBook("War and Peace");
        library.checkoutBook("The Master and Margarita");

        System.out.println("\nAfter checkout:");
        System.out.println("Available books:");
        for (Book b : library.listAvailableBooks()) {
            System.out.println(b.toString());
        }

        System.out.println("Checked out books:");
        for (Book b : library.listCheckedOutBooks()) {
            System.out.println(b.toString());
        }

        library.returnBook("War and Peace");
        library.returnBook("The Master and Margarita");

        System.out.println("\nAfter return:");
        System.out.println("Available books:");
        for (Book b : library.listAvailableBooks()) {
            System.out.println(b.toString());
        }

        System.out.println("Checked out books:");
        for (Book b : library.listCheckedOutBooks()) {
            System.out.println(b.toString());
        }
    }
}
