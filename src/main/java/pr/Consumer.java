package pr;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import pr.beh.ConsumerFSM;

public class Consumer extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new WakerBehaviour(this, 15000) {
            @Override
            protected void onWake() {
                getAgent().addBehaviour(new ConsumerFSM());
            }
        });
    }
}
