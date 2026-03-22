package Ex_4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên bệnh nhân: ");
        String inputName = sc.nextLine();

        String safeName = inputName.replace("'", "").replace(";", "").replace("--", "");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Patients WHERE fullname = '" + safeName + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Bệnh nhân: " + rs.getString("fullname") + " | Chẩn đoán: " + rs.getString("diagnosis"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}