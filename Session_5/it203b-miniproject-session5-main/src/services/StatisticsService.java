package services;

import models.Order;

import java.time.LocalDate;
import java.util.List;

public class StatisticsService {

    private List<Order> orderList;

    public StatisticsService(List<Order> orderList) {
        this.orderList = orderList;
    }

    public double getRevenueByDate(LocalDate date) {
        return orderList.stream()
                .filter(o -> o.getStatus().equals("PAID"))
                .filter(o -> o.getOrderDate().toLocalDate().equals(date))
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    public double getRevenueByMonth(int month, int year) {
        return orderList.stream()
                .filter(o -> o.getStatus().equals("PAID"))
                .filter(o -> o.getOrderDate().getMonthValue() == month
                        && o.getOrderDate().getYear() == year)
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }
}