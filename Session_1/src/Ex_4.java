import java.io.IOException;

public class Ex_4 {

    public static void main(String[] args) {
        try {
            processUserData();
        } catch (IOException e) {
            System.out.println("Chốt chặn cuối cùng (Main): Đã xử lý lỗi ghi file - " + e.getMessage());
        } finally {
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }

        System.out.println(">>> Hệ thống vẫn an toàn và tiếp tục vận hành.");
    }

    public static void processUserData() throws IOException {
        saveToFile();
    }

    public static void saveToFile() throws IOException {
        throw new IOException("Lỗi kết nối ổ đĩa hoặc file không tồn tại!");
    }
}