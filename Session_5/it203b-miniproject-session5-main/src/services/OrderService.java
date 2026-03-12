package services;

import models.MenuItem;
import models.Order;

import java.time.LocalDateTime;
import java.util.*;

public class OrderService {
    private final List<Order> orders = new ArrayList<>();
    private Order currentOrder = null;

    // Tính tổng tiền đơn hàng
    public double calculateTotal(Order order) {

        if (order == null || order.getItems().isEmpty()) {
            return 0;
        }

        return order.getItems()
                .entrySet()
                .stream()
                .mapToDouble(entry ->
                        entry.getKey().calculatePrice() * entry.getValue())
                .sum();
    }

    public void displayOrder(Order order) {

        if (order == null) {
            System.out.println("Không tìm thấy đơn hàng.");
            return;
        }

        System.out.println("\n===== ORDER " + order.getOrderId() + " =====");

        if (order.getItems().isEmpty()) {
            System.out.println("Đơn hàng chưa có món.");
            return;
        }

        for (Map.Entry<MenuItem, Integer> entry : order.getItems().entrySet()) {

            MenuItem item = entry.getKey();
            int quantity = entry.getValue();

            double itemTotal = item.calculatePrice() * quantity;

            System.out.printf("%-20s x%d : %.0f\n",
                    item.getName(),
                    quantity,
                    itemTotal);
        }

        System.out.println("-----------------------------");

        double total = calculateTotal(order);

        System.out.println("TOTAL: " + total);
        System.out.println("ORDER DATE: " + order.getOrderDate());
    }


    public void createOrder() {
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        currentOrder = new Order(orderId, new HashMap<>(), LocalDateTime.now());
        orders.add(currentOrder);
        System.out.println("Da tao don hang moi: " + orderId);
    }

    public void addItemToCurrentOrder(MenuItem item, int quantity) {
        if (currentOrder == null) {
            System.out.println("Loi: Can tao don hang truoc!");
            return;
        }

        if (item.getStock() < quantity) {
            System.out.println("Loi: Khong du hang trong kho!");
            return;
        }

        int currentQty = currentOrder.getItems().getOrDefault(item, 0);
        currentOrder.getItems().put(item, currentQty + quantity);
        item.setStock(item.getStock() - quantity);

        System.out.println("Da them: " + item.getName() + " (SL: " + quantity + ")");
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public List<Order> getAllOrders() {
        return orders;
    }
}