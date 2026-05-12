package com.railway.service;

import com.railway.exception.RailwayException;
import com.railway.model.*;
import java.util.UUID;

/**
 * Core service for handling reservations.
 * Demonstrates Polymorphism and Interfaces.
 */
public class ReservationSystem implements Bookable, Cancelable {
    private DatabaseService db;

    public ReservationSystem() {
        this.db = DatabaseService.getInstance();
    }

    @Override
    public Ticket bookTicket(Train train, Passenger passenger) throws RailwayException {
        if (!isAvailable(train)) {
            throw new RailwayException("No seats available in " + train.getTrainName());
        }

        if (train.bookSeat()) {
            String pnr = "PNR" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            Ticket ticket = new Ticket(pnr, train, passenger, train.getFare());
            db.addTicket(ticket);
            db.saveData();
            return ticket;
        } else {
            throw new RailwayException("Booking failed: Seat count mismatch.");
        }
    }

    @Override
    public boolean isAvailable(Train train) {
        return train.getAvailableSeats() > 0;
    }

    @Override
    public boolean cancelTicket(String pnr) throws RailwayException {
        Ticket ticketToCancel = null;
        for (Ticket ticket : db.getTickets()) {
            if (ticket.getPnr().equals(pnr) && ticket.getStatus().equals("BOOKED")) {
                ticketToCancel = ticket;
                break;
            }
        }

        if (ticketToCancel != null) {
            ticketToCancel.setStatus("CANCELLED");
            ticketToCancel.getTrain().cancelSeat();
            db.saveData();
            return true;
        } else {
            throw new RailwayException("Valid ticket with PNR " + pnr + " not found.");
        }
    }
}
