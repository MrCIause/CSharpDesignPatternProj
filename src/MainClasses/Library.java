package MainClasses;

import java.util.Date;
import java.util.Stack;
import Interfaces.*;

public class Library implements LibraryInterface {
    private Node<Book> books;
    private Node<Member> members;
    private Node<Loan> loans;
    private Node<Librarian> librarians;

    public Library() {
        this.books = null;
        this.members = null;
        this.loans = null;
        this.librarians = null;
    }

    // Methods to add, remove, and retrieve books
    @Override
    public void addBook(Book book) {
        books = addNode(books, book);
    }

    @Override
    public Book getBook(String bookId) {
        return findNode(books, bookId);
    }

    @Override
    public void removeBook(String bookId) {
        books = removeNode(books, bookId);
    }

    // Methods to add, remove, and retrieve members
    @Override
    public void addMember(Member member) {
        members = addNode(members, member);
    }

    @Override
    public Member getMember(String memberId) {
        return findNode(members, memberId);
    }

    @Override
    public void removeMember(String memberId) {
        members = removeNode(members, memberId);
    }

    // Methods to manage loans
    @Override
    public void loanBook(String bookId, String memberId) {
        Book book = getBook(bookId);
        Member member = getMember(memberId);
        if (book != null && member != null) {
            Loan loan = new Loan(book, member);
            loans = addNode(loans, loan);
            book.setLoanStatus(true);
        }
    }

    @Override
    public void returnBook(String bookId) {
        Loan loanToRemove = findLoan(loans, bookId);
        if (loanToRemove != null) {
            loans = removeNode(loans, loanToRemove.getBook().getId());
            loanToRemove.getBook().setLoanStatus(false);
        }
    }

    // Methods to add and retrieve librarians
    @Override
    public void addLibrarian(Librarian librarian) {
        librarians = addNode(librarians, librarian);
    }

    @Override
    public Librarian getLibrarian(String librarianId) {
        return findNode(librarians, librarianId);
    }

    // Generic methods for managing linked lists
    private <T> Node<T> addNode(Node<T> head, T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            return newNode;
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
        return head;
    }

    private <T> T findNode(Node<T> head, String id) {
        Node<T> current = head;
        while (current != null) {
            if (((Identifiable) current.getData()).getId().equals(id)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    private <T> Node<T> removeNode(Node<T> head, String id) {
        if (head == null) {
            return null;
        }
        if (((Identifiable) head.getData()).getId().equals(id)) {
            return head.getNext();
        }
        Node<T> current = head;
        while (current.getNext() != null && !((Identifiable) current.getNext().getData()).getId().equals(id)) {
            current = current.getNext();
        }
        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
        }
        return head;
    }

    private Loan findLoan(Node<Loan> head, String bookId) {
        Node<Loan> current = head;
        while (current != null) {
            if (current.getData().getBook().getId().equals(bookId)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }
}

