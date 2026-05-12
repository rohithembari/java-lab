package com.railway.service;

import com.railway.model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to handle data storage using ArrayList and File I/O.
 * Demonstrates Collections Framework.
 */
public class DatabaseService {
    private static DatabaseService instance;
    
    private List<Train> trains;
    private List<Ticket> tickets;
    private List<User> users;
    
    private static final String DATA_PATH = "data/";

    private DatabaseService() {
        trains = new ArrayList<>();
        tickets = new ArrayList<>();
        users = new ArrayList<>();
        loadData();
        if (trains.isEmpty()) {
            seedInitialData();
        }
    }

    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    private void seedInitialData() {
        trains.add(new Train("12626", "Kerala Express", "New Delhi", "Trivandrum", 100, 1200.0));
        trains.add(new Train("12952", "Rajdhani Express", "Mumbai", "New Delhi", 50, 2500.0));
        trains.add(new Train("12002", "Shatabdi Express", "New Delhi", "Bhopal", 80, 1500.0));
        trains.add(new Train("22436", "Vande Bharat", "New Delhi", "Varanasi", 40, 1800.0));
        
        users.add(new User("admin", "admin123", "System Administrator", "ADMIN"));
        users.add(new User("user", "user123", "Demo User", "PASSENGER"));
        
        saveData();
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        trains = (List<Train>) readFile("trains.dat", trains);
        tickets = (List<Ticket>) readFile("tickets.dat", tickets);
        users = (List<User>) readFile("users.dat", users);
    }

    public void saveData() {
        writeFile("trains.dat", trains);
        writeFile("tickets.dat", tickets);
        writeFile("users.dat", users);
    }

    private Object readFile(String fileName, Object defaultValue) {
        File file = new File(DATA_PATH + fileName);
        if (!file.exists()) return defaultValue;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        } catch (Exception e) {
            System.err.println("Error reading " + fileName + ": " + e.getMessage());
            return defaultValue;
        }
    }

    private void writeFile(String fileName, Object data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_PATH + fileName))) {
            oos.writeObject(data);
        } catch (Exception e) {
            System.err.println("Error writing " + fileName + ": " + e.getMessage());
        }
    }

    // Getters
    public List<Train> getTrains() { return trains; }
    public List<Ticket> getTickets() { return tickets; }
    public List<User> getUsers() { return users; }
    
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        saveData();
    }
    
    public void addUser(User user) {
        users.add(user);
        saveData();
    }
}
