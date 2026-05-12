# Railway Reservation System - Java OOP Lab Project

A modern, attractive Railway Reservation System built using Java Swing and Core Java OOP principles. This project demonstrates advanced concepts like Abstraction, Inheritance, Polymorphism, Encapsulation, and the Collections Framework.

## Features
- **Modern Dark UI**: Premium aesthetic with custom styling.
- **User Authentication**: Login and Registration system.
- **Train Management**: Search and view available trains.
- **Ticket Booking**: Automated PNR generation and fare calculation.
- **Seat Management**: Real-time seat count updates.
- **Booking History**: View all booked tickets.
- **Cancellation**: Easy ticket cancellation with seat recovery.
- **Data Persistence**: Saves data to local files (`.dat`) automatically.

## OOP Concepts Used
1. **Abstraction**: Use of Abstract class `Person` and Interfaces `Bookable` and `Cancelable`.
2. **Inheritance**: `Passenger` class extends `Person`.
3. **Polymorphism**: Interface implementation in `ReservationSystem`.
4. **Encapsulation**: Private fields with public getters/setters in all models.
5. **Collections**: `ArrayList` used to manage trains, tickets, and users.
6. **Exception Handling**: Custom `RailwayException` for business logic errors.

## Project Structure
- `com.railway.model`: Data entities (Train, Passenger, Ticket, User).
- `com.railway.service`: Business logic and persistence (Auth, Booking, Database).
- `com.railway.gui`: Swing components and panels.
- `com.railway.util`: Styling and helper utilities.
- `com.railway.exception`: Custom exception classes.

## How to Run
1. **Prerequisites**: Java JDK 8 or higher installed.
2. **Setup**:
   - Open the project in IntelliJ IDEA, NetBeans, or Eclipse.
   - Ensure the `src` directory is marked as the Source Root.
3. **Execution**:
   - Run the `com.railway.gui.MainFrame` class.
4. **Sample Credentials**:
   - Username: `admin`, Password: `admin123`
   - Username: `user`, Password: `user123`

## Screenshots
*(UI features a dark mode theme with accent blue buttons and responsive tables)*

---
Developed as a B.Tech OOPS through Java lab project.
