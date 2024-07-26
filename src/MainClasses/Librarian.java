package MainClasses;

import Interfaces.ILibrarian;
import Interfaces.IMember;
import Interfaces.IBook;
import Interfaces.ILoan;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a librarian.
 */
public class Librarian implements ILibrarian {
    private List<IBook> books;
    private List<IMember> members;

    public Librarian() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    @Override
    public void addBook(IBook book) {
        books.add(book);
    }

    @Override
    public void removeBook(IBook book) {
        books.remove(book);
    }

    @Override
    public void addMember(IMember member) {
        members.add(member);
    }

    @Override
    public void removeMember(IMember member) {
        members.remove(member);
    }

    @Override
    public void loanBook(IBook book, IMember member) {
        if (books.contains(book) && members.contains(member) && book.isAvailable()) {
            book.setAvailable(false);
            ILoan loan = new Loan.Builder()
                    .withBook(book)
                    .withLoanDate(new java.util.Date())
                    .build();
            member.addLoan(loan);
        }
    }

    @Override
    public void returnBook(IBook book, IMember member) {
        if (books.contains(book) && members.contains(member) && !book.isAvailable()) {
            book.setAvailable(true);
            member.getLoans().removeIf(loan -> loan.getBook().equals(book));
        }
    }

    @Override
    public IMember getMemberById(int id) {
        for (IMember member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null; // Member not found
    }
}
