package models;

abstract public class MenuItem {
    private String id;
    private String name;
    private double price;
    private int stock;

    public MenuItem(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public abstract double calculatePrice();

    @Override
    public String toString() {
        return "ID: " + id + " | Tên: " + name + " | Giá: " + price + " | Số lượng: " + stock;
    }
}
