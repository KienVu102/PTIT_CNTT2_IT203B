import java.util.*;
import java.util.function.*;

class User2 {
    private String username;

    public User2() {
        this.username = "default";
    }

    public User2(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

public class Ex_4 {

    public static void main(String[] args) {

        // Danh sách users tự định nghĩa
        List<User2> users = Arrays.asList(
                new User2("admin"),
                new User2("user1"),
                new User2("user2"));

        // 1. (user) -> user.getUsername()
        // Tham chiếu instance method của đối tượng bất kỳ
        Function<User2, String> getUsername = User2::getUsername;

        // 2. (s) -> System.out.println(s)
        // Tham chiếu instance method của một đối tượng cụ thể
        Consumer<String> print = System.out::println;

        // 3. () -> new User()
        // Tham chiếu Constructor
        Supplier<User2> userSupplier = User2::new;

        // Áp dụng Method Reference
        users.stream()
                .map(getUsername)
                .forEach(print);

        User2 newUser = userSupplier.get();
        System.out.println("New user created: " + newUser.getUsername());
    }
}