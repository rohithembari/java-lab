package com.railway.gui;

import com.railway.model.Ticket;
import com.railway.service.DatabaseService;
import com.railway.service.ReservationSystem;
import com.railway.util.StyleUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel to display booking history and allow cancellation.
 */
public class HistoryPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private ReservationSystem reservationSystem;

    public HistoryPanel() {
        this.reservationSystem = new ReservationSystem();
        setLayout(new BorderLayout(0, 20));
        setBackground(StyleUtil.BACKGROUND);

        JLabel title = new JLabel("Booking History");
        title.setFont(StyleUtil.SUBTITLE_FONT);
        title.setForeground(StyleUtil.TEXT_PRIMARY);

        String[] cols = {"PNR", "Passenger", "Train", "Fare", "Status"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        StyleUtil.styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(StyleUtil.BACKGROUND);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JButton cancelBtn = new JButton("Cancel Selected Ticket");
        StyleUtil.styleButton(cancelBtn);
        cancelBtn.setBackground(StyleUtil.DANGER);

        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(cancelBtn, BorderLayout.SOUTH);

        loadHistory();

        cancelBtn.addActionListener(e -> handleCancellation());
    }

    private void loadHistory() {
        model.setRowCount(0);
        List<Ticket> tickets = DatabaseService.getInstance().getTickets();
        for (Ticket t : tickets) {
            model.addRow(new Object[]{
                t.getPnr(), t.getPassenger().getName(), 
                t.getTrain().getTrainName(), t.getTotalFare(), t.getStatus()
            });
        }
    }

    private void handleCancellation() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a ticket to cancel.");
            return;
        }

        String pnr = (String) model.getValueAt(row, 0);
        String status = (String) model.getValueAt(row, 4);

        if (status.equals("CANCELLED")) {
            JOptionPane.showMessageDialog(this, "Ticket is already cancelled.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel PNR: " + pnr + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (reservationSystem.cancelTicket(pnr)) {
                    JOptionPane.showMessageDialog(this, "Ticket cancelled successfully.");
                    loadHistory();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
