package pr2;

public class Iec104LibServerConnectException extends Iec104LibException{
    public Iec104LibServerConnectException() {
    }

    public Iec104LibServerConnectException(String message) {
        super(message);
    }

    public Iec104LibServerConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public Iec104LibServerConnectException(Throwable cause) {
        super(cause);
    }

    public Iec104LibServerConnectException(String ip, int port) {
        super("Can not connect to server "+ip+":"+port);
    }


}
