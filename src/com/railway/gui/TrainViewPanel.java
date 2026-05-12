package com.railway.gui;

import com.railway.model.Train;
import com.railway.service.DatabaseService;
import com.railway.util.StyleUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Panel to display list of trains with search functionality.
 */
public class TrainViewPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;

    public TrainViewPanel() {
        setLayout(new BorderLayout(0, 20));
        setBackground(StyleUtil.BACKGROUND);

        JLabel title = new JLabel("Available Trains");
        title.setFont(StyleUtil.SUBTITLE_FONT);
        title.setForeground(StyleUtil.TEXT_PRIMARY);

        // Search Bar
        JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchBar.setBackground(StyleUtil.BACKGROUND);
        
        JLabel searchLabel = new JLabel("Search by Destination: ");
        searchLabel.setForeground(StyleUtil.TEXT_SECONDARY);
        searchField = new JTextField(20);
        StyleUtil.styleTextField(searchField);
        
        JButton searchBtn = new JButton("Search");
        StyleUtil.styleButton(searchBtn);
        
        searchBar.add(searchLabel);
        searchBar.add(searchField);
        searchBar.add(searchBtn);

        // Table
        String[] cols = {"Train No", "Name", "Source", "Destination", "Seats", "Fare"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        StyleUtil.styleTable(table);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(StyleUtil.BACKGROUND);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(title, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new BorderLayout(0, 10));
        centerPanel.setBackground(StyleUtil.BACKGROUND);
        centerPanel.add(searchBar, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);

        // Load Data
        loadTrains("");

        searchBtn.addActionListener(e -> loadTrains(searchField.getText()));
    }

    private void loadTrains(String filter) {
        model.setRowCount(0);
        List<Train> trains = DatabaseService.getInstance().getTrains();
        
        if (!filter.isEmpty()) {
            trains = trains.stream()
                    .filter(t -> t.getDestination().toLowerCase().contains(filter.toLowerCase()))
                    .collect(Collectors.toList());
        }

        for (Train t : trains) {
            model.addRow(new Object[]{
                t.getTrainNumber(), t.getTrainName(), t.getSource(), 
                t.getDestination(), t.getAvailableSeats(), t.getFare()
            });
        }
    }
}
