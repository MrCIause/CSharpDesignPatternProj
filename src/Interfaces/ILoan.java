package Interfaces;

import java.util.Date;

public interface ILoan {
    IBook getBook();
    Date getLoanDate();
    Date getReturnDate();
    void setReturnDate(Date returnDate);
}
