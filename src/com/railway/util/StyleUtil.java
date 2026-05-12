package com.railway.util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Utility class for UI styling and modern aesthetics.
 */
public class StyleUtil {
    // Colors
    public static final Color BACKGROUND = new Color(20, 20, 25);
    public static final Color SIDEBAR_BG = new Color(30, 30, 35);
    public static final Color CARD_BG = new Color(40, 40, 45);
    public static final Color ACCENT = new Color(70, 130, 255);
    public static final Color TEXT_PRIMARY = new Color(240, 240, 245);
    public static final Color TEXT_SECONDARY = new Color(170, 170, 180);
    public static final Color SUCCESS = new Color(80, 200, 120);
    public static final Color DANGER = new Color(220, 60, 60);

    // Fonts
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font BODY_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public static void styleButton(JButton button) {
        button.setBackground(ACCENT);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setFont(BUTTON_FONT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void styleSecondaryButton(JButton button) {
        button.setBackground(CARD_BG);
        button.setForeground(TEXT_PRIMARY);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(TEXT_SECONDARY, 1));
        button.setFont(BUTTON_FONT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void styleTextField(JTextField textField) {
        textField.setBackground(CARD_BG);
        textField.setForeground(TEXT_PRIMARY);
        textField.setCaretColor(TEXT_PRIMARY);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 70), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        textField.setFont(BODY_FONT);
    }
    
    public static void styleTable(JTable table) {
        table.setBackground(CARD_BG);
        table.setForeground(TEXT_PRIMARY);
        table.setGridColor(new Color(60, 60, 70));
        table.setSelectionBackground(ACCENT);
        table.setSelectionForeground(Color.WHITE);
        table.setRowHeight(30);
        table.setFont(BODY_FONT);
        table.getTableHeader().setBackground(SIDEBAR_BG);
        table.getTableHeader().setForeground(TEXT_PRIMARY);
        table.getTableHeader().setFont(BUTTON_FONT);
    }
}
