@FunctionalInterface
interface PasswordValidator {
    boolean validate(String password);
}

public class Ex_2 {

    public static void main(String[] args) {

        PasswordValidator validator = password -> password.length() >= 8;

        System.out.println("12345678 -> " + validator.validate("12345678"));
        System.out.println("1234 -> " + validator.validate("1234"));
    }
}