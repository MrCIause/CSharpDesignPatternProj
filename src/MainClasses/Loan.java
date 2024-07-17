package MainClasses;

import Interfaces.IBook;
import Interfaces.ILoan;
import java.util.Date;

/**
 * The Loan class represents a loan of a book to a member.
 */
public class Loan implements ILoan {
    private final IBook book;
    private final Date loanDate;
    private Date returnDate;

    private Loan(Builder builder) {
        this.book = builder.book;
        this.loanDate = builder.loanDate;
        this.returnDate = builder.returnDate;
    }

    public IBook getBook() {
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

    public static class Builder {
        private IBook book;
        private Date loanDate;
        private Date returnDate;

        public Builder withBook(IBook book) {
            this.book = book;
            return this;
        }

        public Builder withLoanDate(Date loanDate) {
            this.loanDate = loanDate;
            return this;
        }

        public Builder withReturnDate(Date returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Loan build() {
            return new Loan(this);
        }
    }
}
