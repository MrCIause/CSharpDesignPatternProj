package Interfaces;

import MainClasses.Book;
import MainClasses.Member;

import java.util.Date;

public interface LoanInterface {
    Book getBook();
    Member getMember();
    Date getLoanDate();
}

