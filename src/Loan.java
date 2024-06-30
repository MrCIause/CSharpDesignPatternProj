import java.util.Date;

public class Loan {
    private Book book;
    private Date loanDate;
    private Date returnDate;

    public Loan(Book book, Date loanDate) {
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = null;
    }

    public Book getBook() {
        return book;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
