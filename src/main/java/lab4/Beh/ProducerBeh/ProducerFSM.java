package lab4.Beh.ProducerBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lab4.Beh.ProducerBeh.FSMBeh.*;
import lab4.Datas.ProducerData;

public class ProducerFSM extends FSMBehaviour {
    String distributersName;
    double neededLoad;
    ProducerData producerData;
    AID topic;
    public ProducerFSM(Agent a, String distributersName, double neededLoad, ProducerData producerData, AID topic) {
        super(a);
        this.distributersName = distributersName;
        this.neededLoad = neededLoad;
        this.producerData = producerData;
        this.topic=topic;

        registerFirstState(new SendingPrice(getAgent(), distributersName, neededLoad, producerData, topic),
                "SendPrice");
        registerState(new ReceivingPricec(getAgent(), neededLoad, producerData, distributersName, topic),
                "ReceivePrice");
        registerState(new WaitingForDecision(getAgent(),10000, distributersName, producerData, neededLoad),
                "WaitForDecision");
        registerLastState(new AfterWin(getAgent(), distributersName, neededLoad, producerData), "Winner");

        registerLastState(new DidntSellEnergy(), "noEnergy");
        registerLastState(new BoughtEnergy(), "BoughtEnergy");


        registerDefaultTransition("SendPrice", "ReceivePrice");
        registerDefaultTransition("ReceivePrice", "WaitForDecision");
        registerTransition("WaitForDecision", "Winner", 1);
        registerTransition("WaitForDecision", "noEnergy",2);
        registerTransition("WaitForDecision", "BoughtEnergy",3);
    }


}
