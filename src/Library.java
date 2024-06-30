import java.util.Date;
import java.util.Stack;

public class Library {
    private static Library instance;
    private Stack<Book> books;
    private Stack<Member> members;

    private Library() {
        books = new Stack<>();
        members = new Stack<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.push(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void addMember(Member member) {
        members.push(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }

    public void loanBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            Loan loan = new Loan(book, new Date());
            member.addLoan(loan);
        }
    }

    public void returnBook(Book book, Member member) {
        book.setAvailable(true);
        for (Loan loan : member.getLoans()) {
            if (loan.getBook().equals(book)) {
                loan.setReturnDate(new Date());
                member.removeLoan(loan);
                break;
            }
        }
    }

    public void changeBookStatus(Book book, boolean isAvailable) {
        book.setAvailable(isAvailable);
    }

    public String getLibrarySummary() {
        int availableBooks = 0;
        int loanedBooks = 0;
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks++;
            } else {
                loanedBooks++;
            }
        }
        int activeMembers = members.size();
        int totalLoans = 0;
        for (Member member : members) {
            totalLoans += member.getLoans().size();
        }
        return "Available Books: " + availableBooks + "\nLoaned Books: " + loanedBooks + "\nActive Members: " + activeMembers + "\nTotal Loans: " + totalLoans;
    }

    public Book cloneBook(Book book) {
        try {
            return book.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
