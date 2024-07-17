package UserUI;
import Interfaces.*;
import MainClasses.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Class for the Member Management UI.
 */
public class MemberManagementUI {
    private ILibrarian librarian;
    private IMemberFactory memberFactory;

    public MemberManagementUI() {
        this.librarian = new Librarian();
        this.memberFactory = new MemberFactory();
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Member Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        // Create control panel
        JPanel controlPanel = new JPanel(new GridLayout(4, 2));

        controlPanel.add(new JLabel("Member Name:"));
        JTextField memberNameField = new JTextField();
        controlPanel.add(memberNameField);

        controlPanel.add(new JLabel("Member ID:"));
        JTextField memberIdField = new JTextField();
        controlPanel.add(memberIdField);

        JButton addMemberButton = new JButton("Add Member");
        controlPanel.add(addMemberButton);

        JButton removeMemberButton = new JButton("Remove Member");
        controlPanel.add(removeMemberButton);

        JButton showSummaryButton = new JButton("Show Library Summary");
        controlPanel.add(showSummaryButton);

        JButton backButton = new JButton("Back to Main Menu");
        controlPanel.add(backButton);

        // Add action listeners
        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = memberNameField.getText();
                String id = memberIdField.getText();

                IMember member = memberFactory.createMember(name, id);
                librarian.addMember(member);

                JOptionPane.showMessageDialog(frame, "Member added successfully!");
            }
        });

        removeMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = memberIdField.getText();
                List<IMember> members = Library.getInstance().getMembers();
                for (IMember member : members) {
                    if (member.getId().equals(id)) {
                        librarian.removeMember(member);
                        JOptionPane.showMessageDialog(frame, "Member removed successfully!");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Member not found!");
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

        // Display the window
        frame.setVisible(true);
    }
}
