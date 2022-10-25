package pr3SR;

import jade.core.Agent;

public class BuyerAgent extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new ReceiverBidAndSendBid());


    }
}
