package ru.mentee.power.methods.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LibraryTest {

    private Library library;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        library = new Library(10);
        book1 = new Book("War and Peace", "Leo Tolstoy", 1869);
        book2 = new Book("Crime and Punishment", "Fyodor Dostoevsky", 1866);
        book3 = new Book("The Master and Margarita", "Mikhail Bulgakov", 1967);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
    }

    @Test
    void testAddBook() {
        Book newBook = new Book("1984", "George Orwell", 1949);
        assertThat(library.addBook(newBook)).isTrue();

        Book foundBook = library.findBookByTitle("1984");
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getAuthor()).isEqualTo("George Orwell");
    }

    @Test
    void testFindBookByTitle() {
        Book foundBook = library.findBookByTitle("War and Peace");
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getAuthor()).isEqualTo("Leo Tolstoy");
        assertThat(foundBook.getYear()).isEqualTo(1869);

        Book notFoundBook = library.findBookByTitle("Unknown Book");
        assertThat(notFoundBook).isNull();
    }
//
    @Test
    void testCheckoutAndReturnBook() {
        assertThat(book1.isAvailable()).isTrue();

        assertThat(library.checkoutBook("War and Peace")).isTrue();
        assertThat(book1.isAvailable()).isFalse();

        assertThat(library.checkoutBook("War and Peace")).isFalse();

        assertThat(library.returnBook("War and Peace")).isTrue();
        assertThat(book1.isAvailable()).isTrue();

        assertThat(library.returnBook("War and Peace")).isFalse();
    }
//
    @Test
    void testListAvailableAndCheckedOutBooks() {
        assertThat(library.listAvailableBooks()).hasSize(3);
        assertThat(library.listCheckedOutBooks()).isEmpty();

        library.checkoutBook("War and Peace");
        library.checkoutBook("The Master and Margarita");

        assertThat(library.listAvailableBooks()).hasSize(1);
        assertThat(library.listCheckedOutBooks()).hasSize(2);

        library.returnBook("War and Peace");

        assertThat(library.listAvailableBooks()).hasSize(2);
        assertThat(library.listCheckedOutBooks()).hasSize(1);
    }

    @Test
    void testLibraryCapacity() {
        Library smallLibrary = new Library(2);

        assertThat(smallLibrary.addBook(book1)).isTrue();
        assertThat(smallLibrary.addBook(book2)).isTrue();

        assertThat(smallLibrary.addBook(book3)).isFalse();
    }
}