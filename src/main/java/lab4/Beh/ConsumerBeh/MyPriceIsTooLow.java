package lab4.Beh.ConsumerBeh;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lab4.Datas.ConsumerData;

public class MyPriceIsTooLow extends Behaviour {
    ConsumerData consumerData;
    int onEnd=1;

    public MyPriceIsTooLow(Agent a, ConsumerData consumerData) {
        super(a);
        this.consumerData = consumerData;
    }

    @Override
    public void action() {
        MessageTemplate reply = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.REJECT_PROPOSAL),
                MessageTemplate.MatchProtocol("MaxPriceTooLow"));
        ACLMessage myReply = getAgent().receive( reply );
        if (myReply != null) {
            double lowestPriceInTrade = Double.parseDouble(myReply.getContent());
            consumerData.setMaxPrice(lowestPriceInTrade+0.01);
            System.out.println(getAgent().getLocalName()+": my last price was too low, my new price is "+lowestPriceInTrade);
            onEnd = 2;
        }
        else {
            block();
        }

    }

    @Override
    public boolean done() {
        return onEnd == 2;
    }
}
