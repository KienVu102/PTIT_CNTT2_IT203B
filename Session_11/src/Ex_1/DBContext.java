package Ex_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB";
    private static final String USER = "root";
    private static final String PASS = "cocailon$10206";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            if (conn != null && !conn.isClosed()) {
                System.out.println("HỆ THỐNG: Kết nối đến Hospital_DB thành công!");
            }
            return conn;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Không tìm thấy Driver JDBC: " + e.getMessage());
        }
    }
}