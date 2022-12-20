package lab4.Beh.ConsumerBeh;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import lab4.Datas.ConsumerData;

public class CreateConsumerData extends OneShotBehaviour {
    ConsumerData consumerData;

    public CreateConsumerData(Agent a, ConsumerData consumerData) {
        super(a);
        this.consumerData = consumerData;
    }

    @Override
    public void action() {
        consumerData.setMaxPrice(300);


    }
}
