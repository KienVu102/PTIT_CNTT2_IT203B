package models;

import java.time.LocalDateTime;
import java.util.Map;

public class Order {

    private String orderId;
    private Map<MenuItem, Integer> items;
    private LocalDateTime orderDate;
    private double totalPrice;
    private String status;

    public Order(String orderId, Map<MenuItem, Integer> items, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.items = items;
        this.orderDate = orderDate;
        this.status = "PENDING";
        calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        totalPrice = items.entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public String getOrderId() {
        return orderId;
    }

    public Map<MenuItem, Integer> getItems() {
        return items;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}