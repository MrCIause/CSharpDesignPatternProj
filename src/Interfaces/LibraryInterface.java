package Interfaces;

import MainClasses.Book;
import MainClasses.Librarian;
import MainClasses.Member;

import java.util.Stack;

public interface LibraryInterface {
    void addBook(Book book);
    Book getBook(String bookId);
    void removeBook(String bookId);

    void addMember(Member member);
    Member getMember(String memberId);
    void removeMember(String memberId);

    void loanBook(String bookId, String memberId);
    void returnBook(String bookId);

    void addLibrarian(Librarian librarian);
    Librarian getLibrarian(String librarianId);
}


