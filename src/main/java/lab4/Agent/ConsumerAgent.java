package lab4.Agent;

import jade.core.Agent;
import lab4.Beh.ConsumerBeh.ConsumerFSM;
import lab4.Beh.ConsumerBeh.CreateConsumerData;
import lab4.Beh.ConsumerBeh.ReceivingBoughtEnergy;
import lab4.Beh.ConsumerBeh.SendingTaskToAuction;
import lab4.Datas.ConsumerData;
import lab4.TimeHelper;

public class ConsumerAgent extends Agent {
    ConsumerData consumerData = new ConsumerData();


    @Override
    protected void setup() {
        addBehaviour(new CreateConsumerData(this, consumerData));
        addBehaviour(new ConsumerFSM(this, consumerData));
    }
}
