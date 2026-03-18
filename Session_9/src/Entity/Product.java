package Entity;

public abstract class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return this.price;
    }

    public abstract void displayInfo();

    public String toString(){
        return "Product [id=" + id + ", name=" + name + ", price=" + price +"]";
    }
}
