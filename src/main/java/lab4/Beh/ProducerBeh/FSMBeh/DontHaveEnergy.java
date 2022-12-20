package lab4.Beh.ProducerBeh.FSMBeh;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;

public class DontHaveEnergy extends OneShotBehaviour {
    String distributersName;

    public DontHaveEnergy(String distributersName) {
        this.distributersName = distributersName;
    }

    @Override
    public void action() {
        System.out.println(getAgent().getLocalName()+" doesn't have enough energy");

    }
}
