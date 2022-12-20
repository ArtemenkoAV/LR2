package prAuc;

import lab4.Config.WESCfg;
import lab4.TimeHelper;
import lab4.XMLHelper;

import java.util.Arrays;
import java.util.Random;

public class ttt {
    public static void main(String[] args) {
        Double[] production = new Double[24];
//        Random r =new Random();
//        double a;
//        WESCfg wes = XMLHelper.unMarshalAny(WESCfg.class, "WES.xml");
//        double b1 = wes.getB1();
//        double b2 = wes.getB2();
//
//        for (int i = 0; i < 24; i++) {
//            a = r.nextGaussian(b1, b2);
//            if(a<0){
//                production[i] =0d;
//            }
//            else {
//                production[i]=a;
//            }
//
//        }
        WESCfg wes = XMLHelper.unMarshalAny(WESCfg.class, "WES.xml");
        double b1 = wes.getB1();
        double b2 = wes.getB2();

        for (int i = 0; i < 24; i++) {
            production[i] = 100*((1/(Math.sqrt(2*Math.PI)*b2))*(Math.exp(-(Math.pow(i-b1,2))/(2*b2*b2)))+0.002);

        }
        System.out.println(Arrays.toString(production));
    }
}
