package UserUI;

import Interfaces.*;
import MainClasses.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Date;

/**
 * Class for the Book Management UI.
 */
public class BookManagementUI {
    private ILibrarian librarian;
    private IBookFactory bookFactory;
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public BookManagementUI() {
        this.librarian = new Librarian();
        this.bookFactory = new BookFactory();
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Book Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        // Create control panel
        JPanel controlPanel = new JPanel(new GridLayout(7, 2));

        controlPanel.add(new JLabel("Book Title:"));
        JTextField bookTitleField = new JTextField();
        controlPanel.add(bookTitleField);

        controlPanel.add(new JLabel("Author:"));
        JTextField bookAuthorField = new JTextField();
        controlPanel.add(bookAuthorField);

        controlPanel.add(new JLabel("Publication Year:"));
        JTextField publicationYearField = new JTextField();
        controlPanel.add(publicationYearField);

        controlPanel.add(new JLabel("Available:"));
        JCheckBox isAvailableCheckBox = new JCheckBox();
        controlPanel.add(isAvailableCheckBox);

        JButton addBookButton = new JButton("Add Book");
        controlPanel.add(addBookButton);

        JButton removeBookButton = new JButton("Remove Book");
        controlPanel.add(removeBookButton);

        JButton loanBookButton = new JButton("Loan Book");
        controlPanel.add(loanBookButton);

        JButton returnBookButton = new JButton("Return Book");
        controlPanel.add(returnBookButton);

        JButton showSummaryButton = new JButton("Show Library Summary");
        controlPanel.add(showSummaryButton);

        JButton backButton = new JButton("Back to Main Menu");
        controlPanel.add(backButton);

        // Table to display books
        String[] columnNames = {"Title", "Author", "Year", "Available"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);

        // Add action listeners
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = bookTitleField.getText();
                String author = bookAuthorField.getText();
                int year = Integer.parseInt(publicationYearField.getText());
                boolean isAvailable = isAvailableCheckBox.isSelected();

                IBook book = bookFactory.createBook(title, author, year, isAvailable);
                librarian.addBook(book);
                updateBookTable();

                JOptionPane.showMessageDialog(frame, "Book added successfully!");
            }
        });

        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = bookTitleField.getText();
                List<IBook> books = Library.getInstance().getBooks();
                for (IBook book : books) {
                    if (book.getTitle().equals(title)) {
                        librarian.removeBook(book);
                        updateBookTable();
                        JOptionPane.showMessageDialog(frame, "Book removed successfully!");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Book not found!");
            }
        });

        loanBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = bookTitleField.getText();
                String memberId = JOptionPane.showInputDialog(frame, "Enter Member ID:");

                List<IBook> books = Library.getInstance().getBooks();
                IBook bookToLoan = null;

                for (IBook book : books) {
                    if (book.getTitle().equals(title) && book.isAvailable()) {
                        bookToLoan = book;
                        break;
                    }
                }

                if (bookToLoan == null) {
                    JOptionPane.showMessageDialog(frame, "Book is unavailable!");
                    return;
                }

                List<IMember> members = Library.getInstance().getMembers();
                IMember memberToLoan = null;

                for (IMember member : members) {
                    if (member.getId().equals(memberId)) {
                        memberToLoan = member;
                        break;
                    }
                }

                if (bookToLoan != null && memberToLoan != null) {
                    ILoan loan = new Loan.Builder()
                            .withBook(bookToLoan)
                            .withLoanDate(new Date())
                            .build();
                    memberToLoan.addLoan(loan); // Assuming the addLoan method exists
                    librarian.loanBook(bookToLoan, memberToLoan);
                    updateBookTable();
                    JOptionPane.showMessageDialog(frame, "Book loaned successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Book or Member not found!");
                }
            }
        });

        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = bookTitleField.getText();
                String memberId = JOptionPane.showInputDialog(frame, "Enter Member ID:");

                List<IBook> books = Library.getInstance().getBooks();
                IBook bookToReturn = null;

                for (IBook book : books) {
                    if (book.getTitle().equals(title) && !book.isAvailable()) {
                        bookToReturn = book;
                        break;
                    }
                }

                if (bookToReturn == null) {
                    JOptionPane.showMessageDialog(frame, "No borrowed copy of this book found!");
                    return;
                }

                List<IMember> members = Library.getInstance().getMembers();
                IMember memberToReturn = null;

                for (IMember member : members) {
                    if (member.getId().equals(memberId)) {
                        memberToReturn = member;
                        break;
                    }
                }

                if (bookToReturn != null && memberToReturn != null) {
                    librarian.returnBook(bookToReturn, memberToReturn);
                    updateBookTable();
                    JOptionPane.showMessageDialog(frame, "Book returned successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Book or Member not found!");
                }
            }
        });

        showSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea librarySummaryArea = new JTextArea();
                librarySummaryArea.setEditable(false);
                librarySummaryArea.setText(Library.getInstance().getLibrarySummary());
                JOptionPane.showMessageDialog(frame, new JScrollPane(librarySummaryArea), "Library Summary", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LibraryMainMenuUI.main(null);
            }
        });

        // Add panels to container
        container.add(controlPanel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);

        // Display the window
        frame.setVisible(true);
        updateBookTable();
    }

    private void updateBookTable() {
        tableModel.setRowCount(0); // Clear existing data
        List<IBook> books = Library.getInstance().getBooks();
        for (IBook book : books) {
            String[] rowData = {
                    book.getTitle(),
                    book.getAuthor(),
                    String.valueOf(book.getPublicationYear()),
                    book.isAvailable() ? "Available" : "Loaned"
            };
            tableModel.addRow(rowData);
        }
    }
}
