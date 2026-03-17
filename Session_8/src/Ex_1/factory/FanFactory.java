package Ex_1.factory;

import Ex_1.Device;
import Ex_1.Fan;

public class FanFactory extends DeviceFactory{

    @Override
    public Device createDevice() {
        System.out.println("Đã tạo quạt mới");
        return new Fan();
    }
}
