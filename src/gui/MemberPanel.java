package gui;

import model.Admin;
import model.Member;
import model.MembershipPlan;
import model.ProgressLog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MemberPanel extends JFrame {
    private Admin admin;
    private JTextArea memberDisplay;

    public MemberPanel(Admin admin) {
        this.admin = admin;

        setTitle("👥 Member Management");
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        memberDisplay = new JTextArea();
        memberDisplay.setEditable(false);
        memberDisplay.setBackground(Color.BLACK);
        memberDisplay.setForeground(Color.WHITE);
        memberDisplay.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(memberDisplay);
        scrollPane.getViewport().setBackground(Color.BLACK);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBackground(Color.BLACK);

        JButton viewBtn = createStyledButton("🔍 View All");
        JButton addBtn = createStyledButton("➕ Add");
        JButton removeBtn = createStyledButton("❌ Remove");
        JButton progressBtn = createStyledButton("📈 Progress Logs");

        buttonPanel.add(viewBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(progressBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);

        viewBtn.addActionListener(e -> displayMembers());
        addBtn.addActionListener(e -> addMemberDialog());
        removeBtn.addActionListener(e -> removeMemberDialog());
        progressBtn.addActionListener(e -> showProgressLogs());
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(new Color(50, 50, 50));
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }

    private void displayMembers() {
        StringBuilder sb = new StringBuilder("--- Member List ---\n\n");
        for (Member m : admin.getMembers()) {
            sb.append("ID: ").append(m.getId())
                    .append(" | Name: ").append(m.getName())
                    .append(" | Age: ").append(m.getAge())
                    .append("\n");
        }
        memberDisplay.setText(sb.toString());
    }

    private void addMemberDialog() {
        JTextField idField = createDarkField();
        JTextField nameField = createDarkField();
        JTextField ageField = createDarkField();
        JTextField weightField = createDarkField();
        JTextField heightField = createDarkField();
        JTextField goalField = createDarkField();

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBackground(Color.BLACK);
        JLabel[] labels = {
                new JLabel("ID:"), new JLabel("Name:"),
                new JLabel("Age:"), new JLabel("Weight (kg):"),
                new JLabel("Height (cm):"), new JLabel("Goal:")
        };
        for (JLabel label : labels) {
            label.setForeground(Color.WHITE);
        }

        inputPanel.add(labels[0]); inputPanel.add(idField);
        inputPanel.add(labels[1]); inputPanel.add(nameField);
        inputPanel.add(labels[2]); inputPanel.add(ageField);
        inputPanel.add(labels[3]); inputPanel.add(weightField);
        inputPanel.add(labels[4]); inputPanel.add(heightField);
        inputPanel.add(labels[5]); inputPanel.add(goalField);

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Add New Member", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String id = idField.getText();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                String goal = goalField.getText();

                Member newMember = new Member(id, name, age, weight, height, goal);
                admin.addMember(newMember);
                JOptionPane.showMessageDialog(this, "Member added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeMemberDialog() {
        String id = JOptionPane.showInputDialog(this, "Enter Member ID to remove:");
        if (id != null && !id.isEmpty()) {
            admin.removeMember(id);
            JOptionPane.showMessageDialog(this, "Member removed if ID matched.");
        }
    }

    private void showProgressLogs() {
        JTextField memberIdField = createDarkField();
        int result = JOptionPane.showConfirmDialog(this, memberIdField, "Enter Member ID", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String id = memberIdField.getText();
            Member member = admin.getMemberById(id);
            if (member != null) {
                StringBuilder sb = new StringBuilder("Progress Logs for " + member.getName() + ":\n\n");
                ProgressLog log1 = new ProgressLog("2025-01-01", 85.5, 350);
                ProgressLog log2 = new ProgressLog("2025-02-01", 83.2, 400);
                ProgressLog log3 = new ProgressLog("2025-03-01", 81.8, 450);

                sb.append("Date: 2025-01-01\nWeight: 85.5kg\nCalories Burned: 350\n\n");
                sb.append("Date: 2025-02-01\nWeight: 83.2kg\nCalories Burned: 400\n\n");
                sb.append("Date: 2025-03-01\nWeight: 81.8kg\nCalories Burned: 450\n\n");

                memberDisplay.setText(sb.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Member not found.");
            }
        }
    }

    private JTextField createDarkField() {
        JTextField field = new JTextField();
        field.setBackground(new Color(30, 30, 30));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        return field;
    }
}
