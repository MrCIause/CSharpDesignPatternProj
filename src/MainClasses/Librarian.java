package MainClasses;

import Interfaces.IBook;
import Interfaces.ILibrarian;
import Interfaces.IMember;
import Interfaces.ILibrary;

/**
 * The Librarian class provides a facade for managing books and members in the library.
 */
public class Librarian implements ILibrarian {
    private ILibrary library;

    /**
     * Constructs a new Librarian instance.
     */
    public Librarian() {
        this.library = Library.getInstance();
    }

    @Override
    public void addBook(IBook book) {
        library.addBook(book);
    }

    @Override
    public void removeBook(IBook book) {
        library.removeBook(book);
    }

    @Override
    public void addMember(IMember member) {
        library.addMember(member);
    }

    @Override
    public void removeMember(IMember member) {
        library.removeMember(member);
    }

    @Override
    public void loanBook(IBook book, IMember member) {
        library.loanBook(book, member);
    }

    @Override
    public void returnBook(IBook book, IMember member) {
        library.returnBook(book, member);
    }
}
