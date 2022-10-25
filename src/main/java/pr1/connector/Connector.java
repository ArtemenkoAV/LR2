package pr1.connector;

import pr1.model.DataClass;

public abstract class Connector {
    public String var ="dasd";
    protected boolean checkData(DataClass data) {
        if (data == null || data.getData() == null || data.getData().isEmpty()){
            return false;
        }
        return true;
    }

    abstract boolean sendData (DataClass data);

    boolean innerServerOk(){
        return true;
    }
}
