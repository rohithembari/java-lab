package com.railway.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Ticket class representing a booking.
 */
public class Ticket implements Serializable {
    private String pnr;
    private Train train;
    private Passenger passenger;
    private Date bookingDate;
    private String status; // BOOKED, CANCELLED
    private double totalFare;

    public Ticket(String pnr, Train train, Passenger passenger, double totalFare) {
        this.pnr = pnr;
        this.train = train;
        this.passenger = passenger;
        this.bookingDate = new Date();
        this.status = "BOOKED";
        this.totalFare = totalFare;
    }

    // Getters and Setters
    public String getPnr() {
        return pnr;
    }

    public Train getTrain() {
        return train;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalFare() {
        return totalFare;
    }

    @Override
    public String toString() {
        return "PNR: " + pnr + " | Train: " + train.getTrainName() + " | Status: " + status;
    }
}
