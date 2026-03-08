import java.util.Scanner;
import java.time.Year;

public class Ex_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Nhập năm sinh: ");
            int namSinh = Integer.parseInt(scanner.nextLine());
            int tuoi = Year.now().getValue() - namSinh;
            System.out.println("Tuổi của bạn: " + tuoi);

            System.out.print("Nhập tổng số người dùng: ");
            int tongSoNguoi = Integer.parseInt(scanner.nextLine());

            System.out.print("Nhập số lượng nhóm: ");
            int soNhom = Integer.parseInt(scanner.nextLine());

            int soNguoiMoiNhom = tongSoNguoi / soNhom;
            System.out.println("Mỗi nhóm có: " + soNguoiMoiNhom + " người.");

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng chỉ nhập số nguyên!");
        } catch (ArithmeticException e) {
            System.out.println("Lỗi: Không thể chia cho 0!");
        } finally {
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
            scanner.close();
        }

        System.out.println("Hệ thống vẫn tiếp tục chạy.");
    }
}