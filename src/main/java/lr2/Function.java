package lr2;

public class Function {

    public static double funAgent1(double x){
        double value = -x * x + 5;
        return value;
    }
    public static double funAgent2(double x){
        double value = 2*x + 2;
        return value;
    }
    public static double funAgent3(double x){
        double value = Math.sin(x);
        return value;
    }
}
