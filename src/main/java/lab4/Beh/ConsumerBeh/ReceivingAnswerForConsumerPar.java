package lab4.Beh.ConsumerBeh;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import lab4.Datas.ConsumerData;
import lab4.Datas.OnEnd;

public class ReceivingAnswerForConsumerPar extends ParallelBehaviour {
    ConsumerData consumerData;
    private Behaviour priceTooLow, receiveEnergy, haveNoEnergy, receiveEnergyAfterDivision;
    OnEnd onEnd;
    @Override
    public int onEnd() {
        if (priceTooLow.done()) {
            onEnd.setOnEnd(2);
        } if (receiveEnergy.done()) {
            onEnd.setOnEnd(1);
        } if (haveNoEnergy.done()){
            onEnd.setOnEnd(3);
        } if (receiveEnergyAfterDivision.done()) {
            onEnd.setOnEnd(4);
        }
        return super.onEnd();
    }

    public ReceivingAnswerForConsumerPar(Agent a, ConsumerData consumerData, OnEnd onEnd) {
        super(a, WHEN_ANY);
        this.consumerData = consumerData;
        this.onEnd=onEnd;

        receiveEnergyAfterDivision =new ReceivingBoughtEnergyAfterDivision();
        receiveEnergy = new ReceivingBoughtEnergy();
        priceTooLow = new MyPriceIsTooLow(getAgent(),consumerData);
        haveNoEnergy = new TheyHaveNoEnergy();

        addSubBehaviour(receiveEnergy);
        addSubBehaviour(priceTooLow);
        addSubBehaviour(receiveEnergyAfterDivision);
        addSubBehaviour(haveNoEnergy);
    }
}
