package com.railway.exception;

/**
 * Custom Exception for Railway System errors.
 * Demonstrates Exception Handling.
 */
public class RailwayException extends Exception {
    public RailwayException(String message) {
        super(message);
    }
}
