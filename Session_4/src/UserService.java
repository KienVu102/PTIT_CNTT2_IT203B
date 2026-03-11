//Ex 2
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserService {
    public boolean checkRegistrationAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Tuổi không được là số âm");
        }
        return age >= 18;
    }
}
class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    void testCheckRegistrationAge_ValidBoundary() {
        boolean result = userService.checkRegistrationAge(18);
        assertEquals(true, result);
    }

    @Test
    void testCheckRegistrationAge_BelowRequired() {
        boolean result = userService.checkRegistrationAge(17);
        assertEquals(false, result);
    }

    @Test
    void testCheckRegistrationAge_NegativeAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.checkRegistrationAge(-1);
        });
    }
}