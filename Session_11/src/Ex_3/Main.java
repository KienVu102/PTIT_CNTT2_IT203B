package Ex_3;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã giường cần cập nhật ( nhap 1 hoac 2 ): ");
        String inputId = sc.nextLine();

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DBContext.getConnection();
            stmt = conn.createStatement();

            String sql = "UPDATE Beds SET bed_status = 'Occupied' WHERE bed_id = '" + inputId + "'";

            int rowsAffected = stmt.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Thành công: Đã cập nhật giường " + inputId + " sang 'Dang su dung'.");
            } else {
                System.out.println("Lỗi: Mã giường '" + inputId + "' không tồn tại trong hệ thống!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}