package lab4.Beh.ProducerBeh.FSMBeh;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import lab4.Datas.OnEnd;
import lab4.Datas.ProducerData;

public class WaitingForDecision extends ParallelBehaviour {
    double neededLoad;
    String distributersName;
    private Behaviour winningAfterDiv;
    private Behaviour winning;
    ProducerData producerData;

    private OnEnd onEnd = new OnEnd();
    public WaitingForDecision(Agent a,long wakeUpTime, String distributersName, ProducerData producerData, double neededLoad) {
        super(a, WHEN_ANY);
        this.distributersName = distributersName;
        this.producerData = producerData;
        this.neededLoad = neededLoad;
        winningAfterDiv = new WaitingForWinAfterDiv(distributersName, producerData, onEnd, neededLoad);
        winning = new WaitingForWin(distributersName, producerData, onEnd, neededLoad);
        addSubBehaviour(winningAfterDiv);
        addSubBehaviour(winning);
        addSubBehaviour(new WakerBehaviour(getAgent(), wakeUpTime) {
            @Override
            protected void onWake() {
                super.onWake();
            }
        });
    }

    @Override
    public int onEnd() {
        if (winningAfterDiv.done()) {
            return 1;
        }
        if(winning.done()){
            return 3;
        }
        else {
            return 2;
        }
    }

}
