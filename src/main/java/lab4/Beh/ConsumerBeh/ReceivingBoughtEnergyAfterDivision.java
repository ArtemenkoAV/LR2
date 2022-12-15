package lab4.Beh.ConsumerBeh;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lab4.Datas.ConsumerData;

import java.util.ArrayList;
import java.util.Arrays;

public class ReceivingBoughtEnergyAfterDivision extends Behaviour {
    int onEnd = 1;
    ConsumerData data;

    public ReceivingBoughtEnergyAfterDivision(ConsumerData data) {
        this.data = data;
    }

    @Override
    public void action() {
        MessageTemplate reply = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL),
                MessageTemplate.MatchProtocol("IBoughtEnergyAfterDivision"));
        ACLMessage myReply = getAgent().receive( reply );
        if ( myReply != null) {
            ArrayList<String> producers = new ArrayList<>(Arrays.asList(myReply.getContent().split(",")));
            System.out.println(getAgent().getLocalName()+": I bought "+ data.getLoad()*producers.size()/2 +
                    " kw power from "+ myReply.getContent());
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
