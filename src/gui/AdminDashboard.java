package gui;

import model.Admin;
import model.MembershipPlan;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminDashboard extends JFrame {
    private Admin admin;

    public AdminDashboard(Admin admin) {
        this.admin = admin;
        setTitle("🏋️ Admin Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton btnMembers = createStyledButton("👥 Manage Members");
        JButton btnTrainers = createStyledButton("🧑‍🏫 Manage Trainers");
        JButton btnPlans = createStyledButton("💳 View Membership Plans");
        JButton btnLogout = createStyledButton("🚪 Logout");

        btnMembers.addActionListener(e -> new MemberPanel(admin));
        btnTrainers.addActionListener(e -> JOptionPane.showMessageDialog(this, "Trainer panel coming soon."));
        btnPlans.addActionListener(e -> showPlans());
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        panel.add(btnMembers);
        panel.add(btnTrainers);
        panel.add(btnPlans);
        panel.add(btnLogout);

        add(panel);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.PLAIN, 16));
        button.setBackground(new Color(60, 120, 240));
        button.setForeground(Color.WHITE);
        return button;
    }

    private void showPlans() {
        List<MembershipPlan> plans = MembershipPlan.loadFromFile("plans.txt");
        StringBuilder sb = new StringBuilder("Membership Plans:\n\n");
        for (MembershipPlan plan : plans) {
            sb.append(plan.getPlanName()).append(" - $")
                    .append(plan.getMonthlyFee()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Plans", JOptionPane.INFORMATION_MESSAGE);
    }
}
