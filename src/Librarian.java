public class Librarian {
    private Library library = Library.getInstance();

    public void addBook(Book book) {
        library.addBook(book);
    }

    public void removeBook(Book book) {
        library.removeBook(book);
    }

    public void addMember(Member member) {
        library.addMember(member);
    }

    public void removeMember(Member member) {
        library.removeMember(member);
    }

    public void loanBook(Book book, Member member) {
        library.loanBook(book, member);
    }

    public void returnBook(Book book, Member member) {
        library.returnBook(book, member);
    }

    public void changeBookStatus(Book book, boolean isAvailable) {
        library.changeBookStatus(book, isAvailable);
    }

    public String getLibrarySummary() {
        return library.getLibrarySummary();
    }

    public Book cloneBook(Book book) {
        return library.cloneBook(book);
    }
}
