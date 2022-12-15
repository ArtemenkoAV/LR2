package lab4.Beh.ProducerBeh.FSMBeh;

import jade.core.behaviours.OneShotBehaviour;

public class DidntSellEnergy extends OneShotBehaviour {
    @Override
    public void action() {
        System.out.println(getAgent().getLocalName()+" i didn't sell anything");
    }
}
