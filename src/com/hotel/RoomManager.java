package com.hotel;//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class RoomManager {
//    public static void showAvailableRooms() {
//        try (Connection conn = DatabaseConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM Rooms WHERE status='Available'")) {
//
//            System.out.println("Available Rooms:");
//            while (rs.next()) {
//                System.out.println("Room ID: " + rs.getInt("room_id") + ", Type: " +
//                        rs.getString("room_type") + ", Price: $" + rs.getDouble("price"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
import com.hotel.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RoomManager {
    public static void showAvailableRooms() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Rooms WHERE status='Available'")) {

            System.out.println("Available Rooms:");
            while (rs.next()) {
                System.out.println("Room ID: " + rs.getInt("room_id") + ", Type: " +
                        rs.getString("room_type") + ", Price: $" + rs.getDouble("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}