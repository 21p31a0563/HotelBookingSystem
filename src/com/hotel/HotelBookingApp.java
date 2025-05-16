package com.hotel;

import java.util.Scanner;

public class HotelBookingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Hotel Booking System ===");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Enter Choice: ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                RoomManager.showAvailableRooms();
            } else if (choice == 2) {
                Booking.bookRoom();
            } else if (choice == 3) {
                BookingManager.cancelBooking();
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice! Try again.");
            }
        }

        scanner.close();
    }
}