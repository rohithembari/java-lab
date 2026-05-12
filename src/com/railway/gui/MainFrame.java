package com.railway.gui;

import com.railway.util.StyleUtil;
import javax.swing.*;
import java.awt.*;

/**
 * Main application frame that handles navigation between screens.
 */
public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Railway Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(StyleUtil.BACKGROUND);

        // Add Screens
        mainPanel.add(new LoginPanel(this), "LOGIN");
        
        add(mainPanel);
        setVisible(true);
    }

    public void showPanel(String name) {
        if (name.equals("DASHBOARD")) {
            // Re-create dashboard to ensure data is fresh
            mainPanel.add(new DashboardPanel(this), "DASHBOARD");
        }
        cardLayout.show(mainPanel, name);
    }

    public static void main(String[] args) {
        // Set Look and Feel to System (or Nimbus for a bit more modern feel)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(MainFrame::new);
    }
}
