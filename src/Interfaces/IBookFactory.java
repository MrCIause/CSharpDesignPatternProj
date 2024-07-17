package Interfaces;

/**
 * Factory interface for creating Book instances.
 */
public interface IBookFactory {
    /**
     * Creates a new book instance.
     *
     * @param title           The title of the book.
     * @param author          The author of the book.
     * @param publicationYear The publication year of the book.
     * @param isAvailable     The availability status of the book.
     * @return A new IBook instance.
     */
    IBook createBook(String title, String author, int publicationYear, boolean isAvailable);
}
