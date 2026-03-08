package Ex_5;

public class User {
    private int age;

    public void setAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Lỗi nghiệp vụ: Tuổi " + age + " không hợp lệ (phải từ 0-150)!");
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
