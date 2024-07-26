package Interfaces;

import java.util.List;

public interface IMember {
    String getName();
    int getId();
    List<ILoan> getLoans();
    void addLoan(ILoan loan);
}
