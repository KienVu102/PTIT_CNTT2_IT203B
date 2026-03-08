import java.util.Scanner;

public class Example {
    private static Scanner sc = new Scanner(System.in);

    public static int inputInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
            }
        }
    }

    public static double inputDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số thực (dùng dấu chấm cho phần thập phân)!");
            }
        }
    }

    public static String inputString(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Lỗi: Không được để trống!");
        }
    }

    public static char inputChar(String message) {
        while (true) {
            String input = inputString(message);
            if (input.length() == 1) {
                return input.charAt(0);
            }
            System.out.println("Lỗi: Vui lòng chỉ nhập duy nhất một ký tự!");
        }
    }

    public static boolean inputBoolean(String message) {
        while (true) {
            System.out.print(message + " (true/false): ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Lỗi: Chỉ chấp nhận giá trị 'true' hoặc 'false'!");
        }
    }

    public static void main(String[] args) {
        int tuoi = inputInt("Nhập tuổi của bạn: ");
        double diem = inputDouble("Nhập điểm trung bình: ");
        String ten = inputString("Nhập họ tên: ");
        boolean ketQua = inputBoolean("Bạn có đi học đầy đủ không?");

        System.out.println("Tên: " + ten + " | Tuổi: " + tuoi + " | Điểm: " + diem + " | Chuyên cần: " + ketQua);
    }
}