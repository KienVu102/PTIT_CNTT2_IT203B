import java.util.*;
import java.util.stream.Collectors;

enum Status3 {
    ACTIVE,
    INACTIVE
}

record User3(String username, String email, Status3 status) {
}

public class Ex_4 {
    public static void main(String[] args) {

        List<User3> users = List.of(
                new User3("alice", "alice@gmail.com", Status3.ACTIVE),
                new User3("bob", "bob@yahoo.com", Status3.INACTIVE),
                new User3("alice", "alice2@gmail.com", Status3.ACTIVE),
                new User3("charlie", "charlie@gmail.com", Status3.ACTIVE),
                new User3("bob", "bob2@yahoo.com", Status3.INACTIVE));

        List<User3> uniqueUsers = users.stream()
                .collect(Collectors.toMap(
                        User3::username,
                        user -> user,
                        (existing, duplicate) -> existing
                ))
                .values()
                .stream()
                .toList();

        uniqueUsers.forEach(System.out::println);
    }
}