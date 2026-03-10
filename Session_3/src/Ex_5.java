import java.util.Comparator;
import java.util.List;

enum Status4 {
    ACTIVE,
    INACTIVE
}

record User4(String username, String email, Status4 status) {
}

public class Ex_5 {
    public static void main(String[] args) {

        List<User4> users = List.of(
                new User4("alexander", "alex@gmail.com", Status4.ACTIVE),
                new User4("charlotte", "charlotte@gmail.com", Status4.ACTIVE),
                new User4("benjamin", "ben@gmail.com", Status4.INACTIVE),
                new User4("bob", "bob@gmail.com", Status4.INACTIVE),
                new User4("alice", "alice@gmail.com", Status4.ACTIVE));

        users.stream()
                .sorted(Comparator.comparingInt(
                        user -> ((User4) user).username().length()).reversed())
                .limit(3)
                .map(User4::username)
                .forEach(System.out::println);
    }
}