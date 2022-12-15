package pr.beh;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import pr.dataaaaaaaa;

public class ReceivePrarllelBeh extends ParallelBehaviour {
    dataaaaaaaa data;

    public ReceivePrarllelBeh(Agent a, dataaaaaaaa data) {
        super(a, WHEN_ANY);
        this.data = data;

        addSubBehaviour(new ReceiveAnsweres(data));
        addSubBehaviour(new WakerBehaviour(getAgent(), 20000) {
            @Override
            protected void onWake() {
                System.out.println("The response timeout has expired");
            }
        });
    }

}
