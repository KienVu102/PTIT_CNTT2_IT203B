package Ex_4.observer;

import Ex_4.Observer;

public class Humidifier implements Observer {
    @Override
    public void update(int temperature) {
        System.out.println("Máy tạo ẩm: Điều chỉnh nhiệt độ cho nhiệt độ " + temperature);
    }
}
