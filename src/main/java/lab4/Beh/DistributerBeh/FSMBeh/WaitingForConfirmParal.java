package lab4.Beh.DistributerBeh.FSMBeh;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class WaitingForConfirmParal extends ParallelBehaviour {
    private Behaviour receiver, waker;

    @Override
    public int onEnd() {
        if (receiver.done()) {
            return 1;
        }  else {
            return 2;
        }
    }

    public WaitingForConfirmParal(Agent a, long wakeUpTime) {
        super(a, WHEN_ANY);

        receiver = new WaitingForConfirm();
        waker = new WakerBehaviour(getAgent(), wakeUpTime) {
            @Override
            protected void onWake() {
                super.onWake();
            }
        };
        addSubBehaviour(receiver);
        addSubBehaviour(waker);
    }
}
