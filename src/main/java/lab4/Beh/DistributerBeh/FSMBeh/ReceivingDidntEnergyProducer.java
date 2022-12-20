package lab4.Beh.DistributerBeh.FSMBeh;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lab4.Datas.OnEnd;
import lab4.Datas.PriceWithNameForDistributerData;

import java.util.ArrayList;

public class ReceivingDidntEnergyProducer extends Behaviour {
    OnEnd onEnd;
    boolean finish = false;

    public ReceivingDidntEnergyProducer(Agent a, OnEnd onEnd) {
        super(a);
        this.onEnd = onEnd;
    }

    @Override
    public void action() {
        MessageTemplate price = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol("DidnEnergy"));
        ACLMessage priceFromProducer = getAgent().receive(price);
        if (priceFromProducer != null) {
            onEnd.setOnEnd(3);

        }
    }

        @Override
        public boolean done () {
            return finish;
    }
}

