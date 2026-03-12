package models;

public class Drink extends MenuItem {
    private String size;

    public Drink(String id, String name, double price, int stock, String size) {
        super(id, name, price, stock);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public double calculatePrice() {
        switch (size.toLowerCase()) {
            case "l":
                return getPrice() + 10000;
            case "xl":
                return getPrice() + 20000;
            default:
                return getPrice();
        }
    }
}