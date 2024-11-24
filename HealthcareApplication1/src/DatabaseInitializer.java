
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

