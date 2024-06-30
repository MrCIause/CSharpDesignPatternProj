import java.util.Stack;

public class Member {
    private String name;
    private String id;
    private Stack<Loan> loans;

    public Member(String name, String id) {
        this.name = name;
        this.id = id;
        this.loans = new Stack<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Stack<Loan> getLoans() {
        return loans;
    }

    public void addLoan(Loan loan) {
        loans.push(loan);
    }

    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }
}
