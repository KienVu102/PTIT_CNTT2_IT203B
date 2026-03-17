package Ex_4;

import Ex_1.Fan;

import java.util.ArrayList;
import java.util.List;

public class TemperatureSensor implements Subject{
    private List<Observer> observers = new ArrayList<>();
    private int currentTemp;

    public TemperatureSensor() {
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
        System.out.println("Cảm biến: Nhiệt độ = " + currentTemp);
        notifyObservers();
    }


    @Override
    public void attach(Observer o) {
        observers.add(o);   // thêm 1 đối tượng cần theo dõi và nhận thông báo từ hệ thống cảm biến
        if (o instanceof Fan){
            System.out.println("Quạt: Đã đăng ký nhận thông báo");
        }else{
            System.out.println("Máy tạo ẩm: Đã đăng ký");
        }
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers){
            observer.update(currentTemp);
        }
    }
}
