package pr3SR;

import jade.core.AID;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;

public class SellerAgent extends Agent {
    List<AID> receivers = new ArrayList<>();
    @Override
    protected void setup() {
        if(this.getLocalName().equals("Seller")) {
            receivers.add(new AID("Buyer1", false));
            receivers.add(new AID("Buyer2", false));
            receivers.add(new AID("Buyer3", false));
            addBehaviour(new RecivedBid(this, receivers));
            addBehaviour(new SendInitialBid(this, receivers, 15000));

        }
    }
}
