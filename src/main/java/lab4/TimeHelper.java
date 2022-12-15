package lab4;

public class TimeHelper {

    private static long startTime = System.currentTimeMillis();
    private static int hourDuration = 60_000;

    public static int getActualHour(){
        return (int) (System.currentTimeMillis() - startTime) / hourDuration % 24;
    }

    public static int getDelay(){
        return hourDuration - (int) (System.currentTimeMillis() - startTime) % hourDuration;
    }
}
