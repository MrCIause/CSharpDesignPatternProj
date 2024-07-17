package MainClasses;

import Interfaces.ILoan;
import Interfaces.IMember;

import java.util.Stack;

public class Member implements IMember {
    private String name;
    private String id;
    private Stack<ILoan> loans;  // Change Loan to ILoan

    public Member(String name, String id) {
        this.name = name;
        this.id = id;
        this.loans = new Stack<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Stack<ILoan> getLoans() {  // Change Loan to ILoan
        return loans;
    }

    @Override
    public void addLoan(ILoan loan) {  // Change Loan to ILoan
        loans.push(loan);
    }

    @Override
    public void removeLoan(ILoan loan) {  // Change Loan to ILoan
        loans.remove(loan);
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", loans=" + loans +
                '}';
    }
}
