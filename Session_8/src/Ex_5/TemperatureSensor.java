package Ex_5;

import Ex_1.Fan;
import Ex_5.observer.Humidifier;

import java.util.ArrayList;
import java.util.List;

public class TemperatureSensor implements Subject{
    private List<Observer> observers = new ArrayList<>();
    private int thisTemp;

    public void setThisTemp(int thisTemp) {
        this.thisTemp = thisTemp;
        notifyObserver();
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
        if(observer instanceof Fan){
            System.out.println("Quạt đã đăng ký nhận cảm biến");
        }else if(observer instanceof Humidifier){
            System.out.println("Máy tạo độ ẩm đã đăng ký");
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers){
            observer.update(thisTemp);
        }
    }

    public void getDeviceStatus(){
        System.out.println("Nhiệt độ hiện tại: " + this.thisTemp);
        for (Observer observer : observers){
            observer.update(thisTemp);
        }
    }
}
