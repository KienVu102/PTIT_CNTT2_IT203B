import java.util.List;
import java.util.Optional;

enum Status2 {
    ACTIVE,
    INACTIVE
}

record User2(String username, String email, Status2 status) {
}

// Repository tìm kiếm User
class UserRepository {

    private static final List<User2> users = List.of(
            new User2("alice", "alice@gmail.com", Status2.ACTIVE),
            new User2("bob", "bob@yahoo.com", Status2.INACTIVE),
            new User2("charlie", "charlie@gmail.com", Status2.ACTIVE));

    public static Optional<User2> findUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.username().equals(username))
                .findFirst();
    }
}

public class Ex_3 {
    public static void main(String[] args) {

        Optional<User2> result = UserRepository.findUserByUsername("alice");

        if (result.isPresent()) {
            System.out.println("Welcome " + result.get().username());
        } else {
            System.out.println("Guest login");
        }

        // Cách 2: Viết gọn 
        result.ifPresentOrElse(
                user -> System.out.println("Welcome " + user.username()),
                () -> System.out.println("Guest login"));
    }
}