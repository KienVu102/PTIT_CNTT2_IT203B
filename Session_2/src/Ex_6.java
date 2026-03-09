@FunctionalInterface
interface UserProcessor {
    String process(User3 u);
}

class User3 {
    private String username;

    public User3(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

class UserUtils {
    public static String convertToUpperCase(User3 u) {
        return u.getUsername().toUpperCase();
    }
}

public class Ex_6 {

    public static void main(String[] args) {

        User3 user = new User3("ptit_student");

        UserProcessor processor = UserUtils::convertToUpperCase;

        String result = processor.process(user);
        System.out.println(result);
    }
}