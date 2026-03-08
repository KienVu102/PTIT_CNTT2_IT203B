package Ex_6;

public class User {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Tuổi " + age + " nằm ngoài phạm vi cho phép (0-150).");
        }
        this.age = age;
    }

    public void displayName() {
        if (this.name != null) {
            System.out.println("Tên người dùng: " + this.name.toUpperCase());
        } else {
            System.out.println("Tên người dùng chưa được thiết lập.");
        }
    }

    public int getAge() {
        return age;
    }
}
