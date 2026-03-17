package Ex_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OldThermometer oldThermometer = new OldThermometer(); // Tạo 1 nhiệt kế cũ (giả lập)
        TemperatureSensor tempSensor = new ThermometerAdapter(oldThermometer); // tạo thiết bị cảm ứng và lấy ra và chuyển đổi nhiệt độ hiện tại

        SmartHomeFacade homeFacade = new SmartHomeFacade(tempSensor);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Xem nhiệt độ");
            System.out.println("2. Rời nhà");
            System.out.println("3. Chế độ ngủ");
            System.out.println("0. Thoát");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    homeFacade.getCurrentTemperature();
                    break;
                case 2:
                    homeFacade.leaveHome();
                    break;
                case 3:
                    homeFacade.sleepMode();
                    break;
                case 0:
                    return;
            }
        }


    }

}
