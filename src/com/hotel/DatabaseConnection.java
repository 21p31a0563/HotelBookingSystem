package com.hotel;

import java.sql.*;


public class DatabaseConnection {
    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/HotelBookingDB?useSSL=false&allowPublicKeyRetrieval=true", "root", "alisha@786");
            }
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
        return conn;
    }
}