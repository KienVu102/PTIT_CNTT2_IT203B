package Ex_4.observer;

import Ex_4.Observer;

public class Fan implements Observer {

    @Override
    public void update(int temperature) {
        if(temperature < 20){
            System.out.println("Quạt: Nhiệt độ thấp tự động tắt");
        }else{
            System.out.println("Quạt: Nhiệt độ cao, tăng tốc độ quạt");
        }
    }
}
