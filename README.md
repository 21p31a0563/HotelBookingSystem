🏨 Hotel Booking System
A simple Java-based hotel booking management system using MySQL and JDBC. The application allows you to view available rooms and can be extended to handle bookings, check-ins, check-outs, and more.

🚀 Features
View available hotel rooms
Connects to MySQL using JDBC
Clean and modular structure (RoomManager, DatabaseConnection, etc.)
Easily extendable for full hotel booking functionality
🛠️ Technologies Used
Java
MySQL
JDBC
IntelliJ IDEA
📁 Project Structure
✅ Project Structure (Package: hotel)

hotel/ │ ├── HotelBookingApp.java → Main class with user menu ├── Booking.java → Handles booking logic ├── BookingManager.java → Cancels bookings ├── RoomManager.java → Shows available rooms ├── DatabaseConnection.java → Manages DB connection └── [Database: MySQL/PostgreSQL with tables]

⸻

🗃 Database Tables

Rooms
room_id INT PRIMARY KEY room_type VARCHAR price DECIMAL status VARCHAR -- "Available" / "Booked"

Bookings
booking_id INT PRIMARY KEY AUTO_INCREMENT room_id INT FOREIGN KEY → Rooms(room_id) customer_name VARCHAR check_in DATE / check_out DATE/

⸻

🔄 Application Flow

🟢 Step 1: App Starts

User sees a menu from HotelBookingApp.java.

System.out.println("1. View Available Rooms"); System.out.println("2. Book a Room"); System.out.println("3. Cancel Booking"); System.out.println("4. Exit");

⸻

🟡 Step 2: View Available Rooms

Called:

RoomManager.showAvailableRooms();

Inside RoomManager.java:

SELECT * FROM Rooms WHERE status = 'Available';

➡ Displays all rooms where status = “Available”.

⸻

🔵 Step 3: Book a Room

Called:

Booking.bookRoom();

Inside Booking.java: 1. User enters name and room ID. 2. Room status is checked. 3. If available: • Insert booking into Bookings table. • Update Rooms table → status='Booked'.

SQL Used:

INSERT INTO Bookings (...) VALUES (...); UPDATE Rooms SET status='Booked' WHERE room_id=?;

⸻

🔴 Step 4: Cancel a Booking

Called:

BookingManager.cancelBooking();

Inside BookingManager.java: 1. User enters Booking ID. 2. System finds associated room ID. 3. Deletes the booking. 4. Updates room status back to ‘Available’.

SQL Used:

DELETE FROM Bookings WHERE booking_id=?; UPDATE Rooms SET status='Available' WHERE room_id=?;

⸻

⚙ Step 5: Database Connection

Called:

DatabaseConnection.getConnection();

Inside DatabaseConnection.java: Returns a single JDBC connection using:

DriverManager.getConnection(url, user, password);

⸻

✅ Summary: Complete Working

Feature What Happens View Rooms Fetch rooms where status = 'Available' Book Room Insert into Bookings, then mark room as 'Booked' Cancel Booking Delete from Bookings, then mark associated room as 'Available' Exit Exits the application
