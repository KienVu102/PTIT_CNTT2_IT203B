package Ex_1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            System.out.println("Kết nối thành công!");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Patients");

            while (rs.next()) {
                System.out.println("Bệnh nhân: " + rs.getString("fullname"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("Đã đóng kết nối an toàn. Hệ thống sẽ không bị treo!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}