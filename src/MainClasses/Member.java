package MainClasses;

import Interfaces.IMember;
import Interfaces.ILoan;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a library member.
 */
public class Member implements IMember {
    private String name;
    private int id;
    private List<ILoan> loans;

    public Member(String name, int id) {
        this.name = name;
        this.id = id;
        this.loans = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<ILoan> getLoans() {
        return loans;
    }

    @Override
    public void addLoan(ILoan loan) {
        loans.add(loan);
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
