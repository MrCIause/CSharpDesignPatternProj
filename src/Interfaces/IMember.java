package Interfaces;

import java.util.Stack;

import java.util.Stack;

/**
 * Interface for member operations.
 */
public interface IMember {
    String getName();
    String getId();
    Stack<ILoan> getLoans();
    void addLoan(ILoan loan);
    void removeLoan(ILoan loan);
}

