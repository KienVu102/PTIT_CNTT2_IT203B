package Ex_2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT medicine_name, stock FROM Pharmacy";
            rs = stmt.executeQuery(sql);

            System.out.printf("%-20s | %-10s\n", "Tên Thuốc", "Số Lượng");
            System.out.println("-------------------------------------------");

            while (rs.next()) {
                String name = rs.getString("medicine_name");
                int stock = rs.getInt("stock");

                System.out.printf("%-20s | %-10d\n", name, stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}