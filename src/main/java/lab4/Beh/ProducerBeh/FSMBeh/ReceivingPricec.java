package lab4.Beh.ProducerBeh.FSMBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import lab4.Datas.ProducerData;

public class ReceivingPricec extends ParallelBehaviour {
    double neededLoad;
    ProducerData producerData;
    String distributersName;
    AID topic;

    public ReceivingPricec(Agent a, double neededLoad, ProducerData producerData, String distributersName, AID topic) {
        super( WHEN_ANY);
        this.neededLoad = neededLoad;
        this.producerData = producerData;
        this.distributersName = distributersName;
        this.topic = topic;

        addSubBehaviour(new ReceivingPricesFromOtherProducers(getAgent(), neededLoad, producerData, distributersName,
                topic));
        addSubBehaviour(new WakerBehaviour(getAgent(), 4000) {
            @Override
            protected void onWake() {
                super.onWake();
            }
        });
    }
}
