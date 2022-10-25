package pr3SR;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Date;
import java.util.List;

public class SendInitialBid extends TickerBehaviour {

    List<AID> receivers;

    public SendInitialBid(Agent a,List<AID> receivers, long period) {
        super(a, period);
        this.receivers=receivers;
    }


    @Override
    protected void onTick() {
        for(AID receiver:receivers) {
            ACLMessage msg = new ACLMessage(ACLMessage.CFP);
            msg.setContent("200");
            msg.setProtocol("Seller");
            msg.addReceiver(receiver);
//            msg.addReceiver(new AID("Buyer2", false));
//            msg.addReceiver(new AID("Buyer3", false));
            myAgent.send(msg);
        }
    }
}
