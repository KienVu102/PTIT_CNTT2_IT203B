package Main;

import Entity.Product;
import Entity.PhysicalProduct;
import Entity.DigitalProduct;
import ValidateData.ProductDatabase;
import ProductFactory.ProductFactory;
import java.util.Scanner;

public class Main {
    private static ProductDatabase database = ProductDatabase.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getIntInput("Lựa chọn của bạn");
            
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    System.out.println("Thoát chương trình");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 1-5.");
            }
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n---------------------- QUẢN LÝ SẢN PHẨM ----------------------");
        System.out.println("1. Thêm mới sản phẩm");
        System.out.println("2. Xem danh sách sản phẩm");
        System.out.println("3. Cập nhật thông tin sản phẩm");
        System.out.println("4. Xoá sản phẩm");
        System.out.println("5. Thoát");
        System.out.println("-----------------------------------------------------------------");
    }
    
    private static void addProduct() {
        try {
            System.out.println("\n--- Thêm mới sản phẩm ---");
            System.out.println("Chọn loại sản phẩm:");
            System.out.println("1. Physical Product");
            System.out.println("2. Digital Product");
            
            int type = getIntInput("Loại sản phẩm");
            
            String id = getStringInput("ID sản phẩm");
            String name = getStringInput("Tên sản phẩm");
            double price = getDoubleInput("Giá sản phẩm");
            
            Product product;
            if (type == 1) {
                double weight = getDoubleInput("Trọng lượng");
                product = ProductFactory.createPhysicalProduct(id, name, price, weight);
            } else if (type == 2) {
                double size = getDoubleInput("Dung lượng");
                product = ProductFactory.createDigitalProduct(id, name, price, size);
            } else {
                System.out.println("Loại sản phẩm không hợp lệ!");
                return;
            }
            
            database.addProduct(product);
            
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
        }
    }
    
    private static void viewProducts() {
        System.out.println("\n--- Danh sách sản phẩm ---");
        database.displayAllProducts();
    }
    
    private static void deleteProduct() {
        System.out.println("\n--- Xoá sản phẩm ---");
        String id = getStringInput("ID sản phẩm cần xoá");
        
        Product product = database.getProductById(id);
        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm với ID: " + id);
            return;
        }
        
        System.out.println("Thông tin sản phẩm sẽ bị xoá:");
        product.displayInfo();
        
        String confirm = getStringInput("Bạn có chắc muốn xoá? (y/n)");
        if (confirm.equalsIgnoreCase("y")) {
            database.deleteProduct(id);
        } else {
            System.out.println("Đã hủy thao tác xoá.");
        }
    }
    
    private static String getStringInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
            }
        }
    }
    
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số thực hợp lệ.");
            }
        }
    }
}
