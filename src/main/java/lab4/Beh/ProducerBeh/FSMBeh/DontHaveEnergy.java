package lab4.Beh.ProducerBeh.FSMBeh;

import jade.core.behaviours.OneShotBehaviour;

public class DontHaveEnergy extends OneShotBehaviour {
    @Override
    public void action() {
        System.out.println(getAgent().getLocalName()+" doesn't have enough energy");
    }
}
