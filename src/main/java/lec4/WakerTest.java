package lec4;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Date;

public class WakerTest extends WakerBehaviour {
    public WakerTest(Agent a, long timeout) {
        super(a, timeout);
    }

    @Override
    protected void onWake() {
        ACLMessage aclMessage =new ACLMessage(ACLMessage.INFORM);
        aclMessage.addReceiver(new AID("Hello1", false));
        myAgent.send(aclMessage);
    }
}
