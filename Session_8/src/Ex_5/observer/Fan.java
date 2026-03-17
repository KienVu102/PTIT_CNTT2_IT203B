package Ex_5.observer;

import Ex_5.Observer;

public class Fan implements Observer {

    public Fan() {
    }

    @Override
    public void update(int temperature) {
        if(temperature < 20){
            System.out.println("Quạt: Nhiệt độ thấp tự động tắt");
        }else{
            System.out.println("Quạt: Nhiệt độ cao, tăng tốc độ quạt");
        }
    }
}
