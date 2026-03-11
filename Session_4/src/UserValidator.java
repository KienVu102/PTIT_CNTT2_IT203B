//Ex 1
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserValidator {
    public boolean isValidUsername(String username) {
        if (username == null) {
            return false;
        }
        return username.length() >= 6 &&
                username.length() <= 20 &&
                !username.contains(" ");
    }
}

class UserValidatorTest {

    private final UserValidator validator = new UserValidator();

    @Test
    void TC01_ValidUsername() {
        String username = "user123";

        boolean result = validator.isValidUsername(username);

        assertTrue(result, "Username hợp lệ phải trả về true");
    }

    @Test
    void TC02_UsernameTooShort() {
        String username = "abc";

        boolean result = validator.isValidUsername(username);

        assertFalse(result, "Username quá ngắn phải trả về false");
    }

    @Test
    void TC03_UsernameContainsSpace() {
        String username = "user name";

        boolean result = validator.isValidUsername(username);

        assertFalse(result, "Username chứa khoảng trắng phải trả về false");
    }
}