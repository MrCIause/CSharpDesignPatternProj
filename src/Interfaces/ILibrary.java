package Interfaces;

/**
 * Interface for library operations.
 */
public interface ILibrary {
    void addBook(IBook book);
    void removeBook(IBook book);
    void addMember(IMember member);
    void removeMember(IMember member);
    void loanBook(IBook book, IMember member);
    void returnBook(IBook book, IMember member);
    void changeBookStatus(IBook book, boolean isAvailable);
    String getLibrarySummary();
    IBook cloneBook(IBook book);
}
