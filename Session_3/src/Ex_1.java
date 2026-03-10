import java.util.List;

record User(String username, String gmail, Status status) {}

enum Status {
    ACTIVE, INACTIVE
}

public class Ex_1 {
    public static void main(String[] args){
        List<User> users = List.of(
                new User("alice", "alice@gmail.com", Status.ACTIVE),
                new User("bob ", "bob@gmail.com", Status.INACTIVE),
                new User("charlie ", "charlie@gmail.com", Status.ACTIVE)
                );

        System.out.println("Danh sách người dùng: ");
        users.forEach(user -> System.out.println(user));
    }
}
