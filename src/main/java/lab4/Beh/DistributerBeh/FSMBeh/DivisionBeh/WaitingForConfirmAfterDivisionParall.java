package lab4.Beh.DistributerBeh.FSMBeh.DivisionBeh;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import lab4.Datas.DistributerData;
import lab4.Datas.OnEnd;
import lab4.Datas.PriceWithNameForDistributerData;

public class WaitingForConfirmAfterDivisionParall extends ParallelBehaviour {

    DistributerData data;
    OnEnd onEnd = new OnEnd();

    @Override
    public int onEnd() {
        return onEnd.getOnEnd();
    }

    public WaitingForConfirmAfterDivisionParall(Agent a, long wakeUpTime, DistributerData data) {
        super(a, WHEN_ANY);
        this.data = data;
        addSubBehaviour(new WaitingForConfirmAfterDivision(getAgent(), data, onEnd));
        addSubBehaviour(new WakerBehaviour(getAgent(), wakeUpTime) {
            @Override
            protected void onWake() {
                super.onWake();
            }
        });
    }
}
