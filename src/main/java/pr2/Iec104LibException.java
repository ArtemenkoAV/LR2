package pr2;

public class Iec104LibException extends RuntimeException{
    public Iec104LibException() {
    }

    public Iec104LibException(String message) {
        super("Lib104 exception "+message);
    }

    public Iec104LibException(String message, Throwable cause) {
        super("Lib104 exception "+message, cause);
    }

    public Iec104LibException(Throwable cause) {
        super(cause);
    }
}
