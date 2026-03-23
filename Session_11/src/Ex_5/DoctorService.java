package Ex_5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";
        
        try (Connection conn = DBContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Doctor doctor = new Doctor(
                    rs.getString("doctor_id"),
                    rs.getString("full_name"),
                    rs.getString("specialization")
                );
                doctors.add(doctor);
            }
        }
        return doctors;
    }
    
    public boolean addDoctor(Doctor doctor) throws SQLException {
        String sql = "INSERT INTO Doctors VALUES (?, ?, ?)";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, doctor.getId());
            pstmt.setString(2, doctor.getName());
            pstmt.setString(3, doctor.getSpec());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    public void displayDoctorStatistics() throws SQLException {
        String sql = "SELECT specialization, COUNT(*) as count FROM Doctors GROUP BY specialization";
        
        try (Connection conn = DBContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.printf("%-20s | %-10s\n", "Chuyên khoa", "Số lượng");
            System.out.println("--------------------------------");
            
            while (rs.next()) {
                System.out.printf("%-20s | %-10d\n", 
                    rs.getString("specialization"), 
                    rs.getInt("count"));
            }
        }
    }
}
