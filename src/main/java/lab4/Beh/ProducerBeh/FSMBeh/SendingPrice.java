package lab4.Beh.ProducerBeh.FSMBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Datas.ProducerData;

public class SendingPrice extends OneShotBehaviour {

    String distributersName;
    double neededLoad;
    ProducerData producerData;
    AID topic;
    int onEnd;
    public SendingPrice(Agent a, String distributersName, double neededLoad, ProducerData producerData, AID topic) {
        super(a);
        this.distributersName = distributersName;
        this.neededLoad = neededLoad;
        this.producerData = producerData;
        this.topic=topic;
    }
    @Override
    public int onEnd() {return onEnd;}
    @Override
    public void action() {
        if (neededLoad < producerData.getProducerLoad().get(getAgent().getAID())) {
            double myPrice = 1000/producerData.getProducerLoad().get(getAgent().getAID());
            ACLMessage price = new ACLMessage(ACLMessage.INFORM);
            price.setContent(String.valueOf(myPrice));
            price.addReceiver(topic);
            price.setProtocol("PriceFromProducer");
            getAgent().send(price);
            System.out.println(getAgent().getLocalName()+" sent his price "+myPrice);
            onEnd = 1;
        }else {
            onEnd = 2;
        }

    }

}
