package presentation;

import models.Drink;
import models.Food;
import models.MenuItem;
import services.MenuItemService;
import utils.Input;

import java.util.Optional;

public class FoodMenu {
    private MenuItemService menuItemService = new MenuItemService();

    void add() {
        int choice;
        while (true) {
            System.out.println("1. Dồ ăn");
            System.out.println("2. Đồ uống");
            choice = Input.inputIntegerPositive("Chọn chức năng: ");
            if (choice != 1 && choice != 2) {
                System.out.println("Chức năng không hợp lệ");
            } else {
                break;
            }
        }
        String id = String.valueOf((int) (Math.random() * 1000));
        String name = Input.inputString("Nhập tên: ");
        double price = Input.inputDoublePositive("Nhập giá sản phẩm: ");
        int stock = Input.inputIntegerPositive("Nhập số lượng sản phẩm: ");
        switch (choice) {
            case 1:
                String type = Input.inputString("Nhập loại đồ ăn: ");
                Food food = new Food(id, name, price, stock, type);
                menuItemService.add(food);
                break;
            case 2:
                String size = Input.inputString("Nhập size đồ uống: ");
                Drink drink = new Drink(id, name, price, stock, size);
                menuItemService.add(drink);
                break;
        }
    }

    public void edit() {
        String id = Input.inputString("Nhập ID món ăn/đồ uống muốn sửa: ");
        Optional<MenuItem> optionalItem = menuItemService.findById(id);

        if (!optionalItem.isPresent()) {
            System.out.println("Không tìm thấy");
            return;
        }

        MenuItem item = optionalItem.get();

        String newName = Input.inputString("Nhập tên mới: ");
        item.setName(newName);

        double newPrice = Input.inputDoublePositive("Nhập giá mới: ");
        item.setPrice(newPrice);

        int newStock = Input.inputIntegerPositive("Nhập số lượng mới: ");
        item.setStock(newStock);

        // sửa thuộc tính riêng
        if (item instanceof Food) {
            Food food = (Food) item;
            String type = Input.inputString("Nhập loại đồ ăn mới: ");
            food.setType(type);
        } else if (item instanceof Drink) {
            Drink drink = (Drink) item;
            String size = Input.inputString("Nhập size đồ uống mới: ");
            drink.setSize(size);
        }

        menuItemService.update(item);
    }

    public void delete() {
        String id = Input.inputString("Nhập ID món ăn/đồ uống muốn xóa: ");
        Optional<MenuItem> item = menuItemService.findById(id);
        if (!item.isPresent()) {
            System.out.println("Không tìm thấy");
            return;
        }
        menuItemService.delete(item.get());
    }

    public void findByName() {
        String name = Input.inputString("Nhập tên đồ ăn hoặc đồ uống: ");
        menuItemService.findByName(name).ifPresentOrElse(
                list -> {
                    list.forEach(System.out::println);
                },
                () -> {
                    System.out.println("Không có sản phẩm nào");
                }
        );
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n--- QUẢN LÝ MÓN ĂN ---");
            System.out.println("1. Thêm món ăn/đồ uống");
            System.out.println("2. Sửa món ăn/đồ uống");
            System.out.println("3. Xóa món ăn/đồ uống");
            System.out.println("4. Tìm món theo tên");
            System.out.println("5. Hiển thị toàn bộ thực đơn");
            System.out.println("0. Quay lại");
            choice = Input.inputIntegerPositive("Chọn chức năng: ");
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    edit();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    findByName();
                    break;
                case 5:
                    menuItemService.displayAll();
                    break;
                default:
                    break;
            }
        } while (choice != 0);
    }
}