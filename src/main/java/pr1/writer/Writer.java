package pr1.writer;

import pr1.model.ConnectorType;

import java.util.List;

public interface Writer {
    public String var ="dasd";    void execute(String msg);

    void configure(List<String> args);

    default String addInfo(String msg){
        return Thread.currentThread().getName() + " : "+ System.currentTimeMillis() + " - " + msg;
    }

    ConnectorType getType();

}
