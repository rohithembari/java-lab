package com.railway.gui;

import com.railway.model.Train;
import com.railway.service.DatabaseService;
import com.railway.util.StyleUtil;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel to visually display seat availability using progress bars.
 */
public class SeatAvailabilityPanel extends JPanel {
    
    public SeatAvailabilityPanel() {
        setLayout(new BorderLayout(0, 20));
        setBackground(StyleUtil.BACKGROUND);

        JLabel title = new JLabel("Seat Availability Overview");
        title.setFont(StyleUtil.SUBTITLE_FONT);
        title.setForeground(StyleUtil.TEXT_PRIMARY);
        add(title, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(StyleUtil.BACKGROUND);

        List<Train> trains = DatabaseService.getInstance().getTrains();
        for (Train t : trains) {
            listPanel.add(createTrainStatusCard(t));
            listPanel.add(Box.createVerticalStrut(15));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.getViewport().setBackground(StyleUtil.BACKGROUND);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createTrainStatusCard(Train t) {
        JPanel card = new JPanel(new BorderLayout(20, 10));
        card.setBackground(StyleUtil.CARD_BG);
        card.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        card.setMaximumSize(new Dimension(800, 100));

        JLabel nameLabel = new JLabel(t.getTrainName() + " (" + t.getTrainNumber() + ")");
        nameLabel.setFont(StyleUtil.BUTTON_FONT);
        nameLabel.setForeground(StyleUtil.TEXT_PRIMARY);

        int available = t.getAvailableSeats();
        int total = t.getTotalSeats();
        int booked = total - available;
        int percentage = (int) (((double) booked / total) * 100);

        JProgressBar bar = new JProgressBar(0, 100);
        bar.setValue(percentage);
        bar.setStringPainted(true);
        bar.setString(available + " / " + total + " seats available");
        bar.setBackground(StyleUtil.SIDEBAR_BG);
        bar.setForeground(percentage > 90 ? StyleUtil.DANGER : StyleUtil.SUCCESS);

        card.add(nameLabel, BorderLayout.NORTH);
        card.add(bar, BorderLayout.CENTER);

        return card;
    }
}
