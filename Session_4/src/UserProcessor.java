//Ex 3
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserProcessor {
    public String processEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email không được null");
        }

        int atIndex = email.indexOf('@');
        if (atIndex == -1 || atIndex == email.length() - 1) {
            throw new IllegalArgumentException("Email không đúng định dạng");
        }

        return email.toLowerCase();
    }
}

class UserProcessorTest {

    private UserProcessor userProcessor;

    @BeforeEach
    void setUp() {
        userProcessor = new UserProcessor();
    }

    @Test
    void shouldReturnLowercaseEmail_WhenInputIsValid() {
        String input = "user@gmail.com";
        String result = userProcessor.processEmail(input);

        assertEquals("user@gmail.com", result);
    }

    @Test
    void shouldThrowException_WhenEmailIsMissingAtSymbol() {
        String input = "usergmail.com";

        assertThrows(IllegalArgumentException.class, () -> {
            userProcessor.processEmail(input);
        });
    }

    @Test
    void shouldThrowException_WhenEmailIsMissingDomain() {
        String input = "user@";

        assertThrows(IllegalArgumentException.class, () -> {
            userProcessor.processEmail(input);
        });
    }

    @Test
    void shouldConvertEmailToLowercase_WhenInputHasUppercase() {
        String input = "Example@Gmail.com";
        String result = userProcessor.processEmail(input);

        assertEquals("example@gmail.com", result);
    }
}