package lab4.Config;

import lab4.XMLHelper;

import java.util.ArrayList;
import java.util.List;

public class CreatingConsumerCfg {
    public static void main(String[] args) {

        ConsumerCfg firstConsumerCfg = new ConsumerCfg();
        List<Double> load1 = new ArrayList<>();
        load1.add(0.85*18.9);
        load1.add(0.85*18.9);
        load1.add(0.85*18.9);
        load1.add(0.8*18.9);
        load1.add(0.8*18.9);
        load1.add(0.8*18.9);
        load1.add(0.9*18.9);
        load1.add(0.9*18.9);
        load1.add(1*18.9);
        load1.add(1*18.9);
        load1.add(1*18.9);
        load1.add(0.95*18.9);
        load1.add(0.95*18.9);
        load1.add(0.95*18.9);
        load1.add(0.95*18.9);
        load1.add(0.9*18.9);
        load1.add(0.9*18.9);
        load1.add(0.9*18.9);
        load1.add(0.9*18.9);
        load1.add(0.9*18.9);
        load1.add(0.9*18.9);
        load1.add(0.9*18.9);
        load1.add(0.85*18.9);
        load1.add(0.85*18.9);
        firstConsumerCfg.setLoad(load1);
        firstConsumerCfg.setDistributerName("firstDistributer");
        firstConsumerCfg.setMaxPrice(100);
        XMLHelper.marshalAny(firstConsumerCfg, "FoodIndustry.xml");

        ConsumerCfg secondConsumerCfg = new ConsumerCfg();
        List<Double> load2 = new ArrayList<>();
        load2.add(0.8*18.9);
        load2.add(0.8*18.9);
        load2.add(0.8*18.9);
        load2.add(0.8*18.9);
        load2.add(0.8*18.9);
        load2.add(0.8*18.9);
        load2.add(0.8*18.9);
        load2.add(1*18.9);
        load2.add(1*18.9);
        load2.add(1*18.9);
        load2.add(0.9*18.9);
        load2.add(1*18.9);
        load2.add(1*18.9);
        load2.add(1*18.9);
        load2.add(1*18.9);
        load2.add(1*18.9);
        load2.add(1*18.9);
        load2.add(0.8*18.9);
        load2.add(0.8*18.9);
        load2.add(0.8*18.9);
        load2.add(0.8*18.9);
        load2.add(0.8*18.9);
        load2.add(0.8*18.9);
        load2.add(0.8*18.9);
        secondConsumerCfg.setLoad(load2);
        secondConsumerCfg.setDistributerName("secondDistributer");
        XMLHelper.marshalAny(secondConsumerCfg, "MetalsPlant.xml");

        ConsumerCfg therdConsumerCfg = new ConsumerCfg();
        List<Double> load3 = new ArrayList<>();
        load3.add(0.2*18.9);
        load3.add(0.2*18.9);
        load3.add(0.2*18.9);
        load3.add(0.2*18.9);
        load3.add(0.2*18.9);
        load3.add(0.2*18.9);
        load3.add(0.2*18.9);
        load3.add(0.4*18.9);
        load3.add(0.6*18.9);
        load3.add(1*18.9);
        load3.add(1*18.9);
        load3.add(1*18.9);
        load3.add(1*18.9);
        load3.add(0.7*18.9);
        load3.add(0.5*18.9);
        load3.add(0.5*18.9);
        load3.add(0.3*18.9);
        load3.add(0.3*18.9);
        load3.add(0.3*18.9);
        load3.add(0.2*18.9);
        load3.add(0.2*18.9);
        load3.add(0.2*18.9);
        load3.add(0.2*18.9);
        load3.add(0.2*18.9);
        therdConsumerCfg.setLoad(load3);
        therdConsumerCfg.setDistributerName("thirdDistributer");
        XMLHelper.marshalAny(therdConsumerCfg, "Mpei.xml");
    }
}
