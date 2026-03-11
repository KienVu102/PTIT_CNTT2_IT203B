import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

enum Role1 { ADMIN, MODERATOR, USER }
enum Action1 { DELETE_USER, LOCK_USER, VIEW_PROFILE }

class UserSystem {

    public boolean isValidUsername(String username) {
        if (username == null) return false;
        return username.length() >= 6 && username.length() <= 20 && !username.contains(" ");
    }

    public String processEmail(String email) {
        if (email == null || !email.contains("@") || email.endsWith("@")) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
        return email.toLowerCase();
    }

    public boolean checkRegistrationAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Tuổi không được âm");
        return age >= 18;
    }

    public boolean canPerformAction(Role1 role, Action1 action) {
        return switch (role) {
            case ADMIN -> true;
            case MODERATOR -> action != Action1.DELETE_USER;
            case USER -> action == Action1.VIEW_PROFILE;
        };
    }

    public String evaluatePasswordStrength(String pw) {
        if (pw == null || pw.length() < 8) return "Yếu";
        boolean hasUpper = pw.matches(".*[A-Z].*");
        boolean hasLower = pw.matches(".*[a-z].*");
        boolean hasDigit = pw.matches(".*\\d.*");
        boolean hasSpecial = pw.matches(".*[!@#$%^&*].*");

        if (hasUpper && hasLower && hasDigit && hasSpecial) return "Mạnh";
        if (pw.length() >= 8 && (hasLower || hasUpper)) return "Trung bình";
        return "Yếu";
    }

    public boolean updateProfile(String newEmail, LocalDate birthDate, List<String> existingEmails) {
        if (birthDate.isAfter(LocalDate.now())) return false;
        if (existingEmails.contains(newEmail)) return false;
        return true;
    }
}



@DisplayName("Hệ Thống Kiểm Thử Nghiệp Vụ Tổng Hợp - Vũ Đình Kiên")
class ComprehensiveSystemTest {

    private UserSystem system;

    @BeforeEach
    void setUp() {
        system = new UserSystem();
    }

    @Nested
    @DisplayName("Nhóm 1: Xác thực tài khoản & Email")
    class AuthenticationTests {
        @Test
        void testAccountValidation() {
            assertAll("Xác thực đầu vào",
                    () -> assertTrue(system.isValidUsername("user123"), "Username hợp lệ"),
                    () -> assertFalse(system.isValidUsername("abc"), "Username quá ngắn"),
                    () -> assertEquals("example@gmail.com", system.processEmail("Example@Gmail.com"), "Phải lowercase email"),
                    () -> assertThrows(IllegalArgumentException.class, () -> system.processEmail("user@"), "Thiếu domain")
            );
        }
    }

    @Nested
    @DisplayName("Nhóm 2: Phân quyền & Độ tuổi")
    class AuthorizationTests {
        @Test
        void testPermissions() {
            assertAll("Ma trận quyền hạn",
                    () -> assertTrue(system.checkRegistrationAge(18), "Đủ 18 tuổi"),
                    () -> assertThrows(IllegalArgumentException.class, () -> system.checkRegistrationAge(-1)),
                    () -> assertTrue(system.canPerformAction(Role1.ADMIN, Action1.DELETE_USER), "Admin có mọi quyền"),
                    () -> assertFalse(system.canPerformAction(Role1.MODERATOR, Action1.DELETE_USER), "Mod không được xóa"),
                    () -> assertTrue(system.canPerformAction(Role1.USER, Action1.VIEW_PROFILE), "User chỉ được xem")
            );
        }
    }

    @Nested
    @DisplayName("Nhóm 3: Bảo mật mật khẩu")
    class SecurityTests {
        @Test
        void testPasswordStrength() {
            assertAll("Đánh giá mật khẩu",
                    () -> assertEquals("Mạnh", system.evaluatePasswordStrength("Abc123!@")),
                    () -> assertEquals("Trung bình", system.evaluatePasswordStrength("Abc12345")),
                    () -> assertEquals("Yếu", system.evaluatePasswordStrength("123"))
            );
        }
    }

    @Nested
    @DisplayName("Nhóm 4: Cập nhật hồ sơ (Coverage 100%)")
    class ProfileTests {
        @Test
        void testUpdateProfile() {
            List<String> dbEmails = Arrays.asList("admin@test.com", "user@test.com");

            assertAll("Ràng buộc cập nhật hồ sơ",
                    () -> assertTrue(system.updateProfile("new@test.com", LocalDate.of(2000, 1, 1), dbEmails), "Cập nhật hợp lệ"),
                    () -> assertFalse(system.updateProfile("new@test.com", LocalDate.now().plusDays(1), dbEmails), "Ngày sinh tương lai"),
                    () -> assertFalse(system.updateProfile("admin@test.com", LocalDate.of(2000, 1, 1), dbEmails), "Email đã tồn tại")
            );
        }
    }

    @AfterEach
    void tearDown() {
        system = null;
    }
}