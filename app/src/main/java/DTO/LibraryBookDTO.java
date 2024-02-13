package DTO;
import model.Book;
import model.Library;

public class LibraryBookDTO {
    private int available;
    private Book book;
    private int checkedOut;
    private String isbn;
    private Library library;
    private int stock;

    public LibraryBookDTO(int available, Book book, int checkedOut, String isbn, Library library, int stock) {
        this.available = available;
        this.book = book;
        this.checkedOut = checkedOut;
        this.isbn = isbn;
        this.library = library;
        this.stock = stock;
    }

    public LibraryBookDTO() {
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(int checkedOut) {
        this.checkedOut = checkedOut;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
