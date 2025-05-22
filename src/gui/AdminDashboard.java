package gui;

import javax.swing.*;
import java.awt.*;
import model.Admin;
import model.MembershipPlan;
import java.util.List;

public class AdminDashboard extends JFrame {
    private Admin admin;

    public AdminDashboard(Admin admin) {
        this.admin = admin;
        setTitle("model.Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));

        JButton membersBtn = new JButton("Manage Members");
        JButton trainersBtn = new JButton("Manage Trainers");
        JButton plansBtn = new JButton("View Membership Plans");

        membersBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Members panel coming soon..."));
        trainersBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Trainers panel coming soon..."));
        plansBtn.addActionListener(e -> showPlans());

        panel.add(membersBtn);
        panel.add(trainersBtn);
        panel.add(plansBtn);

        add(panel);
        setVisible(true);
    }

    private void showPlans() {
        StringBuilder sb = new StringBuilder("Membership Plans:\n");
        for (MembershipPlan plan : MembershipPlan.loadFromFile("plans.txt")) {
            sb.append("- ").append(plan.getPlanName()).append(": $")
                    .append(plan.getMonthlyFee()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }
}
