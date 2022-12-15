package lab4.Agent;

import jade.core.Agent;
import lab4.Beh.DistributerBeh.ReceiveTaskToAuction;
import pr.beh.ReciveTopicName;

public class DistributerAgent extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new ReceiveTaskToAuction());

    }
}

