package Ex_2;

import Database.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HospitalPaymentService {

    public void thanhToanVienPhi(int patientId, int invoiceId, double amount) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            conn.setAutoCommit(false);

            String sqlDeduct = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sqlDeduct);
            ps1.setDouble(1, amount);
            ps1.setInt(2, patientId);
            ps1.executeUpdate();

            String sqlUpdate = "UPDATE Invoicesss SET status = 'PAID' WHERE invoice_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sqlUpdate);
            ps2.setInt(1, invoiceId);
            ps2.executeUpdate();

            conn.commit();
            System.out.println("Thanh toán thành công!");

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Đã rollback giao dịch thành công!");
                } catch (SQLException ex) {
                    System.out.println("Lỗi khi rollback: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
            System.out.println("Lỗi hệ thống: " + e.getMessage());
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
        HospitalPaymentService service = new HospitalPaymentService();
        service.thanhToanVienPhi(1, 10, 500000.0);
    }
}