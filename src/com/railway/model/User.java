package com.railway.model;

import java.io.Serializable;

/**
 * User class for login credentials.
 */
public class User implements Serializable {
    private String username;
    private String password;
    private String fullName;
    private String role; // ADMIN, PASSENGER

    public User(String username, String password, String fullName, String role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return username + " (" + role + ")";
    }
}
