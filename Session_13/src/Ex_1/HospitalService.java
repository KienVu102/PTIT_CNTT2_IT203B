

package Ex_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Database.DBContext;


public class HospitalService {

    public void capPhatThuoc(int medicineId, int patientId) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();

            conn.setAutoCommit(false);

            String sqlUpdate = "UPDATE Medicine_Inventory SET quantity = quantity - 1 WHERE medicine_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sqlUpdate);
            ps1.setInt(1, medicineId);
            ps1.executeUpdate();
            System.out.println("-> Đã trừ 1 thuốc trong kho.");


            String sqlInsert = "INSERT INTO Prescription_History (patient_id, medicine_id, date) VALUES (?, ?, NOW())";
            PreparedStatement ps2 = conn.prepareStatement(sqlInsert);
            ps2.setInt(1, patientId);
            ps2.setInt(2, medicineId);
            ps2.executeUpdate();
            System.out.println("-> Đã ghi hồ sơ bệnh án.");

            conn.commit();
            System.out.println("SUCCESS: Giao dịch thành công!");

        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.err.println("ERROR: Có lỗi xảy ra. Đã Rollback dữ liệu (Thuốc sẽ không bị trừ)!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Chi tiết lỗi: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        HospitalService service = new HospitalService();
        // Giả sử bệnh nhân ID 1 lấy thuốc ID 101
        service.capPhatThuoc(101, 1);
    }
}
