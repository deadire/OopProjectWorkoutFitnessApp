package gui;

import javax.swing.*;
import java.awt.*;
import model.Admin;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    private Admin admin = new Admin("A001", "model.Admin", "admin", "admin123");

    public LoginFrame() {
        setTitle("Workout Fitness App - model.Admin Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        add(loginButton);
        loginButton.addActionListener(e -> handleLogin());

        setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (admin.getUsername().equals(username) && admin.checkPassword(password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            dispose(); // Close login window
            new AdminDashboard(admin); // Open dashboard
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.");
        }
    }
}
