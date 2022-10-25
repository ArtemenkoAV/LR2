package lec4;


import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class PeriodicPrintingBehaviour extends TickerBehaviour {
    private Agent myAgent;
    public PeriodicPrintingBehaviour(Agent a, long period) {
        super(a, period);
        this.myAgent = a;
    }

    @Override
    protected void onTick() {
        ACLMessage m = new ACLMessage(ACLMessage.REQUEST);
        m.addReceiver(new AID("ReplierAgent", false));
        m.setContent("Hello from" + myAgent.getLocalName());
        myAgent.send(m);

    }
}
