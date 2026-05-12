package com.railway.gui;

import com.railway.service.AuthService;
import com.railway.util.StyleUtil;
import javax.swing.*;
import java.awt.*;

/**
 * Main dashboard with sidebar navigation.
 */
public class DashboardPanel extends JPanel {
    private MainFrame mainFrame;
    private JPanel contentArea;
    private CardLayout contentLayout;

    public DashboardPanel(MainFrame frame) {
        this.mainFrame = frame;
        setLayout(new BorderLayout());
        setBackground(StyleUtil.BACKGROUND);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(StyleUtil.SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(200, 700));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel logo = new JLabel("RAILWAY");
        logo.setFont(StyleUtil.TITLE_FONT);
        logo.setForeground(StyleUtil.ACCENT);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel userLabel = new JLabel("Welcome, " + AuthService.getCurrentUser().getFullName());
        userLabel.setFont(StyleUtil.BODY_FONT);
        userLabel.setForeground(StyleUtil.TEXT_SECONDARY);
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(userLabel);
        sidebar.add(Box.createVerticalStrut(40));

        // Navigation Buttons
        String[] buttons = {"Book Ticket", "View Trains", "My History", "Seat Availability", "Logout"};
        for (String btnText : buttons) {
            JButton btn = new JButton(btnText);
            StyleUtil.styleSecondaryButton(btn);
            btn.setMaximumSize(new Dimension(180, 40));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(10));

            btn.addActionListener(e -> handleNavigation(btnText));
        }

        // Content Area
        contentLayout = new CardLayout();
        contentArea = new JPanel(contentLayout);
        contentArea.setBackground(StyleUtil.BACKGROUND);
        contentArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add views to content area
        contentArea.add(new TrainViewPanel(), "View Trains");
        contentArea.add(new BookingPanel(), "Book Ticket");
        contentArea.add(new HistoryPanel(), "My History");
        contentArea.add(new SeatAvailabilityPanel(), "Seat Availability");

        add(sidebar, BorderLayout.WEST);
        add(contentArea, BorderLayout.CENTER);
        
        handleNavigation("View Trains"); // Default view
    }

    private void handleNavigation(String view) {
        if (view.equals("Logout")) {
            mainFrame.showPanel("LOGIN");
        } else {
            contentLayout.show(contentArea, view);
        }
    }
}
