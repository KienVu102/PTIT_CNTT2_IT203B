package Ex_1.factory;

import Ex_1.AirConditioner;
import Ex_1.Device;

public class AirConditionerFactory extends DeviceFactory {

    @Override
    public Device createDevice() {
        System.out.println("Đã tạo điều hòa mới");
        return new AirConditioner();
    }
}
