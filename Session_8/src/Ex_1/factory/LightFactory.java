package Ex_1.factory;

import Ex_1.Device;
import Ex_1.Light;

public class LightFactory extends DeviceFactory{

    @Override
    public Device createDevice() {
        System.out.println("Đã tạo đèn mới");
        return new Light();
    }
}
