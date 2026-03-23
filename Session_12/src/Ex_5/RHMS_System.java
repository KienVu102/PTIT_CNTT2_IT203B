package Ex_5;

import Data.DBContext;
import java.sql.*;
import java.util.Scanner;

public class RHMS_System {
    private final Scanner sc = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n--- HE THONG QUAN LY NOI TRU RIKKEI-HOSPITAL ---");
            System.out.println("1. Danh sach benh nhan");
            System.out.println("2. Tiep nhan benh nhan moi");
            System.out.println("3. Cap nhat benh an");
            System.out.println("4. Xuat vien & Tinh phi");
            System.out.println("5. Thoat");
            System.out.print("Chon chuc nang: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: showPatients(); break;
                case 2: addPatient(); break;
                case 3: updateDiagnosis(); break;
                case 4: dischargePatient(); break;
                case 5: return;
            }
        }
    }

    // 1. Danh sách bệnh nhân
    private void showPatients() {
        String sql = "SELECT * FROM Inpatients";
        try (Connection conn = DBContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.printf("%-10s | %-20s | %-5s | %-15s\n", "Ma BN", "Ten", "Tuoi", "Khoa");
            while (rs.next()) {
                System.out.printf("%-10s | %-20s | %-5d | %-15s\n",
                        rs.getString("p_id"), rs.getString("p_name"), rs.getInt("p_age"), rs.getString("department"));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    // 2. Tiếp nhận bệnh nhân mới (Xử lý tên có dấu nháy như D'Arcy)
    private void addPatient() {
        System.out.print("Ma BN: "); String id = sc.nextLine();
        System.out.print("Ten BN: "); String name = sc.nextLine();
        System.out.print("Tuoi: "); int age = Integer.parseInt(sc.nextLine());
        System.out.print("Khoa: "); String dept = sc.nextLine();
        System.out.print("So ngay nhap vien: "); int days = Integer.parseInt(sc.nextLine());

        String sql = "INSERT INTO Inpatients (p_id, p_name, p_age, department, admission_days) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setString(4, dept);
            pstmt.setInt(5, days);
            pstmt.executeUpdate();
            System.out.println("Tiep nhan thanh cong!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    // 3. Cập nhật bệnh án
    private void updateDiagnosis() {
        System.out.print("Nhap Ma BN can cap nhat: "); String id = sc.nextLine();
        System.out.print("Chan doan moi: "); String diag = sc.nextLine();

        String sql = "UPDATE Inpatients SET diagnosis = ? WHERE p_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, diag);
            pstmt.setString(2, id);
            if (pstmt.executeUpdate() > 0) System.out.println("Cap nhat thanh cong!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    // 4. Xuất viện & Tính phí (Gọi Stored Procedure)
    private void dischargePatient() {
        System.out.print("Nhap Ma BN xuat vien: "); String id = sc.nextLine();
        String sqlCall = "{call CALCULATE_DISCHARGE_FEE(?, ?)}";

        try (Connection conn = DBContext.getConnection();
             CallableStatement cstmt = conn.prepareCall(sqlCall)) {
            cstmt.setString(1, id);
            cstmt.registerOutParameter(2, Types.DECIMAL);
            cstmt.execute();

            double fee = cstmt.getDouble(2);
            System.out.println("Tong vien phi phai thanh toan: " + fee + " VND");

            // Xóa bệnh nhân sau khi tính phí (Option)
            // conn.createStatement().executeUpdate("DELETE FROM Inpatients WHERE p_id = '" + id + "'");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        new RHMS_System().start();
    }
}