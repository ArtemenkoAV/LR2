package pr1.connector;

import pr1.model.DataClass;

public class Iec104Connector extends Connector {
    @Override
    public boolean sendData(DataClass data) {
        checkData(data);
        System.out.println("Data sent vid iec 104");
        return true;
    }
}
