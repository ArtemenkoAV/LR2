package lab4.Beh.ProducerBeh;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import lab4.Config.TPPCfg;
import lab4.Datas.ProducerData;
import lab4.XMLHelper;

public class TPPParcer extends TickerBehaviour {
    ProducerData producerData;

    public TPPParcer(Agent a, long period, ProducerData producerData) {
        super(a, period);
        this.producerData = producerData;
    }

    @Override
    protected void onTick() {
        TPPCfg tppCfg = XMLHelper.unMarshalAny(TPPCfg.class, getAgent().getLocalName() + ".xml");
        producerData.getProducerLoad().put(getAgent().getAID(),tppCfg.getA());
        System.out.println(getAgent().getLocalName()+" storage is "+producerData.getProducerLoad().get(getAgent().getAID()));

    }
}
