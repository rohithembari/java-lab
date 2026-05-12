package com.railway.model;

/**
 * Passenger class representing a traveler.
 * Demonstrates Inheritance and Encapsulation.
 */
public class Passenger extends Person {
    private String passengerId;
    private String email;
    private String phoneNumber;

    public Passenger(String name, int age, String gender, String passengerId, String email, String phoneNumber) {
        super(name, age, gender);
        this.passengerId = passengerId;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getRole() {
        return "Passenger";
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return super.toString() + ", ID: " + passengerId + ", Email: " + email;
    }
}
