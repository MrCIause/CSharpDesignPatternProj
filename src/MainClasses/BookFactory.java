package MainClasses;

import Interfaces.IBook;
import Interfaces.IBookFactory;
/**
 * Factory class for creating Book instances.
 */
public class BookFactory implements IBookFactory {
    @Override
    public IBook createBook(String title, String author, int publicationYear, boolean isAvailable) {
        return new Book(title, author, publicationYear, isAvailable);
    }
}

