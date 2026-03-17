package Ex_3;

import Ex_1.AirConditioner;
import Ex_1.Fan;
import Ex_1.Light;
import Ex_3.command.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Gán các nút
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        RemoteControl remote = new RemoteControl();
        Scanner sc = new Scanner(System.in);

        // Set up commands
        remote.setCommand(1, new LightOnCommand(light));
        remote.setCommand(2, new LightOffCommand(light));
        remote.setCommand(3, new FanOnCommand(fan));
        remote.setCommand(4, new FanOffCommand(fan));
        remote.setCommand(5, new ACSetTemperatureCommand(ac, 28));

        int choice;
        do {
            System.out.println("\n========= REMOTE CONTROL MENU ==========");
            System.out.println("1. Bật đèn");
            System.out.println("2. Tắt đèn");
            System.out.println("3. Bật quạt");
            System.out.println("4. Tắt quạt");
            System.out.println("5. Set điều hòa 28°C");
            System.out.println("6. Undo lệnh cuối");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    remote.pressButton(choice);
                    break;
                case 6:
                    remote.undoCommand();
                    break;
                case 0:
                    System.out.println("Thoát chương trình");
                    sc.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;
            }
        } while (choice != 0);
    }
}
