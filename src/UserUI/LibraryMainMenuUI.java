package UserUI;
import Interfaces.*;
import MainClasses.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main class for the Library Management System Main Menu UI.
 */
public class LibraryMainMenuUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryMainMenuUI ui = new LibraryMainMenuUI();
            ui.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Library Management System Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create main menu panel
        JPanel mainMenuPanel = new JPanel(new GridLayout(2, 1));

        JButton manageBooksButton = new JButton("Manage Books");
        JButton manageMembersButton = new JButton("Manage Members");

        manageBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                BookManagementUI bookUI = new BookManagementUI();
                bookUI.createAndShowGUI();
            }
        });

        manageMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MemberManagementUI memberUI = new MemberManagementUI();
                memberUI.createAndShowGUI();
            }
        });

        mainMenuPanel.add(manageBooksButton);
        mainMenuPanel.add(manageMembersButton);

        frame.getContentPane().add(mainMenuPanel);
        frame.setVisible(true);
    }
}
