import java.util.List;

enum Status1 {
    ACTIVE,
    INACTIVE
}

record User1(String username, String email, Status1 status) {
}

public class Ex_2 {
    public static void main(String[] args) {

        List<User1> users = List.of(
                new User1("alice", "alice@gmail.com", Status1.ACTIVE),
                new User1("bob", "bob@yahoo.com", Status1.INACTIVE),
                new User1("charlie", "charlie@gmail.com", Status1.ACTIVE));

        // Lọc user dùng gmail và in username
        users.stream()
                .filter(User1 -> User1.email().endsWith("@gmail.com"))
                .map(User1::username)
                .forEach(System.out::println);
    }
}