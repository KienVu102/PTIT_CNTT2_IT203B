package Ex_5;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static DoctorService doctorService = new DoctorService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- HỆ THỐNG RIKKEI-CARE ---");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ mới");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng: ");
            
            try {
                int choice = Integer.parseInt(sc.nextLine());
                
                switch (choice) {
                    case 1: showAllDoctors(); break;
                    case 2: addDoctor(); break;
                    case 3: statisticsBySpec(); break;
                    case 4: System.exit(0);
                    default: System.out.println("Chọn sai!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số từ 1-4!");
            }
        }
    }

    private static void showAllDoctors() {
        try {
            List<Doctor> doctors = doctorService.getAllDoctors();
            
            System.out.printf("%-10s | %-20s | %-20s\n", "Mã số", "Họ tên", "Chuyên khoa");
            System.out.println("--------------------------------------------");
            
            for (Doctor doctor : doctors) {
                System.out.printf("%-10s | %-20s | %-20s\n",
                    doctor.getId(), doctor.getName(), doctor.getSpec());
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách bác sĩ: " + e.getMessage());
        }
    }

    private static void addDoctor() {
        try {
            System.out.print("Nhập mã bác sĩ: ");
            String id = sc.nextLine();
            System.out.print("Nhập họ tên: ");
            String name = sc.nextLine();
            System.out.print("Nhập chuyên khoa: ");
            String spec = sc.nextLine();
            
            if (id.trim().isEmpty() || name.trim().isEmpty() || spec.trim().isEmpty()) {
                System.out.println("Lỗi: Không được để trống các trường thông tin!");
                return;
            }
            
            Doctor doctor = new Doctor(id, name, spec);
            boolean success = doctorService.addDoctor(doctor);
            
            if (success) {
                System.out.println("Thêm bác sĩ thành công!");
            } else {
                System.out.println("Lỗi: Không thể thêm bác sĩ!");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
            System.out.println("Nguyên nhân có thể: Mã bác sĩ đã tồn tại hoặc dữ liệu quá dài!");
        }
    }

    private static void statisticsBySpec() {
        try {
            doctorService.displayDoctorStatistics();
        } catch (SQLException e) {
            System.out.println("Lỗi khi thống kê: " + e.getMessage());
        }
    }
}
