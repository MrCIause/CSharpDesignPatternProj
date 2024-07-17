package Interfaces;

/**
 * Interface for librarian operations in the library.
 */
public interface ILibrarian {
    void addBook(IBook book);
    void removeBook(IBook book);
    void addMember(IMember member);
    void removeMember(IMember member);
    void loanBook(IBook book, IMember member);
    void returnBook(IBook book, IMember member);
}

