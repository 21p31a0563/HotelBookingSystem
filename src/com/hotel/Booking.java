package com.hotel;

import java.util.*;
import java.sql.*;

public class Booking {
    public static void bookRoom() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Your Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Room ID: ");
        int roomId = scanner.nextInt();

        System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
        String checkIn = scanner.next();

        System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
        String checkOut = scanner.next();

        try {
            // Simple connection (edit DB URL, username, password as needed)
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/HotelBookingDB", "root", "alisha@786"
            );

            // Insert booking
            String insertSQL = "INSERT INTO Bookings (customer_name, room_id, check_in, check_out) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(insertSQL);
            pst.setString(1, name);
            pst.setInt(2, roomId);
            pst.setString(3, checkIn);
            pst.setString(4, checkOut);
            int inserted = pst.executeUpdate();

            if (inserted > 0) {
                // Update room status
                String updateSQL = "UPDATE Rooms SET status='Booked' WHERE room_id=?";
                PreparedStatement pst2 = conn.prepareStatement(updateSQL);
                pst2.setInt(1, roomId);
                pst2.executeUpdate();
                System.out.println("Room booked successfully!");
            } else {
                System.out.println("Booking failed.");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}