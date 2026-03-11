//Ex 4
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidator {
    public String evaluatePasswordStrength(String password) {
        if (password == null || password.length() < 8) {
            return "Yếu";
        }

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        if (hasUpper && hasLower && hasDigit && hasSpecial) {
            return "Mạnh";
        }

        if ((hasLower && !hasUpper && !hasDigit && !hasSpecial) ||
                (hasUpper && hasDigit && !hasLower && !hasSpecial)) {
            return "Yếu";
        }

        return "Trung bình";
    }
}

class PasswordValidatorTest {

    private final PasswordValidator validator = new PasswordValidator();

    @Test
    @DisplayName("Kiểm tra các kịch bản mật khẩu Mạnh và Trung bình")
    void testPasswordStrength_StrongAndMedium() {
        assertAll("Đánh giá độ mạnh mật khẩu",
                () -> assertEquals("Mạnh", validator.evaluatePasswordStrength("Abc123!@"), "TC01: Phải là Mạnh"),
                () -> assertEquals("Trung bình", validator.evaluatePasswordStrength("abc123!@"), "TC02: Thiếu chữ hoa"),
                () -> assertEquals("Trung bình", validator.evaluatePasswordStrength("ABC123!@"), "TC03: Thiếu chữ thường"),
                () -> assertEquals("Trung bình", validator.evaluatePasswordStrength("Abcdef!@"), "TC04: Thiếu số"),
                () -> assertEquals("Trung bình", validator.evaluatePasswordStrength("Abc12345"), "TC05: Thiếu ký tự đặc biệt")
        );
    }

    @Test
    @DisplayName("Kiểm tra các kịch bản mật khẩu Yếu")
    void testPasswordStrength_Weak() {
        assertAll("Đánh giá mật khẩu yếu",
                () -> assertEquals("Yếu", validator.evaluatePasswordStrength("Ab1!"), "TC06: Quá ngắn"),
                () -> assertEquals("Yếu", validator.evaluatePasswordStrength("password"), "TC07: Chỉ có chữ thường"),
                () -> assertEquals("Yếu", validator.evaluatePasswordStrength("ABC12345"), "TC08: Chỉ có chữ hoa và số")
        );
    }
}