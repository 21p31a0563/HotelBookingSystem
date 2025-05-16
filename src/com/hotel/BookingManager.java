package com.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BookingManager {
    public static void cancelBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Booking ID to Cancel: ");
        int bookingId = scanner.nextInt();

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.out.println("Database connection failed.");
                return;
            }

            // 1. Get room_id for the booking
            String getRoomQuery = "SELECT room_id FROM Bookings WHERE booking_id = ?";
            try (PreparedStatement getRoomStmt = conn.prepareStatement(getRoomQuery)) {
                getRoomStmt.setInt(1, bookingId);
                try (ResultSet rs = getRoomStmt.executeQuery()) {
                    if (rs.next()) {
                        int roomId = rs.getInt("room_id");

                        // 2. Delete the booking
                        String deleteSql = "DELETE FROM Bookings WHERE booking_id = ?";
                        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                            deleteStmt.setInt(1, bookingId);
                            int rows = deleteStmt.executeUpdate();

                            // 3. Update room status
                            if (rows > 0) {
                                String updateRoom = "UPDATE Rooms SET status = 'Available' WHERE room_id = ?";
                                try (PreparedStatement updateStmt = conn.prepareStatement(updateRoom)) {
                                    updateStmt.setInt(1, roomId);
                                    updateStmt.executeUpdate();
                                    System.out.println("Booking cancelled and room marked as Available.");
                                }
                            } else {
                                System.out.println("No booking found with ID: " + bookingId);
                            }
                        }
                    } else {
                        System.out.println("Booking ID not found.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while cancelling booking:");
            e.printStackTrace();
        }
    }
}