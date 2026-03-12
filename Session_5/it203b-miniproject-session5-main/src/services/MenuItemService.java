package services;

import models.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuItemService {
    List<MenuItem> list = new ArrayList<>();

    public void add(MenuItem item) {
        list.add(item);
        System.out.println("Thêm thành công");
    }

    public void delete(MenuItem item) {
        list.remove(item);
        System.out.println("Xóa thành công");
    }

    public void update(MenuItem item) {
        System.out.println("Cập nhật thành công");
    }

    public Optional<MenuItem> findById(String id) {
        for (MenuItem item : list) {
            if (item.getId().equals(id)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Optional<List<MenuItem>> findByName(String name) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : list) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(item);
            }
        }
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    public void displayAll() {
        if (isEmpty()) {
            System.out.printf("Danh sách trống");
            return;
        }
        list.forEach(System.out::println);
    }

    public Optional<List<MenuItem>> findByPriceRange(double minPrice, double maxPrice) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : list) {
            double price = item.getPrice();
            if (price >= minPrice && price <= maxPrice) {
                result.add(item);
            }
        }
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result);
    }
}