package lab4.Beh.DistributerBeh;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Datas.DistributerData;

public class SendTaskForTopic extends WakerBehaviour {
    DistributerData data;
    public SendTaskForTopic(Agent a, long timeout, DistributerData data) {
        super(a, timeout);
        this.data = data;
    }

    @Override
    protected void onWake() {
        ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
        msg.setContent(String.valueOf(data.getLoad()));
        msg.setProtocol("Task");
        msg.addReceiver(data.getTopic());
        System.out.println("Task load "+data.getTopic());
        getAgent().send(msg);
    }
}
