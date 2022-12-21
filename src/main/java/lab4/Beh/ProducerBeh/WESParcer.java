package lab4.Beh.ProducerBeh;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import lab4.Config.SESCfg;
import lab4.Config.WESCfg;
import lab4.Datas.ProducerData;
import lab4.TimeHelper;
import lab4.XMLHelper;

import java.util.List;

public class WESParcer extends TickerBehaviour {
    ProducerData producerData;

    public WESParcer(Agent a, long period, ProducerData producerData) {
        super(a, period);
        this.producerData = producerData;
    }
    Double[] production = new Double[]{9.757779704751353, 18.266510025681676, 15.8757780073765025, 8.70968927970475, 0.0, 0.0,
            10.598052030455568, 0.0, 2.1511444117808427, 9.1414845793996, 0.8382181822275747, 6.229445588857814,
            9.309234432456359, 0.0, 11.456969272142675, 0.0, 0.0, 10.973807986919512, 19.561405755439953,
            13.936885738401923, 2.381665325635846, 7.205232560843886, 5.7394282693902126, 0.0};


    @Override
    protected void onTick() {

//        WESCfg wes = XMLHelper.unMarshalAny(WESCfg.class, "WES.xml");
//        double b1 = wes.getB1();
//        double b2 = wes.getB2();
//
//        for (int i = 0; i < 24; i++) {
//            production[i] = 100*((1/(Math.sqrt(2*Math.PI)*b2))*(Math.exp(-(Math.pow(i-b1,2))/(2*b2*b2)))+0.002);
//
//        }
        double value = production[TimeHelper.getActualHour()];
        producerData.getProducerLoad().put(getAgent().getAID(), value);
        System.out.println(getAgent().getLocalName()+" storage is "+producerData.getProducerLoad().get(getAgent().getAID()));

    }
}
