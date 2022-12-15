package lab4.Beh.ProducerBeh.FSMBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lab4.Datas.ProducerData;

import java.util.Objects;

public class ReceivingPricesFromOtherProducers extends Behaviour {
    double neededLoad;
    ProducerData producerData;
    String distributersName;
    AID topic;

    public ReceivingPricesFromOtherProducers(Agent a, double neededLoad, ProducerData producerData, String distributersName, AID topic) {
        super(a);
        this.neededLoad = neededLoad;
        this.producerData = producerData;
        this.distributersName = distributersName;
        this.topic = topic;
    }

    @Override
    public void action() {
        MessageTemplate price = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol("PriceFromProducer"));
        ACLMessage priceFromProducer = getAgent().receive(price);
        if (priceFromProducer != null) {
            if (neededLoad <= producerData.getProducerLoad().get(getAgent().getAID())) {
                double myPrice = 1000/producerData.getProducerLoad().get(getAgent().getAID());
                double minPrice = myPrice/2;
                double enemyPrice = Double.parseDouble(priceFromProducer.getContent());
                String sender = priceFromProducer.getSender().getLocalName();
                double myNewPrice = 0.9 * enemyPrice;
                if (enemyPrice < myPrice&& !Objects.equals(getAgent().getAID().getLocalName(), sender)) {
                    if (myNewPrice >= minPrice) {
                        ACLMessage newPrice = new ACLMessage(ACLMessage.INFORM);
                        newPrice.setContent(String.valueOf(myNewPrice));
                        newPrice.addReceiver(topic);
                        newPrice.setProtocol("PriceFromProducer");
                        getAgent().send(newPrice);
                        System.out.println(getAgent().getLocalName() + ": forget what i said, this my new price " + myNewPrice + ", which is much better than " + priceFromProducer.getSender().getLocalName() + " offers");
                    }else {
                        block();
                    }
                }
            } else {
                block();
            }
        }
        else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
