package com.railway.service;

import com.railway.model.User;
import java.util.Optional;

/**
 * Service to handle User Authentication.
 */
public class AuthService {
    private DatabaseService db;
    private static User currentUser;

    public AuthService() {
        this.db = DatabaseService.getInstance();
    }

    public boolean login(String username, String password) {
        Optional<User> user = db.getUsers().stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst();
        
        if (user.isPresent()) {
            currentUser = user.get();
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public boolean register(String username, String password, String fullName) {
        if (db.getUsers().stream().anyMatch(u -> u.getUsername().equals(username))) {
            return false; // Already exists
        }
        User newUser = new User(username, password, fullName, "PASSENGER");
        db.addUser(newUser);
        return true;
    }
}
