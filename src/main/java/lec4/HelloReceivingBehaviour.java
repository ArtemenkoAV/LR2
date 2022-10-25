package lec4;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class HelloReceivingBehaviour extends Behaviour {
    private Agent myAgent;
    public HelloReceivingBehaviour(Agent a) {
        super(a);
        this.myAgent = a;
    }

    private int count = 0;

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if(msg!=null){

            System.out.println("I received msg from" + msg.getSender().getLocalName()+ "with content"+msg.getContent());
            count++;
        }else {
            block();
        }


    }

    @Override
    public boolean done() {
        return count>10;
    }
}
