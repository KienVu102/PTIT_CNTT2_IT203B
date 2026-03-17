package Ex_1;

public class Light implements Device{

    private boolean isOn = false;

    public boolean isOn() {
        return isOn;
    }

    public void setOn() {
        isOn = true;
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Đèn đã tắt");
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Đèn đã bật");
    }
}
