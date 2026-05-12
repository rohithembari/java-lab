package com.railway.service;

import com.railway.model.Passenger;
import com.railway.model.Train;
import com.railway.model.Ticket;

/**
 * Interface defining booking behavior.
 * Demonstrates the concept of Abstraction/Interface.
 */
public interface Bookable {
    Ticket bookTicket(Train train, Passenger passenger) throws Exception;
    boolean isAvailable(Train train);
}
