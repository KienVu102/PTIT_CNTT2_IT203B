package Ex_1;

public class Fan implements Device{

    @Override
    public void turnOn() {
        System.out.println("Quạt đã bật");
    }

    @Override
    public void turnOff() {
        System.out.println("Quạt đã tắt");
    }

    public void lowFan(){
        System.out.println("Quạt tốc độ thấp");
    }
}
