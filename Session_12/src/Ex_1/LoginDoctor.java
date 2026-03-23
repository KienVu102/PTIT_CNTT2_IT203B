package Ex_1;

import Data.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class LoginDoctor {

    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã bác sĩ: ");
        String doctorCode = sc.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String pass = sc.nextLine();

        String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, doctorCode);
            pstmt.setString(2, pass);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("-----------------------------------------");
                System.out.println("ĐĂNG NHẬP THÀNH CÔNG!");
                System.out.println("Chào bác sĩ: " + rs.getString("full_name"));
                System.out.println("Chuyên khoa: " + rs.getString("specialization"));
            } else {
                System.out.println("Lỗi: Sai mã số hoặc mật khẩu!");
            }

        } catch (Exception e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new LoginDoctor().login();
    }
}