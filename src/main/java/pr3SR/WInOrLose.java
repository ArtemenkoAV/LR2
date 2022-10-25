package pr3SR;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class WInOrLose extends Behaviour {
    private MessageTemplate mt = MessageTemplate.and(
            MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL),
                    MessageTemplate.MatchPerformative(ACLMessage.REJECT_PROPOSAL)),
            MessageTemplate.MatchProtocol("WinOrLose"));
    @Override
    public void action() {
        ACLMessage winLose = myAgent.receive(mt);
        if(winLose!=null){
            System.out.println("I received a message from "+winLose.getSender() + "that i" + winLose.getContent());
        }
        else{
            block();
        }

    }

    @Override
    public boolean done() {
        return false;
    }
}
