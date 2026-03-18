package ProductFactory;

import Entity.Product;
import Entity.PhysicalProduct;
import Entity.DigitalProduct;

public class ProductFactory {
    
    public static Product createProduct(int productType, String id, String name, double price, double attribute) {
        switch (productType) {
            case 1:
                return new PhysicalProduct(id, name, price, attribute);
            case 2:
                return new DigitalProduct(id, name, price, attribute);
            default:
                throw new IllegalArgumentException("Sản phẩm không hợp lệ");
        }
    }
    
    public static Product createPhysicalProduct(String id, String name, double price, double weight) {
        return new PhysicalProduct(id, name, price, weight);
    }
    
    public static Product createDigitalProduct(String id, String name, double price, double size) {
        return new DigitalProduct(id, name, price, size);
    }
}
