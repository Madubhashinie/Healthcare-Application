# Healthcare-Application


# Overview
The Healthcare Management System is a Java application designed to manage doctors and patients in a healthcare facility. It supports adding, viewing, and managing data using an SQLite database.

# Core Features
Doctor Availability Management

Patient Booking



# Technologies Used

**Programming Language:** Java 

**Database:** SQLite 

**IDE:** IntelliJ IDEA


# Prerequisites
Before running the application, ensure you have the following installed:

**Java Development Kit (JDK) (version 17 or 21)**

**SQLite JDBC Driver:**
Download the driver from SQLite JDBC Driver.

**IntelliJ IDEA**

# Database Setup Instructions
Create an SQLite database file named healthcare.db.

Create the necessary tables using the following SQL commands:

<code>
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase() {
        String url = "jdbc:sqlite:healthcare.db"; // This creates a file named 'healthcare.db' in your project folder

        String createDoctorsTable = "CREATE TABLE IF NOT EXISTS Doctors (" +
                "DoctorId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL," +
                "Birthday TEXT," +
                "Specialization TEXT," +
                "Contact TEXT" +
                ");";

        String createPatientsTable = "CREATE TABLE IF NOT EXISTS Patients (" +
                "PatientId TEXT PRIMARY KEY," +
                "Name TEXT NOT NULL," +
                "Contact TEXT" +
                ");";

        String createAppointmentsTable = "CREATE TABLE IF NOT EXISTS Appointments (" +
                "AppointmentId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "DoctorId INTEGER," +
                "PatientId TEXT," +
                "Date TEXT," +
                "Time TEXT," +
                "Type TEXT," +
                "Notes TEXT," +
                "FOREIGN KEY (DoctorId) REFERENCES Doctors(DoctorId)," +
                "FOREIGN KEY (PatientId) REFERENCES Patients(PatientId)" +
                ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createDoctorsTable);
            stmt.execute(createPatientsTable);
            stmt.execute(createAppointmentsTable);
            System.out.println("Database initialized successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
</code>


# Running the Application

Open the project in IntelliJ IDEA.

Run the Main.java file to start the application.

Follow the menu prompts to add, view, and manage doctors and patients.


# Error Handling

Duplicate Entry Error: If you encounter a PRIMARY KEY constraint failed error, ensure that DoctorId or PatientId is not being manually set. These fields are auto-incremented.

No Suitable Driver: Verify that the SQLite JDBC driver is correctly added to the project dependencies.








