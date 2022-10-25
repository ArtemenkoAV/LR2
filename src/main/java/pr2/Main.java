package pr2;


import lombok.SneakyThrows;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
//        /* */
//        byte [] arr  = new byte[]{2,3,4,5};
//            try {
//                connect("127.0.0.1", 8080);
//                parseArr(arr);
//            } catch (Iec104LibDataParsingException e){
//                e.printStackTrace();
//            } catch (Iec104LibServerConnectException e1){
//                e1.printStackTrace();
//                reconnect("127.0.0.1",8080);
//            } catch (Iec104LibException e2){
//                e2.printStackTrace();
//        }
        MyTestException myTestException = new MyTestException("Can not connect");
            throw myTestException;
    }

    private static void parseArr(byte[] arr) {
        for (int i = 0; i < arr.length; i++){
            if(i == 2){
                throw new Iec104LibDataParsingException(arr);
            }
        }
    }

    private static boolean connect(String ip, int port){
        throw new Iec104LibServerConnectException(ip,port);
    }

    private static  boolean reconnect(String ip, int port){
        return true;
    }

}
