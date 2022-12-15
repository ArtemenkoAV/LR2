package pr.beh;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import pr.AgentPrice;
import pr.dataaaaaaaa;

public class ReceiveAnsweres extends Behaviour {
    dataaaaaaaa data;
    int count = 0;
    public ReceiveAnsweres(dataaaaaaaa data) {
        this.data = data;
    }

    
    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol("price"));
        ACLMessage receive = getAgent().receive(mt);
        if(receive!=null){
            data.getAgentPrices().add(new AgentPrice(Integer.parseInt(receive.getContent()),
                    receive.getSender().getLocalName()));
            count++;
        }
        else {
            block();
        }
    }

    @Override
    public boolean done() {
        return count==data.getNumberOfProducers();
    }
}
