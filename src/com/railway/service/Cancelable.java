package com.railway.service;

import com.railway.model.Ticket;

/**
 * Interface defining cancellation behavior.
 */
public interface Cancelable {
    boolean cancelTicket(String pnr) throws Exception;
}
