package pr3SR;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;
import java.util.List;

public class RecivedBid extends Behaviour {
    List<ACLMessage> messages = new ArrayList<>();
    List<AID> receivers;

    public RecivedBid(Agent a, List<AID> receivers) {
        super(a);
        this.receivers = receivers;
    }

    private MessageTemplate mt = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.PROPOSE),
            MessageTemplate.MatchProtocol("Buyer"));

    @Override
    public void action() {

        ACLMessage seller = myAgent.receive(mt);
        if (seller!=null){
            messages.add(seller);
        }
        if(messages.size()==3){
            Winner winner = new Winner(messages);
            for(AID receiver:receivers){
                if(receiver.equals(winner.win().getSender())){
                    ACLMessage ms = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                    ms.setContent("You won");
                    ms.setProtocol("WinOrLose");
                    ms.addReceiver(receiver);
                    myAgent.send(ms);
                    System.out.println("Winner is "+" "+receiver.getLocalName());
                }
                else {
                    ACLMessage ms = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
                    ms.setContent("You Lose");
                    ms.setProtocol("WinOrLose");
                    ms.addReceiver(receiver);
                    myAgent.send(ms);
                    System.out.println("Lossers is "+" "+receiver.getLocalName());
                }
            }
            messages.clear();
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
