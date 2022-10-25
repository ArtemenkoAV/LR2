package pr1.writer;

import pr1.model.ConnectorType;
import pr1.model.Mode;

import java.util.List;

public class ConsoleWriter implements Writer{
    private Mode mode;

    @Override
    public void execute(String msg) {
        if (mode == Mode.Out){
            System.out.println(addInfo(msg));
        } else {
            System.err.println(addInfo(msg));
        }
    }

    @Override
    public void configure(List<String> args) {
        if (args.size() < 1 ){
            System.err.println("Console writer args must contain mode, default value will be applied");
        } else {
            this.mode = Mode.valueOf(args.get(0));
        }
    }

    @Override
    public ConnectorType getType() {
        return ConnectorType.Console;
    }
}
