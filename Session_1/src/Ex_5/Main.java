package Ex_5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        try {
            System.out.print("Nhập tuổi người dùng mới: ");
            int inputAge = Integer.parseInt(scanner.nextLine());

            user.setAge(inputAge);
            System.out.println("Hệ thống: Đã lưu tuổi " + user.getAge() + " vào cơ sở dữ liệu.");

        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng: Vui lòng nhập số nguyên!");
        } catch (InvalidAgeException e) {
            System.out.println("Thông báo từ hệ thống: " + e.getMessage());
        } finally {
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
            scanner.close();
        }

        System.out.println(">>> Kết thúc phiên làm việc.");
    }
}
