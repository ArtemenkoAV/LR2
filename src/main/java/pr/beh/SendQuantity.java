package pr.beh;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import pr.dataaaaaaaa;

public class SendQuantity extends WakerBehaviour {
    dataaaaaaaa data;
    public SendQuantity(Agent a, long timeout, dataaaaaaaa data) {
        super(a, timeout);
        this.data=data;
    }

    @Override
    protected void onWake() {
        ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
        msg.setContent("20");
        msg.setProtocol("quantity");
        msg.addReceiver(data.getTopic());
        System.out.println(data.getTopic());
        getAgent().send(msg);
    }
}

