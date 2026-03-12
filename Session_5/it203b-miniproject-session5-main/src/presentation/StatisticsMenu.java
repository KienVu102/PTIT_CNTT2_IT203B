package presentation;

import services.MenuItemService;
import services.StatisticsService;
import utils.Input;

import java.time.LocalDate;

public class StatisticsMenu {

    private StatisticsService statisticsService;
    private MenuItemService menuItemService;

    public StatisticsMenu(StatisticsService statisticsService, MenuItemService menuItemService) {
        this.statisticsService = statisticsService;
        this.menuItemService = menuItemService;
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n--- THỐNG KÊ/ TÌM KIẾM ---");
            System.out.println("1. Tìm món theo tên");
            System.out.println("2. Tìm món theo khoảng giá");
            System.out.println("3. Doanh thu theo ngày");
            System.out.println("4. Doanh thu theo tháng");
            System.out.println("5. Liệt kê món bán chạy nhất");
            System.out.println("0. Quay lại");

            choice = Input.inputIntegerPositive("Nhập chức năng: ");

            switch (choice) {
                case 1:
                    String name = Input.inputString("Nhập tên món: ");
                    menuItemService.findByName(name).ifPresentOrElse(list -> list.forEach(System.out::println), () -> System.out.println("Không tìm thấy món phù hợp"));
                    break;

                case 2:
                    double minPrice = Input.inputDoublePositive("Nhập giá tối thiểu: ");
                    double maxPrice = Input.inputDoublePositive("Nhập giá tối đa: ");
                    if (maxPrice < minPrice) {
                        System.out.println("Giá tối đa phải lớn hơn hoặc bằng giá tối thiểu.");
                        break;
                    }
                    menuItemService.findByPriceRange(minPrice, maxPrice).ifPresentOrElse(list -> list.forEach(System.out::println), () -> System.out.println("Không tìm thấy món trong khoảng giá này"));
                    break;
                case 3:
                    LocalDate date = Input.inputDate("Nhập ngày (yyyy-MM-dd): ");
                    double revenueDay = statisticsService.getRevenueByDate(date);
                    System.out.println("Doanh thu ngày " + date + " : " + revenueDay);
                    break;

                case 4:
                    int month = Input.inputIntegerPositive("Nhập tháng: ");
                    int year = Input.inputIntegerPositive("Nhập năm: ");
                    double revenueMonth = statisticsService.getRevenueByMonth(month, year);
                    System.out.println("Doanh thu tháng " + month + "/" + year + " : " + revenueMonth);
                    break;

                default:
                    break;
            }

        } while (choice != 0);
    }
}