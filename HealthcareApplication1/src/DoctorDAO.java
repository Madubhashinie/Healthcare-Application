
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private static final String URL = "jdbc:sqlite:healthcare.db";

    public static void addDoctor(String name, String birthday, String specialization, String contact) {
        String sql = "INSERT INTO Doctors (Name, Birthday, Specialization, Contact) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, birthday);
            pstmt.setString(3, specialization);
            pstmt.setString(4, contact);
            pstmt.executeUpdate();
            System.out.println("Doctor added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAllDoctors() {
        String sql = "SELECT * FROM Doctors";
        List<String> doctors = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String doctorInfo = "Doctor ID: " + rs.getInt("DoctorId") +
                        ", Name: " + rs.getString("Name") +
                        ", Specialization: " + rs.getString("Specialization") +
                        ", Contact: " + rs.getString("Contact");
                doctors.add(doctorInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctors;
    }
}


