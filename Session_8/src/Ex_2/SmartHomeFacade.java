package Ex_2;

import Ex_1.AirConditioner;
import Ex_1.Fan;
import Ex_1.Light;

public class SmartHomeFacade {
    private Light light = new Light();
    private Fan fan = new Fan();
    private AirConditioner  airConditioner = new AirConditioner();
    private TemperatureSensor sensor; // Đối tượng cảm biến nhiệt độ


    public SmartHomeFacade(TemperatureSensor sensor) {
        this.sensor = sensor;
    }

    public void leaveHome(){
        light.turnOff();
        fan.turnOff();
        airConditioner.turnOff();
        System.out.println("Kích hoạt chế độ rời nhà!!\n");
    };

    public void sleepMode(){
        light.turnOff();
        airConditioner.setTemperature(27);
        fan.turnOff(); // có thể mở rộng thêm với chức năng cho nhỏ mức quạt
        System.out.println("Kích hoạt chế độ ngủ!!\n");
    }

    public void getCurrentTemperature(){
        double currentTemp = this.sensor.getTemperatureCelsius();
        System.out.println("Nhiệt độ hiện tại: " + currentTemp + " độ C");
    }
}
