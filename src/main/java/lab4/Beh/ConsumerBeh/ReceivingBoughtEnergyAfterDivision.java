package lab4.Beh.ConsumerBeh;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReceivingBoughtEnergyAfterDivision extends Behaviour {
    int onEnd = 1;
    @Override
    public void action() {
        MessageTemplate reply = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol("IBoughtEnergyAfterDivision"));
        ACLMessage myReply = getAgent().receive( reply );
        if ( myReply != null) {
            System.out.println(getAgent().getLocalName()+": I got my energy :) " + myReply.getContent());
            onEnd = 2;
        }
        else{
            block();
        }
    }

    @Override
    public boolean done() {
        return onEnd==2;
    }
}
