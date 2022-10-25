package pr2;

import java.util.Arrays;

public class Iec104LibDataParsingException extends Iec104LibException{
    public Iec104LibDataParsingException() {
    }

    public Iec104LibDataParsingException(String message) {
        super(message);
    }

    public Iec104LibDataParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public Iec104LibDataParsingException(Throwable cause) {
        super(cause);
    }

    public Iec104LibDataParsingException(byte[] arr) {
        super("can not parse array "+ Arrays.toString(arr));
    }

}
