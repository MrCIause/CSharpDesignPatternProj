package UserUI;

import Interfaces.*;
import MainClasses.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

/**
 * Class for the Member Management UI.
 */
public class MemberManagementUI {
    private ILibrarian librarian;
    private IMemberFactory memberFactory;
    private JList<String> memberList;

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
        memberIdField.setEditable(false); // ID will be auto-generated
        controlPanel.add(memberIdField);

        JButton addMemberButton = new JButton("Add Member");
        controlPanel.add(addMemberButton);

        JButton removeMemberButton = new JButton("Remove Member");
        controlPanel.add(removeMemberButton);

        JButton showSummaryButton = new JButton("Show Library Summary");
        controlPanel.add(showSummaryButton);

        JButton backButton = new JButton("Back to Main Menu");
        controlPanel.add(backButton);

        // Member list display
        memberList = new JList<>();
        JScrollPane listScrollPane = new JScrollPane(memberList);
        container.add(listScrollPane, BorderLayout.EAST);

        // Add action listeners
        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = memberNameField.getText();
                IMember member = memberFactory.createMember(name);
                if (member == null) {
                    JOptionPane.showMessageDialog(frame, "Member already exists!");
                } else {
                    librarian.addMember(member);
                    Library.getInstance().addMember(member); // Add member to library
                    memberIdField.setText(String.valueOf(member.getId())); // Display generated ID
                    JOptionPane.showMessageDialog(frame, "Member added successfully with ID: " + member.getId());
                    updateMemberList();
                }
            }
        });

        removeMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = memberIdField.getText();
                int memberId = Integer.parseInt(id);
                IMember member = librarian.getMemberById(memberId);
                if (member != null) {
                    librarian.removeMember(member);
                    Library.getInstance().removeMember(member); // Remove member from library
                    MemberFactory.removeMember(member); // Remove from factory queue as well
                    JOptionPane.showMessageDialog(frame, "Member removed successfully!");
                    updateMemberList();
                } else {
                    JOptionPane.showMessageDialog(frame, "Member not found!");
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

        container.add(controlPanel, BorderLayout.NORTH);
        frame.setVisible(true);
        updateMemberList();
    }

    private void updateMemberList() {
        Queue<IMember> members = MemberFactory.getMemberQueue();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (IMember member : members) {
            listModel.addElement(member.toString());
        }
        memberList.setModel(listModel);
    }
}
