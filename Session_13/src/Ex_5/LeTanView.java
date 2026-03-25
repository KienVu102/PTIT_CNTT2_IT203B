package Ex_5;
import java.util.Scanner;

public class LeTanView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BenhNhanController controller = new BenhNhanController();

        while (true) {
            System.out.println("\n===== RIKKEI HOSPITAL - TIẾP NHẬN 1 CHẠM =====");
            System.out.println("1. Xem giường trống");
            System.out.println("2. Tiếp nhận bệnh nhân mới");
            System.out.println("3. Thoát");
            System.out.print("Chọn chức năng: ");

            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    controller.xemGiuongTrong();
                    break;
                case "2":
                    try {
                        System.out.print("Nhập tên BN: ");
                        String ten = sc.nextLine();
                        if (ten.trim().isEmpty()) throw new Exception("Tên không được để trống!");

                        System.out.print("Nhập tuổi: ");
                        int tuoi = Integer.parseInt(sc.nextLine());
                        if (tuoi <= 0) throw new Exception("Tuổi phải lớn hơn 0!");

                        System.out.print("Chọn mã giường: ");
                        int maGiuong = Integer.parseInt(sc.nextLine());

                        System.out.print("Số tiền tạm ứng: ");
                        double tien = Double.parseDouble(sc.nextLine());
                        if (tien < 0) throw new Exception("Tiền không được âm!");

                        controller.tiepNhanNoiTru(ten, tuoi, maGiuong, tien);
                    } catch (NumberFormatException e) {
                        System.err.println("Lỗi: Vui lòng chỉ nhập số cho Tuổi, Giường và Tiền!");
                    } catch (Exception e) {
                        System.err.println("Lỗi: " + e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("Hẹn gặp lại!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}