package lab4.Beh.ProducerBeh;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import lab4.Config.SESCfg;
import lab4.Datas.ProducerData;
import lab4.TimeHelper;
import lab4.XMLHelper;

import java.util.List;

public class SESParcer extends TickerBehaviour {
    ProducerData producerData;

    public SESParcer(Agent a, long period, ProducerData producerData) {
        super(a, period);
        this.producerData = producerData;
    }
    Double[] production = new Double[24];


    @Override
    protected void onTick() {

        SESCfg ses = XMLHelper.unMarshalAny(SESCfg.class, "SES.xml");
        List<Double> c = ses.getC();

        for (int i = 0; i < 24; i++) {
            if (i <= 5 || i >= 19) {
                production[i] = 0.0;
            } else {
                production[i] = 0.0;
                for (int j = 0; j < c.size(); j++) {
                    production[i] += c.get(j) * Math.pow(i, j);
                }
            }
        }
        double value = production[TimeHelper.getActualHour()];
        producerData.getProducerLoad().put(getAgent().getAID(), value);
        System.out.println(getAgent().getLocalName()+" storage is "+producerData.getProducerLoad().get(getAgent().getAID()));

    }
}
