package Interfaces;
import java.util.Date;

import java.util.Date;

/**
 * Interface for loan operations.
 */
public interface ILoan {
    IBook getBook();
    Date getLoanDate();
    Date getReturnDate();
    void setReturnDate(Date returnDate);
}

