package lab4.Config;

import lab4.XMLHelper;

import java.util.ArrayList;
import java.util.List;

public class CreatingSESCfg {
    public static void main(String[] args) {
        SESCfg ses = new SESCfg();
        List<Double> c = new ArrayList<>();
        c.add(-78.985);
        c.add(20.313);
        c.add(-1.3185);
        c.add(0.0247);
        ses.setC(c);
        XMLHelper.marshalAny(ses, "SES.xml");
    }
}
