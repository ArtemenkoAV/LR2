package lab4;

public class TimeHelper {

    private static long startTime = System.currentTimeMillis();
    public static int hourDuration = 75000;

    public static int getActualHour(){
        return (int) (System.currentTimeMillis() - startTime) / hourDuration % 24;
    }

    public static int getDelay(){
        return hourDuration - (int) (System.currentTimeMillis() - startTime) % hourDuration;
    }
}
