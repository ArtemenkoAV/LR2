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

        registerFirstState(new SendingPrice(getAgent(), distributersName, neededLoad, producerData, topic), "SENDINGPRICE");
        registerState(new ReceivingPricec(getAgent(), neededLoad, producerData, distributersName, topic), "RECEIVINGPRICE");
        registerState(new WaitingForDecision(getAgent(),5000, distributersName, producerData, neededLoad), "WAITINGFORDECISION");
        registerLastState(new AfterWin(getAgent(), distributersName, neededLoad, producerData), "AFTERWIN");
        registerLastState(new DontHaveEnergy(),"NOENERGY");
        registerLastState(new DidntSellEnergy(), "AFTERLOST");
        registerLastState(new BoughtEnergy(), "BOUGHT");


        registerTransition("SENDINGPRICE", "RECEIVINGPRICE", 1);
        registerTransition("SENDINGPRICE", "NOENERGY", 2);
        registerDefaultTransition("RECEIVINGPRICE", "WAITINGFORDECISION");
        registerTransition("WAITINGFORDECISION", "AFTERWIN", 1);
        registerTransition("WAITINGFORDECISION", "AFTERLOST",2);
        registerTransition("WAITINGFORDECISION", "BOUGHT",3);
    }


}
