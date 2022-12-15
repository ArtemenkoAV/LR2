package prAuc;


import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

public class BuyerAgent extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new WakerBehaviour(this, 1000) {
            @Override
            protected void onWake() {
                addBehaviour(new BuyingAuctionBehaviour(myAgent));

            }
        });

    }
}
