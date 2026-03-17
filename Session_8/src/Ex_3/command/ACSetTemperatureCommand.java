package Ex_3.command;

import Ex_1.AirConditioner;
import Ex_3.Command;

public class ACSetTemperatureCommand implements Command {

    private AirConditioner airConditioner;
    private double oldTemp;
    private double newTemp;


    public ACSetTemperatureCommand(AirConditioner airConditioner, double temp) {
        this.airConditioner = airConditioner;
        this.newTemp = temp;
    }

    @Override
    public void execute() {
        oldTemp = airConditioner.getTemperature();
        airConditioner.setTemperature(newTemp);
    }

    @Override
    public void undo() {
        airConditioner.setTemperature(oldTemp);
    }
}
