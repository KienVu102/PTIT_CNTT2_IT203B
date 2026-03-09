@FunctionalInterface
interface Authenticatable {

    // 1. Phương thức trừu tượng
    String getPassword();

    // 2. Phương thức mặc định: kiểm tra mật khẩu không rỗng
    default boolean isAuthenticated() {
        return getPassword() != null && !getPassword().isEmpty();
    }

    // 3. Phương thức static: mô phỏng mã hóa mật khẩu
    static String encrypt(String rawPassword) {
        return rawPassword == null ? null : "ENC_" + rawPassword;
    }
}

class User1 implements Authenticatable {
    private String password;

    public User1(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }
}

public class Ex_3 {

    public static void main(String[] args) {

        User1 user1 = new User1(Authenticatable.encrypt("123456"));
        User1 user2 = new User1("");

        System.out.println("User1 authenticated: " + user1.isAuthenticated());
        System.out.println("User2 authenticated: " + user2.isAuthenticated());
    }
}