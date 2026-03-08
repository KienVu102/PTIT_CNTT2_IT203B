import java.util.Scanner;
import java.time.Year;

public class Ex_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Vui lòng nhập năm sinh của bạn: ");
            String input = scanner.nextLine();

            int namSinh = Integer.parseInt(input);

            int namHienTai = Year.now().getValue();
            int tuoi = namHienTai - namSinh;

            if (tuoi < 0 || tuoi > 120) {
                System.out.println("Năm sinh bạn nhập không hợp lý.");
            } else {
                System.out.println("Đăng ký thành công! Tuổi của bạn là: " + tuoi);
            }

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng chỉ nhập con số (Ví dụ: 2000), không nhập chữ hay ký tự đặc biệt.");
        } catch (Exception e) {
            System.out.println("Đã có lỗi hệ thống xảy ra: " + e.getMessage());
        } finally {
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
            scanner.close();
        }
    }
}