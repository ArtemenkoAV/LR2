package lab4.Beh.ConsumerBeh;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lab4.Datas.ConsumerData;
import lab4.Datas.OnEnd;
import lab4.TimeHelper;

public class ConsumerFSM extends FSMBehaviour {
    ConsumerData consumerData;
    OnEnd onEnd = new OnEnd();

    public ConsumerFSM(Agent a, ConsumerData consumerData) {
        super(a);
        this.consumerData = consumerData;
        registerFirstState(new SendingTaskToAuction(getAgent(), 20000, consumerData), "SENDREQ");
        registerState(new ReceivingAnswerForConsumerPar(getAgent(), consumerData, onEnd), "RECEIVINGANSWER");
        registerLastState(new StarterConsumerFSM(getAgent(), consumerData), "ONCEAGAIN");
        registerDefaultTransition("SENDREQ","RECEIVINGANSWER");
        registerDefaultTransition("RECEIVINGANSWER", "ONCEAGAIN");
    }

}
