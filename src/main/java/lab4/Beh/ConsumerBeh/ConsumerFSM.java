package lab4.Beh.ConsumerBeh;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lab4.Datas.ConsumerData;
import lab4.Datas.OnEnd;
import lab4.TimeHelper;

public class ConsumerFSM extends FSMBehaviour {
    ConsumerData consumerData;
    OnEnd onEnded = new OnEnd();

    public ConsumerFSM(Agent a, ConsumerData consumerData) {
        super(a);
        this.consumerData = consumerData;
        registerFirstState(new SendingTaskToAuction(getAgent(), TimeHelper.getDelay()+2000, consumerData),
                "SendReq");
        registerState(new ReceivingAnswerForConsumerPar(getAgent(), consumerData, onEnded), "ReceiveAnswer");

        registerLastState(new StarterConsumerFSM(getAgent(), consumerData), "Restart");
        registerDefaultTransition("SendReq","ReceiveAnswer");
        registerDefaultTransition("ReceiveAnswer", "Restart");

    }

}
