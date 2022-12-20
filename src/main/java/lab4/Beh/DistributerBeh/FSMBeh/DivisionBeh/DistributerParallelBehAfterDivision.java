package lab4.Beh.DistributerBeh.FSMBeh.DivisionBeh;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import lab4.Beh.DistributerBeh.FSMBeh.ReceivingPricesFromProducer;
import lab4.Datas.OnEnd;
import lab4.Datas.PriceForDistributerData;

public class DistributerParallelBehAfterDivision extends ParallelBehaviour {
    PriceForDistributerData pricesForDistributerData;
    private OnEnd onEnd = new OnEnd();

    public DistributerParallelBehAfterDivision(Agent a, PriceForDistributerData pricesForDistributerData,
                                               long wakeUpTime) {
        super(a, WHEN_ANY);
        this.pricesForDistributerData = pricesForDistributerData;
        addSubBehaviour(new ReceivingPricesFromProducer(getAgent(), pricesForDistributerData, onEnd));
        addSubBehaviour(new WakerBehaviour(getAgent(), wakeUpTime) {
            @Override
            protected void onWake() {
//                System.out.println(getAgent().getLocalName()+" ended his trade, best price " + pricesForDistributerData.getPricesWithNames().get(pricesForDistributerData.getPricesWithNames().size()-1).getPrice());
//                System.out.println("Name best Producer: " + pricesForDistributerData.getPricesWithNames().get(pricesForDistributerData.getPricesWithNames().size()-1).getName());
                super.onWake();
            }
        });
    }
    @Override
    public int onEnd() {
        return onEnd.getOnEnd();
    }
}
