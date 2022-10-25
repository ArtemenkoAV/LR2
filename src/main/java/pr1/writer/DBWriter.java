package pr1.writer;

import pr1.model.ConnectorType;

import java.util.List;

public class DBWriter implements Writer{
    @Override
    public void execute(String msg) {

    }

    @Override
    public void configure(List<String> args) {

    }

    @Override
    public ConnectorType getType() {
        return ConnectorType.DB;
    }
}
