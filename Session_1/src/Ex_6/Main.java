package Ex_6;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        try {
            System.out.print("Nhập tên người dùng: ");
            String nameInput = scanner.nextLine();
            if (!nameInput.trim().isEmpty()) {
                user.setName(nameInput);
            }

            System.out.print("Nhập tuổi: ");
            int ageInput = Integer.parseInt(scanner.nextLine());
            user.setAge(ageInput);

            System.out.print("Nhập số nhóm cần chia: ");
            int soNhom = Integer.parseInt(scanner.nextLine());

            if (soNhom != 0) {
                System.out.println("Mỗi nhóm có: " + (100 / soNhom) + " người (tính trên 100 mẫu).");
            } else {
                logError("Arithmetic logic", "Người dùng cố tình chia nhóm cho 0.");
                System.out.println("Cảnh báo: Không thể chia cho 0, bỏ qua thao tác chia nhóm.");
            }

            user.displayName();

        } catch (NumberFormatException e) {
            logError("NumberFormatException", "Định dạng số không hợp lệ: " + e.getMessage());
        } catch (InvalidAgeException e) {
            logError("InvalidAgeException", e.getMessage());
        } finally {
            System.out.println("[" + getTimestamp() + "] [INFO] Giải phóng tài nguyên hệ thống.");
            scanner.close();
        }
    }

    private static void logError(String type, String message) {
        System.err.println("[" + getTimestamp() + "] [ERROR] [" + type + "] " + message);
    }

    private static String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
