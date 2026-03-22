package Ex_5;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- HỆ THỐNG RIKKEI-CARE ---");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ mới");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: showAllDoctors(); break;
                case 2: addDoctor(); break;
                case 3: statisticsBySpec(); break;
                case 4: System.exit(0);
                default: System.out.println("Chọn sai!");
            }
        }
    }

    private static void showAllDoctors() {
        try (Connection conn = DBContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Doctors")) {
            System.out.printf("%-10s | %-20s | %-20s\n", "Mã số", "Họ tên", "Chuyên khoa");
            while (rs.next()) {
                System.out.printf("%-10s | %-20s | %-20s\n",
                        rs.getString("doctor_id"), rs.getString("full_name"), rs.getString("specialization"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private static void addDoctor() {
        System.out.print("Nhập mã bác sĩ: "); String id = sc.nextLine();
        System.out.print("Nhập họ tên: "); String name = sc.nextLine();
        System.out.print("Nhập chuyên khoa: "); String spec = sc.nextLine();

        String sql = "INSERT INTO Doctors VALUES (?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, spec);
            pstmt.executeUpdate();
            System.out.println("Thêm thành công!");
        } catch (SQLException e) {
            System.out.println("Lỗi: Mã bác sĩ có thể đã tồn tại hoặc dữ liệu quá dài!");
        }
    }

    private static void statisticsBySpec() {
        String sql = "SELECT specialization, COUNT(*) as count FROM Doctors GROUP BY specialization";
        try (Connection conn = DBContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.printf("%-20s | %-10s\n", "Chuyên khoa", "Số lượng");
            while (rs.next()) {
                System.out.printf("%-20s | %-10d\n", rs.getString("specialization"), rs.getInt("count"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}