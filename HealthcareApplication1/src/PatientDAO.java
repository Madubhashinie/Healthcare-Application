
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private static final String URL = "jdbc:sqlite:healthcare.db";

    public static void addPatient(String id, String name, String contact) {
        String sql = "INSERT INTO Patients (PatientId, Name, Contact) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, contact);
            pstmt.executeUpdate();
            System.out.println("Patient added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAllPatients() {
        String sql = "SELECT * FROM Patients";
        List<String> patients = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String patientInfo = "Patient ID: " + rs.getString("PatientId") +
                        ", Name: " + rs.getString("Name") +
                        ", Contact: " + rs.getString("Contact");
                patients.add(patientInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return patients;
    }
}


