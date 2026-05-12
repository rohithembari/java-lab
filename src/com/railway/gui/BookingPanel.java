package com.railway.gui;

import com.railway.exception.RailwayException;
import com.railway.model.*;
import com.railway.service.*;
import com.railway.util.StyleUtil;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel to handle ticket booking.
 */
public class BookingPanel extends JPanel {
    private JComboBox<Train> trainCombo;
    private JTextField nameField, ageField, phoneField;
    private JComboBox<String> genderCombo;
    private ReservationSystem reservationSystem;

    public BookingPanel() {
        this.reservationSystem = new ReservationSystem();
        setLayout(new BorderLayout(0, 20));
        setBackground(StyleUtil.BACKGROUND);

        JLabel title = new JLabel("Book Your Ticket");
        title.setFont(StyleUtil.SUBTITLE_FONT);
        title.setForeground(StyleUtil.TEXT_PRIMARY);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(StyleUtil.SIDEBAR_BG);
        form.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Train Selection
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(createLabel("Select Train:"), gbc);
        gbc.gridx = 1;
        trainCombo = new JComboBox<>();
        loadTrains();
        form.add(trainCombo, gbc);

        // Passenger Details
        gbc.gridx = 0; gbc.gridy = 1;
        form.add(createLabel("Passenger Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        StyleUtil.styleTextField(nameField);
        form.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        form.add(createLabel("Age:"), gbc);
        gbc.gridx = 1;
        ageField = new JTextField(20);
        StyleUtil.styleTextField(ageField);
        form.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        form.add(createLabel("Gender:"), gbc);
        gbc.gridx = 1;
        genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        form.add(genderCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        form.add(createLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(20);
        StyleUtil.styleTextField(phoneField);
        form.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JButton bookBtn = new JButton("Confirm Booking");
        StyleUtil.styleButton(bookBtn);
        form.add(bookBtn, gbc);

        add(title, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);

        bookBtn.addActionListener(e -> handleBooking());
    }

    private void loadTrains() {
        List<Train> trains = DatabaseService.getInstance().getTrains();
        for (Train t : trains) {
            trainCombo.addItem(t);
        }
    }

    private void handleBooking() {
        try {
            Train selectedTrain = (Train) trainCombo.getSelectedItem();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = (String) genderCombo.getSelectedItem();
            String phone = phoneField.getText();

            if (name.isEmpty() || phone.isEmpty()) {
                throw new RailwayException("All fields are mandatory!");
            }

            Passenger p = new Passenger(name, age, gender, "P" + (int)(Math.random() * 1000), "user@example.com", phone);
            Ticket t = reservationSystem.bookTicket(selectedTrain, p);

            JOptionPane.showMessageDialog(this, 
                "Booking Successful!\nPNR: " + t.getPnr() + "\nTrain: " + selectedTrain.getTrainName(),
                "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Clear fields
            nameField.setText("");
            ageField.setText("");
            phoneField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (RailwayException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JLabel createLabel(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(StyleUtil.TEXT_PRIMARY);
        l.setFont(StyleUtil.BODY_FONT);
        return l;
    }
}
