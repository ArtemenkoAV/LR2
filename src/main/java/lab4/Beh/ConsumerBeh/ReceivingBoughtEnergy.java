package lab4.Beh.ConsumerBeh;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lab4.Datas.ConsumerData;

public class ReceivingBoughtEnergy extends Behaviour {
    int onEnd = 1;
    ConsumerData data;

    public ReceivingBoughtEnergy(ConsumerData data) {
        this.data = data;
    }

    @Override
    public void action() {
        MessageTemplate reply = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL),
                MessageTemplate.MatchProtocol("IBoughtEnergy"));
        ACLMessage myReply = getAgent().receive( reply );
        if ( myReply != null) {
            System.out.println(getAgent().getLocalName()+": I bought "+ data.getLoad()+" kW power from "+ myReply.getContent());
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
