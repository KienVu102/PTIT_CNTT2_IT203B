import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;

class User {
    private String username;
    private String role;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', role='" + role + "'}";
    }
}

public class Ex_1 {

    public static void main(String[] args) {

        User user = new User("admin01", "ADMIN");

        Predicate<User> isAdmin = u -> u.getRole().equals("ADMIN");
        System.out.println("Is Admin: " + isAdmin.test(user));

        Function<User, String> getUsername = u -> u.getUsername();
        System.out.println("Username: " + getUsername.apply(user));

        Consumer<User> printUser = u -> System.out.println("User info: " + u);
        printUser.accept(user);

        Supplier<User> defaultUser = () -> new User("guest", "USER");
        User newUser = defaultUser.get();
        System.out.println("Default User: " + newUser);
    }
}
