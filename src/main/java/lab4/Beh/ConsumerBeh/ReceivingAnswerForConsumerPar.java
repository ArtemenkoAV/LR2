package lab4.Beh.ConsumerBeh;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import lab4.Datas.ConsumerData;
import lab4.Datas.OnEnd;

public class ReceivingAnswerForConsumerPar extends ParallelBehaviour {
    ConsumerData consumerData;
    private Behaviour priceTooLow, receiveEnergy, haveNoEnergy, receiveEnergyAfterDivision;
    OnEnd onEnded;
    @Override
    public int onEnd() {
        if (priceTooLow.done()) {
            onEnded.setOnEnd(2);
        } if (receiveEnergy.done()) {
            onEnded.setOnEnd(1);
        } if (haveNoEnergy.done()){
            onEnded.setOnEnd(3);
        } if (receiveEnergyAfterDivision.done()) {
            onEnded.setOnEnd(4);
        }
        return super.onEnd();
    }

    public ReceivingAnswerForConsumerPar(Agent a, ConsumerData consumerData, OnEnd onEnded) {
        super(a, WHEN_ANY);
        this.consumerData = consumerData;
        this.onEnded=onEnded;

        receiveEnergyAfterDivision =new ReceivingBoughtEnergyAfterDivision(consumerData);
        receiveEnergy = new ReceivingBoughtEnergy(consumerData);
        priceTooLow = new MyPriceIsTooLow(getAgent(),consumerData);
        haveNoEnergy = new TheyHaveNoEnergy(getAgent());

        addSubBehaviour(receiveEnergy);
        addSubBehaviour(priceTooLow);
        addSubBehaviour(haveNoEnergy);
        addSubBehaviour(receiveEnergyAfterDivision);
//        addSubBehaviour(new WakerBehaviour(getAgent(), 60000) {
//            @Override
//            protected void onWake() {
//                System.out.println(getAgent().getLocalName()+": They don't have any energy for me");
//            }
//        }
//        );

    }
}
