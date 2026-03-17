package Ex_5;

import Ex_1.AirConditioner;
import Ex_1.Fan;
import Ex_1.Light;

public class SleepModeCommand implements Command{
    private Light light = new Light();
    private Fan fan = new Fan();
    private AirConditioner airConditioner = new AirConditioner();

    private boolean sleepMode;
    private boolean wasLightOn;
    private double previousTemperature;
    private boolean wasFanOn;



    public SleepModeCommand() {
        this.sleepMode = false;
    }

    public void setSleepMode(boolean sleepMode) {
        this.sleepMode = sleepMode;
    }

    public boolean getSleepMode() {
        return sleepMode;
    }

    @Override
    public void execute() {
        // Lưu trạng thái trước khi thực hiện
        wasLightOn = ((Light) light).isOn();
        previousTemperature = airConditioner.getTemperature();
        
        System.out.println("SleepMode: Tắt đèn\n" +
                "SleepMode: Điều hòa set 28°C\n" +
                "SleepMode: Quạt tốc độ thấp");
        // thực hiện lần lượt các lệnh:
        // tắt đèn,
        light.turnOff();
        // set nhiệt độ 28, mặc định nhiệt độ ổn định,
        airConditioner.setTemperature(28);
        // set quạt thấp.
        fan.lowFan();
        this.setSleepMode(true);
    }

    @Override
    public void undo() {
        if (sleepMode) {
            System.out.println("Undo SleepMode: Khôi phục trạng thái trước đó");
            // Khôi phục trạng thái đèn
            if (wasLightOn) {
                light.turnOn();
            }
            // Khôi phục nhiệt độ điều hòa
            airConditioner.setTemperature(previousTemperature);
            // Khôi phục trạng thái quạt (chỉ đơn giản là bật lại)
            if (wasFanOn) {
                fan.turnOn();
            }
            this.setSleepMode(false);
        } else {
            System.out.println("Không có gì để undo - chế độ ngủ chưa được kích hoạt");
        }
    }
}
