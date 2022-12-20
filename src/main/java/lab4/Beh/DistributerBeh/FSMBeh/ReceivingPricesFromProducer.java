package lab4.Beh.DistributerBeh.FSMBeh;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lab4.Datas.OnEnd;
import lab4.Datas.PriceForDistributerData;
import lab4.Datas.PriceWithNameForDistributerData;

import java.util.ArrayList;

public class ReceivingPricesFromProducer extends Behaviour {
    PriceForDistributerData priceForDistributerData;
    OnEnd onEnd;
    public ReceivingPricesFromProducer(Agent a, PriceForDistributerData priceForDistributerData, OnEnd onEnd) {
        super(a);
        this.priceForDistributerData = priceForDistributerData;
        this.onEnd = onEnd;
    }

    @Override
    public void action() {
        MessageTemplate price = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol("PriceFromProducer"));
        ACLMessage priceFromProducer = getAgent().receive(price);
        if (priceFromProducer != null) {
            double hisPrice = Double.parseDouble(priceFromProducer.getContent());
            ArrayList<PriceWithNameForDistributerData> prices = priceForDistributerData.getPricesWithNames();
            prices.add(new PriceWithNameForDistributerData(hisPrice, priceFromProducer.getSender().getLocalName()));
            priceForDistributerData.setPricesWithNames(prices);
            onEnd.setOnEnd(1);

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
