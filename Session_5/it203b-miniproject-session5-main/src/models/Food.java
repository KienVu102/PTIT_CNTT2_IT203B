package models;

public class Food extends MenuItem {
    private String type;

    public Food(String id, String name, double price, int stock, String type) {
        super(id, name, price, stock);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public double calculatePrice() {
        switch (type.toLowerCase()) {
            case "fastfood":
                return getPrice(); // giữ nguyên
            case "healthy":
                return getPrice() * 1.10; // cộng thêm 10%
            case "vegetarian":
                return getPrice() * 0.95; // giảm 5%
            default:
                return getPrice(); // mặc định
        }
    }
}
