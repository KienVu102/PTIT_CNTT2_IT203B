package Ex_5.observer;

import Ex_5.Observer;

public class Humidifier implements Observer {
    public Humidifier() {
    }

    @Override
    public void update(int temperature) {
        System.out.println("Máy tạo ẩm: Điều chỉnh nhiệt độ cho nhiệt độ " + temperature);
    }
}
