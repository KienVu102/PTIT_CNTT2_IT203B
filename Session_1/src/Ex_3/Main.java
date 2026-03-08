package Ex_3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        try {
            System.out.print("Nhập tuổi đăng ký: ");
            int inputAge = Integer.parseInt(scanner.nextLine());

            user.setAge(inputAge);
            System.out.println("Cập nhật tuổi thành công: " + user.getAge());

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập số nguyên!");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi nghiệp vụ: " + e.getMessage());
        } finally {
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
            scanner.close();
        }

        System.out.println(">>> Hệ thống kết thúc an toàn.");
    }
}
