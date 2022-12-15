package prAuc;


import jade.core.Agent;
import lab4.DfHelper;

import java.util.Collections;

public class SellerAgent extends Agent {
    @Override
    protected void setup() {
        DfHelper.registerItself(Collections.singletonList("seller"), this);
        String s = this.getLocalName().split("_")[1];
        double price = Double.parseDouble(s);

        addBehaviour(new SellingAuctionBehaviour(this, price));
    }
}
