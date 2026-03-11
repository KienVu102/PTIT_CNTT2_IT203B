import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

enum Role {
    ADMIN, MODERATOR, USER
}

enum Action {
    DELETE_USER, LOCK_USER, VIEW_PROFILE
}

class User {
    private String name;
    private Role role;

    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public Role getRole() { return role; }
}

public class AuthorizationService {
    public boolean canPerformAction(User user, Action action) {
        if (user == null || action == null) return false;

        return switch (user.getRole()) {
            case ADMIN -> true;
            case MODERATOR -> action == Action.LOCK_USER || action == Action.VIEW_PROFILE;
            case USER -> action == Action.VIEW_PROFILE;
        };
    }
}

class AuthorizationServiceTest {

    private AuthorizationService authService;
    private User testUser;

    @BeforeEach
    void setUp() {
        authService = new AuthorizationService();
    }

    @AfterEach
    void tearDown() {
        authService = null;
        testUser = null;
    }

    @Nested
    @DisplayName("Kiểm thử quyền của ADMIN")
    class AdminTests {
        @Test
        void admin_ShouldHaveAllPermissions() {
            testUser = new User("AdminKien", Role.ADMIN);
            assertAll("Quyền của ADMIN",
                    () -> assertTrue(authService.canPerformAction(testUser, Action.DELETE_USER), "ADMIN phải xóa được user"),
                    () -> assertTrue(authService.canPerformAction(testUser, Action.LOCK_USER), "ADMIN phải khóa được user"),
                    () -> assertTrue(authService.canPerformAction(testUser, Action.VIEW_PROFILE), "ADMIN phải xem được profile")
            );
        }
    }

    @Nested
    @DisplayName("Kiểm thử quyền của MODERATOR")
    class ModeratorTests {
        @Test
        void moderator_ShouldHaveLimitedPermissions() {
            testUser = new User("ModKien", Role.MODERATOR);
            assertAll("Quyền của MODERATOR",
                    () -> assertFalse(authService.canPerformAction(testUser, Action.DELETE_USER), "MOD không được xóa user"),
                    () -> assertTrue(authService.canPerformAction(testUser, Action.LOCK_USER), "MOD phải khóa được user"),
                    () -> assertTrue(authService.canPerformAction(testUser, Action.VIEW_PROFILE), "MOD phải xem được profile")
            );
        }
    }

    @Nested
    @DisplayName("Kiểm thử quyền của USER")
    class UserTests {
        @Test
        void user_ShouldOnlyViewProfile() {
            testUser = new User("UserKien", Role.USER);
            assertAll("Quyền của USER",
                    () -> assertFalse(authService.canPerformAction(testUser, Action.DELETE_USER), "USER không được xóa user"),
                    () -> assertFalse(authService.canPerformAction(testUser, Action.LOCK_USER), "USER không được khóa user"),
                    () -> assertTrue(authService.canPerformAction(testUser, Action.VIEW_PROFILE), "USER phải xem được profile")
            );
        }
    }
}