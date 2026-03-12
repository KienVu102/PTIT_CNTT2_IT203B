package presentation;

import models.Order;
import services.OrderService;
import utils.Input;

import java.util.ArrayList;
import java.util.List;

public class OrderMenu {

    private OrderService orderService = new OrderService();
    private List<Order> orders = new ArrayList<>();

    public void menu() {
        int choice;
        do {
            System.out.println("\n--- QUẢN LÝ ĐƠN HÀNG ---");
            System.out.println("1. Tạo đơn hàng mới");
            System.out.println("2. Thêm món vào giỏ hàng");
            System.out.println("3. Tính tổng tiền đơn hàng");
            System.out.println("4. Hiển thị tất cả đơn hàng");
            System.out.println("0. Quay lại");

            choice = Input.inputIntegerPositive("Nhập chức năng: ");

            switch (choice) {

                case 1:
                    createOrder();
                    break;

                case 2:
                    System.out.println("Chức năng thêm món do thành viên khác xử lý.");
                    break;

                case 3:
                    calculateTotal();
                    break;

                case 4:
                    displayAllOrders();
                    break;

                case 0:
                    System.out.println("Quay lại menu chính...");
                    break;

                default:
                    System.out.println("Chức năng không hợp lệ!");
            }

        } while (choice != 0);
    }

    // tạo đơn hàng
    private void createOrder() {
        String id = Input.inputString("Nhập ID đơn hàng: ");
        Order order = new Order(id, new java.util.HashMap<>(), java.time.LocalDateTime.now());
        orders.add(order);
        System.out.println("Tạo đơn hàng thành công!");
    }

    // tính tổng tiền
    private void calculateTotal() {

        if (orders.isEmpty()) {
            System.out.println("Chưa có đơn hàng.");
            return;
        }

        for (Order order : orders) {
            double total = orderService.calculateTotal(order);
            System.out.println("Đơn: " + order.getOrderId() + " | Tổng tiền: " + total);
        }
    }

    // hiển thị tất cả đơn
    private void displayAllOrders() {

        if (orders.isEmpty()) {
            System.out.println("Chưa có đơn hàng.");
            return;
        }

        for (Order order : orders) {
            orderService.displayOrder(order);
        }
    }
}