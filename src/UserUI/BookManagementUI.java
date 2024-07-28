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
        JPanel controlPanel = new JPanel(new GridLayout(8, 2));

        controlPanel.add(new JLabel("Book Title:"));
        JTextField bookTitleField = new JTextField();
        controlPanel.add(bookTitleField);

        controlPanel.add(new JLabel("Author:"));
        JTextField bookAuthorField = new JTextField();
        controlPanel.add(bookAuthorField);

        controlPanel.add(new JLabel("Publication Year:"));
        JTextField publicationYearField = new JTextField();
        controlPanel.add(publicationYearField);

        controlPanel.add(new JLabel("Availability:"));
        String[] availabilityOptions = {"Available", "Unavailable", "Loaned"};
        JComboBox<String> availabilityComboBox = new JComboBox<>(availabilityOptions);
        controlPanel.add(availabilityComboBox);

        JButton addBookButton = new JButton("Add Book");
        controlPanel.add(addBookButton);

        JButton removeBookButton = new JButton("Remove Book");
        controlPanel.add(removeBookButton);

        JButton loanBookButton = new JButton("Loan Book");
        controlPanel.add(loanBookButton);

        JButton returnBookButton = new JButton("Return Book");
        controlPanel.add(returnBookButton);

        JButton makeAvailableButton = new JButton("Make Available");
        controlPanel.add(makeAvailableButton);

        JButton showSummaryButton = new JButton("Show Library Summary");
        controlPanel.add(showSummaryButton);

        JButton backButton = new JButton("Back to Main Menu");
        controlPanel.add(backButton);

        // Table to display books
        String[] columnNames = {"ID", "Title", "Author", "Year", "Availability"};
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
                String availability = (String) availabilityComboBox.getSelectedItem();
                boolean isAvailable = availability.equals("Available");

                IBook book = bookFactory.createBook(title, author, year, isAvailable);
                if (availability.equals("Unavailable")) {
                    // Mark the book as unavailable if selected
                    book.setAvailable(false);
                }
                Library.getInstance().addBook(book);  // Add book to library
                librarian.addBook(book);
                updateBookTable();

                JOptionPane.showMessageDialog(frame, "Book added successfully!");
            }
        });

        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog(frame, "Enter Book Title to Delete:");
                String author = JOptionPane.showInputDialog(frame, "Enter Book Author to Delete:");
                String idStr = JOptionPane.showInputDialog(frame, "Enter Book ID to Delete:");
                if (title != null && author != null && idStr != null && !title.isEmpty() && !author.isEmpty() && !idStr.isEmpty()) {
                    try {
                        int bookId = Integer.parseInt(idStr);
                        if (bookId == 0) {
                            JOptionPane.showMessageDialog(frame, "Book ID 0 is reserved for returning unavailable books.");
                            return;
                        }
                        List<IBook> books = Library.getInstance().getBooks();
                        IBook bookToRemove = null;
                        for (IBook book : books) {
                            if (book.getId() == bookId && book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                                if (!book.isAvailable()) {
                                    JOptionPane.showMessageDialog(frame, "Cannot remove a book that is unavailable or loaned.");
                                    return;
                                }
                                bookToRemove = book;
                                break;
                            }
                        }

                        if (bookToRemove != null) {
                            librarian.removeBook(bookToRemove);
                            Library.getInstance().removeBook(bookToRemove);  // Remove book from library
                            updateBookTable();
                            JOptionPane.showMessageDialog(frame, "Book removed successfully!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Book not found!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid ID format!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please provide title, author, and ID!");
                }
            }
        });

        loanBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog(frame, "Enter Book Title to Loan:");
                String author = JOptionPane.showInputDialog(frame, "Enter Book Author to Loan:");
                String idStr = JOptionPane.showInputDialog(frame, "Enter Book ID to Loan:");
                String memberIdStr = JOptionPane.showInputDialog(frame, "Enter Member ID:");
                if (title != null && author != null && idStr != null && memberIdStr != null && !title.isEmpty() && !author.isEmpty() && !idStr.isEmpty() && !memberIdStr.isEmpty()) {
                    try {
                        int bookId = Integer.parseInt(idStr);
                        int memberId = Integer.parseInt(memberIdStr);

                        List<IBook> books = Library.getInstance().getBooks();
                        IBook bookToLoan = null;

                        for (IBook book : books) {
                            if (book.getId() == bookId && book.getTitle().equals(title) && book.getAuthor().equals(author) && book.isAvailable()) {
                                bookToLoan = book;
                                break;
                            }
                        }

                        if (bookToLoan == null) {
                            JOptionPane.showMessageDialog(frame, "Book is unavailable or not found!");
                            return;
                        }

                        List<IMember> members = Library.getInstance().getMembers();
                        IMember memberToLoan = null;

                        for (IMember member : members) {
                            if (member.getId() == memberId) {
                                memberToLoan = member;
                                break;
                            }
                        }

                        if (memberToLoan != null) {
                            ILoan loan = new Loan.Builder()
                                    .withBook(bookToLoan)
                                    .withLoanDate(new Date())
                                    .build();
                            memberToLoan.addLoan(loan); // Assuming the addLoan method exists
                            librarian.loanBook(bookToLoan, memberToLoan);
                            bookToLoan.setAvailable(false); // Set book as unavailable
                            updateBookTable();
                            JOptionPane.showMessageDialog(frame, "Book loaned successfully!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Member not found!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid ID format!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please provide title, author, book ID, and member ID!");
                }
            }
        });

        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog(frame, "Enter Book Title to Return:");
                String author = JOptionPane.showInputDialog(frame, "Enter Book Author to Return:");
                String idStr = JOptionPane.showInputDialog(frame, "Enter Book ID to Return:");
                String memberIdStr = JOptionPane.showInputDialog(frame, "Enter Member ID:");
                if (title != null && author != null && idStr != null && memberIdStr != null && !title.isEmpty() && !author.isEmpty() && !idStr.isEmpty() && !memberIdStr.isEmpty()) {
                    try {
                        int bookId = Integer.parseInt(idStr);
                        int memberId = Integer.parseInt(memberIdStr);

                        if (bookId == 0) {
                            List<IBook> books = Library.getInstance().getBooks();
                            for (IBook book : books) {
                                if (book.getTitle().equals(title) && book.getAuthor().equals(author) && !book.isAvailable()) {
                                    book.setAvailable(true); // Set book as available
                                    updateBookTable();
                                    JOptionPane.showMessageDialog(frame, "Book returned successfully!");
                                    return;
                                }
                            }
                            JOptionPane.showMessageDialog(frame, "No matching unavailable book found!");
                            return;
                        }

                        List<IBook> books = Library.getInstance().getBooks();
                        IBook bookToReturn = null;

                        for (IBook book : books) {
                            if (book.getId() == bookId && book.getTitle().equals(title) && book.getAuthor().equals(author) && !book.isAvailable()) {
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
                            if (member.getId() == memberId) {
                                memberToReturn = member;
                                break;
                            }
                        }

                        if (bookToReturn != null && memberToReturn != null) {
                            librarian.returnBook(bookToReturn, memberToReturn);
                            bookToReturn.setAvailable(true); // Set book as available
                            updateBookTable();
                            JOptionPane.showMessageDialog(frame, "Book returned successfully!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Book or Member not found!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid ID format!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please provide title, author, book ID, and member ID!");
                }
            }
        });

        makeAvailableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog(frame, "Enter Book Title to Make Available:");
                String idStr = JOptionPane.showInputDialog(frame, "Enter Book ID to Make Available:");
                if (title != null && idStr != null && !title.isEmpty() && !idStr.isEmpty()) {
                    try {
                        int bookId = Integer.parseInt(idStr);
                        List<IBook> books = Library.getInstance().getBooks();
                        for (IBook book : books) {
                            if (book.getId() == bookId && book.getTitle().equals(title) && !book.isAvailable()) {
                                book.setAvailable(true);
                                updateBookTable();
                                JOptionPane.showMessageDialog(frame, "Book made available successfully!");
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(frame, "No matching unavailable book found!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid ID format!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please provide both title and ID!");
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
            String availability = book.isAvailable() ? "Available" : "Unavailable";
            if (!book.isAvailable() && !availability.equals("Unavailable")) {
                availability = "Loaned";
            }
            String[] rowData = {
                    String.valueOf(book.getId()),
                    book.getTitle(),
                    book.getAuthor(),
                    String.valueOf(book.getPublicationYear()),
                    availability
            };
            tableModel.addRow(rowData);
        }
    }
}
