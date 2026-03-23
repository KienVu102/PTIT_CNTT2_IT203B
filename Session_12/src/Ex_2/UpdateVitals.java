package Ex_2;

import Data.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateVitals {

    public void updatePatientVitals(int patientId, double temp, int heartRate) {
        String sql = "UPDATE Vitals SET temperature = ?, heart_rate = ? WHERE p_id = ?";

        try (Connection conn = DBContext.getConnection()) {
            if (conn == null) return;

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDouble(1, temp);
                pstmt.setInt(2, heartRate);
                pstmt.setInt(3, patientId);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Cap nhat thanh cong ID: " + patientId);
                } else {
                    System.out.println("Khong tim thay ID: " + patientId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UpdateVitals().updatePatientVitals(1, 37.5, 85);
    }
}