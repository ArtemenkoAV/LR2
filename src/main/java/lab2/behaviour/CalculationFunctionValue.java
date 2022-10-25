package lab2.behaviour;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CalculationFunctionValue extends Behaviour {

    private MessageTemplate mt = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.INFORM),
            MessageTemplate.MatchProtocol("xAndDelta"));
    @Override
    public void action() {
        ACLMessage xAndDelta = myAgent.receive(mt);
        if(xAndDelta!=null){
            System.out.println(myAgent.getLocalName()+ "recive " + xAndDelta.getSender().getLocalName()+ " " +xAndDelta.getContent());
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
