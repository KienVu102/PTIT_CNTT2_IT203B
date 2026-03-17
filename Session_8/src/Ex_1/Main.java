package Ex_1;

import Ex_1.factory.AirConditionerFactory;
import Ex_1.factory.DeviceFactory;
import Ex_1.factory.FanFactory;
import Ex_1.factory.LightFactory;

import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static Stack<Device> deviceFactories = new Stack<>();
    public static void main(String[] args) {

        HardwareConnection hwConnect = HardwareConnection.getInstance();
        int choice;
        do {
            System.out.println("\n========= MENU ==========");
            System.out.println("1. Kết nối phần cứng (lấy instance Singleton)." +
                    "\n" +
                    "2. Tạo thiết bị mới (chọn loại, factory tạo ra device)." +
                    "\n" +
                    "3. Bật/tắt thiết bị vừa tạo." +
                    "\n" +
                    "4. Tạo thêm thiết bị" +
                    "\n" +
                    "5. Kiểm tra Singleton" +
                    "\n" +
                    "6. Thoát");
            System.out.print("Lựa chọn của bạn: ");
             choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    hwConnect.connect();
                    break;
                case 2:
                    createNewDevice();
                    break;
                case 3:
                    // Bật thiết bị
                    System.out.print("Bật thiết bị vừa tạo: ");
                    Device currDevice = deviceFactories.peek();
                    currDevice.turnOn();
                    break;
                case 4:
                    // Tạo thêm thiết bị
                    createNewDevice();
                    break;
                case 5:
                    hwConnect.connect();
                    break;
                case 6:
                    sc.close();
                    hwConnect.disconnect();
                    System.out.println("Thoát chương trình");
                    break;
            }
        }while (choice != 6);

    }

    public static void createNewDevice(){
        // Tạo thiết bị mới
        boolean isCreated = false;
        while (!isCreated){
            System.out.println("Chọn loại: 1. Đèn, 2. Quạt, 3. Điều hòa\n" +
                    "Chọn: ");
            int deviceChoice = sc.nextInt();
            sc.nextLine();
            switch (deviceChoice){
                case 1:
                    DeviceFactory newLight = new LightFactory();
                    deviceFactories.push(newLight.createDevice());
                    isCreated = true;
                    break;
                case 2:
                    DeviceFactory newFan = new FanFactory();
                    isCreated = true;
                    deviceFactories.push(newFan.createDevice());
                    break;
                case 3:
                    DeviceFactory newAC = new AirConditionerFactory();
                    isCreated = true;
                    deviceFactories.push(newAC.createDevice());
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;

            }
        }
    }
}
