package lab2.behaviour;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Beh extends Behaviour {

    @Override
    public void action() {
        ACLMessage msgR = myAgent.receive(); //"забирает" из пула одно сообщение
        if (msgR != null) {
            System.out.println("----" + myAgent.getLocalName() + " получил ACL от " + msgR.getSender().getLocalName() + " :");
            System.out.println("    getProtocol() " + msgR.getProtocol());
            System.out.println("    getContent()  " + msgR.getContent());
            System.out.println("    getOntology() " + msgR.getOntology());
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
