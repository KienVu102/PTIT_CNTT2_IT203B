package Ex_1;

public class AirConditioner implements Device{
    private double temperature = 26; // set nhiệt độ ban đầu

    public double getTemperature() {
        return temperature;
    }

    @Override
    public void turnOn() {
        System.out.println("Điều hòa đã được bật");
    }


    @Override
    public void turnOff() {
        System.out.println("Điều hòa đã tắt");
    }

    public void setTemperature(double currentTemp){
        System.out.println("Điều hòa được điều chỉnh đến: " + currentTemp + " độ C");
        this.temperature = currentTemp;
    }
}
