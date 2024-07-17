package MainClasses;

import java.util.Stack;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import Interfaces.IBook;
import Interfaces.ILoan;
import Interfaces.IMember;
import Interfaces.ILibrary;
import Interfaces.ISubject;
import Interfaces.IObserver;

/**
 * The Library class represents a singleton instance of a library that manages books and members.
 */
public class Library implements ILibrary, ISubject {
    private static Library instance;
    private Stack<IBook> books;
    private Stack<IMember> members;
    private List<IObserver> observers = new ArrayList<>();

    private Library() {
        books = new Stack<>();
        members = new Stack<>();
    }

    /**
     * Gets the singleton instance of the Library.
     * @return The instance of the Library.
     */
    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    @Override
    public void addBook(IBook book) {
        books.push(book);
        notifyObservers(book);
    }

    @Override
    public void removeBook(IBook book) {
        books.remove(book);
        notifyObservers(book);
    }

    @Override
    public void addMember(IMember member) {
        members.push(member);
    }

    @Override
    public void removeMember(IMember member) {
        members.remove(member);
    }

    @Override
    public void loanBook(IBook book, IMember member) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            ILoan loan = new Loan.Builder()
                    .withBook(book)
                    .withLoanDate(new Date())
                    .build();
            member.addLoan(loan);
            notifyObservers(book);
        }
    }

    @Override
    public void returnBook(IBook book, IMember member) {
        for (ILoan loan : member.getLoans()) {
            if (loan.getBook().equals(book) && loan.getReturnDate() == null) {
                loan.setReturnDate(new Date());
                book.setAvailable(true);
                notifyObservers(book);
            }
        }
    }

    @Override
    public void changeBookStatus(IBook book, boolean isAvailable) {
        book.setAvailable(isAvailable);
        notifyObservers(book);
    }

    @Override
    public String getLibrarySummary() {
        int availableBooks = 0;
        int borrowedBooks = 0;
        for (IBook book : books) {
            if (book.isAvailable()) {
                availableBooks++;
            } else {
                borrowedBooks++;
            }
        }
        return "Library Summary: " +
                "\nAvailable Books: " + availableBooks +
                "\nBorrowed Books: " + borrowedBooks +
                "\nTotal Members: " + members.size();
    }

    @Override
    public IBook cloneBook(IBook book) {
        try {
            return book.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(IBook book) {
        for (IObserver observer : observers) {
            observer.update(book);
        }
    }

    /**
     * Gets the list of books in the library.
     * @return The list of books.
     */
    public List<IBook> getBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Gets the list of members in the library.
     * @return The list of members.
     */
    public List<IMember> getMembers() {
        return new ArrayList<>(members);
    }
}
