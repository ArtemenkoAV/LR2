package lab4.TestBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Beh.ConsumerBeh.ReceivingAnswerForConsumerPar;
import lab4.Datas.ConsumerData;
import lab4.Datas.OnEnd;
import lab4.TimeHelper;

public class ConsumerFSMTest extends FSMBehaviour {
    ConsumerData consumerData;
    OnEnd onEnd = new OnEnd();
    double load;
    double maxPrice;

    @Override
    public int onEnd() {
        return onEnd.getOnEnd();
    }
    public ConsumerFSMTest(ConsumerData consumerData, double load, double maxPrice) {
        this.consumerData = consumerData;
        this.load = load;
        this.maxPrice = maxPrice;

        registerFirstState(new WakerBehaviour(getAgent(), 21000) {
            @Override
            protected void onWake() {
                System.out.println(getAgent().getLocalName()+": My current load is "+load);
                ACLMessage needs = new ACLMessage(ACLMessage.REQUEST);
                needs.setContent(load+","+maxPrice);
                needs.setProtocol("Task");
                needs.addReceiver(new AID("ThirdDistributer", false));
                getAgent().send(needs);
                consumerData.setLoad(load);
            }
        }, "SendReq");
        registerLastState(new ReceivingAnswerForConsumerPar(getAgent(), consumerData, onEnd), "ReceivingAnswer");
        registerDefaultTransition("SendReq", "ReceivingAnswer");
    }

}
