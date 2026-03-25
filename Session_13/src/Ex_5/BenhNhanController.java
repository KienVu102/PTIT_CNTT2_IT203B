package Ex_5;
import Database.DBContext;

import java.sql.*;

public class BenhNhanController {
    public void tiepNhanNoiTru(String ten, int tuoi, int maGiuong, double tien) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            conn.setAutoCommit(false);

            String sql1 = "INSERT INTO Patients (name, age) VALUES (?, ?)";
            PreparedStatement ps1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, ten);
            ps1.setInt(2, tuoi);
            ps1.executeUpdate();

            ResultSet rs = ps1.getGeneratedKeys();
            int patientId = 0;
            if (rs.next()) patientId = rs.getInt(1);

            String sql2 = "UPDATE Beds SET status = 'Co nguoi' WHERE bed_id = ? AND status = 'Trong'";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, maGiuong);
            int checkBed = ps2.executeUpdate();
            if (checkBed == 0) throw new Exception("Giường đã bị chiếm hoặc không tồn tại!");

            String sql3 = "INSERT INTO Finance (patient_id, amount) VALUES (?, ?)";
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setInt(1, patientId);
            ps3.setDouble(2, tien);
            ps3.executeUpdate();

            conn.commit();
            System.out.println(">>> TIẾP NHẬN THÀNH CÔNG BỆNH NHÂN: " + ten);
        } catch (Exception e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) {}
            System.err.println(">>> THẤT BẠI: " + e.getMessage());
        } finally {
            try { if (conn != null) { conn.setAutoCommit(true); conn.close(); } } catch (SQLException e) {}
        }
    }

    public void xemGiuongTrong() {
        try (Connection conn = DBContext.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Beds WHERE status = 'Trong'")) {
            System.out.println("--- DANH SÁCH GIƯỜNG TRỐNG ---");
            while (rs.next()) {
                System.out.println("Mã giường: " + rs.getInt("bed_id"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}