package lab4.Beh.ConsumerBeh;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class TheyHaveNoEnergy extends Behaviour {
    int onEnd = 1;

    @Override
    public void action() {
        MessageTemplate reply = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol("DontHaveEnergy"));
        ACLMessage myReply = getAgent().receive( reply );
        if (myReply != null) {
            System.out.println(getAgent().getLocalName()+": They don't have any energy for me :(");
            onEnd = 2;
        }
    }

    @Override
    public boolean done() {
        return onEnd == 2;
    }
}

