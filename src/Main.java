
import Interfaces.*;
import MainClasses.*;
import UserUI.*;


import javax.swing.*;


public class Main {
    public static void main(String[] args) {
//        ILibrarian librarian = new Librarian();

        // Create factories
//        IBookFactory bookFactory = new BookFactory();
//        IMemberFactory memberFactory = new MemberFactory();

        // Add books
//        IBook book1 = bookFactory.createBook("Book 1", "Author 1", 2001, true);
//        IBook book2 = bookFactory.createBook("Book 2", "Author 2", 2002, true);
//        librarian.addBook(book1);
//        librarian.addBook(book2);

        // Add members
//        IMember member1 = memberFactory.createMember("Member 1", "001");
//        IMember member2 = memberFactory.createMember("Member 2", "002");
//        librarian.addMember(member1);
//        librarian.addMember(member2);

        // Loan a book
//        librarian.loanBook(book1, member1);

        // Return a book
//        librarian.returnBook(book1, member1);

        // Change book status
//        librarian.addBook(book1);
//        librarian.addBook(book2);

        // Remove a book
//        librarian.removeBook(book2);

        // Clone a book
//        IBook clonedBook = Library.getInstance().cloneBook(book1);

        // Provide a summary
//        System.out.println(Library.getInstance().getLibrarySummary());

        // Create and show the main menu
        invokeMemberManagementUI();
    }
    public static void invokeMemberManagementUI() {
        MemberManagementUI memberManagementUI = new MemberManagementUI();
        memberManagementUI.createAndShowGUI();
    }
}
