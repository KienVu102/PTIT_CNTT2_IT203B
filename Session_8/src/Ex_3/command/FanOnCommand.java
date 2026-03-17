package Ex_3.command;

import Ex_1.Fan;
import Ex_3.Command;

public class FanOnCommand implements Command {
    private Fan fan;

    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOn();
    }


    @Override
    public void undo() {
        fan.turnOff();
    }
}
