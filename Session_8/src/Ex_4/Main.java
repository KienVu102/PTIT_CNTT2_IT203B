package Ex_4;

import Ex_4.observer.Fan;
import Ex_4.observer.Humidifier;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TemperatureSensor temperatureSensor = new TemperatureSensor();

        int choice;

        do {
            System.out.println("\n============== MENU ==============");
            System.out.println("1. Đăng ký thiết bị theo dõi nhiệt độ.\n" +
                    "2. Thay đổi nhiệt độ (giả lập).\n" +
                    "3. Xem phản ứng tự động của các thiết bị.\n" +
                    "4. Thoát");
            System.out.print("Lựa chọn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Thêm thiết bị: \n" +
                            "1. Quạt\n" +
                            "2. Máy tạo độ ẩm\n" +
                            "Chọn: ");
                    int dChoice = sc.nextInt();
                    sc.nextLine();
                    switch (dChoice) {
                        case 1:
                            temperatureSensor.attach(new Fan());
                            break;
                        case 2:
                            temperatureSensor.attach(new Humidifier());
                            break;
                        default:
                            System.out.println("Lựa chọn thiết bị không tồn tại");
                    }
                    break;
                case 2:
                    // Thay ddooir
                    System.out.println("Nhiệt độ hiện tại: ");
                    int temp = Integer.parseInt(sc.nextLine());
                    temperatureSensor.setCurrentTemp(temp);
                    break;
                case 3:
                    break;
                case 4:
                    sc.close();
                    System.out.println("Thoát chương trình");
                    break;

            }


        }while (choice != 4);
    }
}
