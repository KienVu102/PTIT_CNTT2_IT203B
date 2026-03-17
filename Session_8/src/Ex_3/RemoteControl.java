package Ex_3;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RemoteControl {

    private Map<Integer, Command> buttons = new HashMap<>();
    private Stack<Command> history = new Stack<>();

    // Set và gán command
    public void setCommand(int slot, Command command) {
        buttons.put(slot, command);
        System.out.println("Đã gán command cho nút " + slot);
    }

    public void pressButton(int slot){
        Command command = buttons.get(slot);
        if(command != null){
            command.execute();
            history.push(command);
        }else{
            System.out.println("Chưa có chức năng");
        }
    }

    public void undoCommand(){
        if(history.isEmpty()) return;
        Command currCommand = history.pop();
        if(currCommand != null){
            currCommand.undo();
        }else {
            System.out.println("Yêu cầu trống, không thể undo");
        }
    }

}
