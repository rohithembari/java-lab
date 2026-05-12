package com.railway.gui;

import com.railway.service.AuthService;
import com.railway.util.StyleUtil;
import javax.swing.*;
import java.awt.*;

/**
 * Login screen for the application.
 */
public class LoginPanel extends JPanel {
    private MainFrame mainFrame;
    private AuthService authService;

    public LoginPanel(MainFrame frame) {
        this.mainFrame = frame;
        this.authService = new AuthService();
        setLayout(new GridBagLayout());
        setBackground(StyleUtil.BACKGROUND);

        JPanel loginBox = new JPanel();
        loginBox.setLayout(new BoxLayout(loginBox, BoxLayout.Y_AXIS));
        loginBox.setBackground(StyleUtil.SIDEBAR_BG);
        loginBox.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel title = new JLabel("Railway Reservation System");
        title.setFont(StyleUtil.TITLE_FONT);
        title.setForeground(StyleUtil.TEXT_PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Login to your account");
        subtitle.setFont(StyleUtil.BODY_FONT);
        subtitle.setForeground(StyleUtil.TEXT_SECONDARY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField userField = new JTextField(20);
        StyleUtil.styleTextField(userField);
        JPasswordField passField = new JPasswordField(20);
        StyleUtil.styleTextField(passField);

        JButton loginBtn = new JButton("Login");
        StyleUtil.styleButton(loginBtn);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton regBtn = new JButton("Create Account");
        StyleUtil.styleSecondaryButton(regBtn);
        regBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginBox.add(title);
        loginBox.add(Box.createVerticalStrut(10));
        loginBox.add(subtitle);
        loginBox.add(Box.createVerticalStrut(30));
        
        loginBox.add(createLabel("Username"));
        loginBox.add(userField);
        loginBox.add(Box.createVerticalStrut(15));
        
        loginBox.add(createLabel("Password"));
        loginBox.add(passField);
        loginBox.add(Box.createVerticalStrut(30));
        
        loginBox.add(loginBtn);
        loginBox.add(Box.createVerticalStrut(10));
        loginBox.add(regBtn);

        add(loginBox);

        // Actions
        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (authService.login(user, pass)) {
                mainFrame.showPanel("DASHBOARD");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        regBtn.addActionListener(e -> {
            String user = JOptionPane.showInputDialog(this, "Enter Username:");
            if (user != null && !user.isEmpty()) {
                String pass = JOptionPane.showInputDialog(this, "Enter Password:");
                String name = JOptionPane.showInputDialog(this, "Enter Full Name:");
                if (authService.register(user, pass, name)) {
                    JOptionPane.showMessageDialog(this, "Account created successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "User already exists.");
                }
            }
        });
    }

    private JLabel createLabel(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(StyleUtil.TEXT_SECONDARY);
        l.setFont(StyleUtil.BODY_FONT);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }
}
