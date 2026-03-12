
package presentation;

import models.Order;
import services.MenuItemService;
import services.StatisticsService;
import utils.Input;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final FoodMenu foodMenu = new FoodMenu();
    private static final OrderMenu orderMenu = new OrderMenu();

    private static final List<Order> orderList = new ArrayList<>();

    private static final StatisticsService statisticsService =
            new StatisticsService(orderList);

    private static final StatisticsMenu statisticsMenu =
            new StatisticsMenu(statisticsService, new MenuItemService());

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== MENU QUẢN LÝ CỬA HÀNG =====");
            System.out.println("1. Quản lý món ăn");
            System.out.println("2. Quản đơn hàng");
            System.out.println("3. Thống kê");
            System.out.println("0. Thoát");

            choice = Input.inputIntegerPositive("Chọn chức năng: ");

            switch (choice) {
                case 1:
                    foodMenu.menu();
                    break;
                case 2:
                    orderMenu.menu();
                    break;
                case 3:
                    statisticsMenu.menu();
                    break;
            }

        } while (choice != 0);
    }
}
