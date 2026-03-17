package Ex_5;

import Ex_1.Light;
import Ex_5.observer.Fan;
import Ex_5.observer.Humidifier;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SleepModeCommand sleepModeCommand = new SleepModeCommand();
        TemperatureSensor tempSensor = new TemperatureSensor();
        tempSensor.attach(new Fan());
        tempSensor.attach(new Humidifier());

        int choice;

        do {
            System.out.println("\n============ MENU =============");
            System.out.println("1. Kích hoạt chế độ ngủ (gọi SleepModeCommand).\n" +
                    "2. Thay đổi nhiệt độ (giả lập).\n" +
                    "3. Xem trạng thái thiết bị.\n" +
                    "4. Thoát");
            System.out.print("Lựa chọn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    sleepModeCommand.execute();
//                    tempSensor.setThisTemp(28); // set nhiet do mac dinh
                    break;
                case 2:
                    System.out.println("Nhập nhiệt độ thay đổi: ");
                    int temp = Integer.parseInt(sc.nextLine());
                    tempSensor.setThisTemp(temp);
                    break;
                case 3:
                    tempSensor.getDeviceStatus();
                    break;
                case 4:
                    sc.close();
                    System.out.println("Chế độ ngủ hiện tại đang: " + (sleepModeCommand.getSleepMode() ? "Mở" : "Tắt"));
                    System.out.println("Thoát chương trình");
                    return;
                default:
                    break;
            }
        }while (choice != 4);
    }
}
