package MainClasses;

import Interfaces.LoanInterface;


import java.util.Date;

public class Loan implements LoanInterface {
    private Book book;
    private Member member;
    private Date loanDate;

    // Constructor, getters, and setters
    public Loan(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.loanDate = new Date();
    }

    @Override
    public Book getBook() {
        return book;
    }

    @Override
    public Member getMember() {
        return member;
    }

    @Override
    public Date getLoanDate() {
        return loanDate;
    }
}


