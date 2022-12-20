package lab4;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class TestAgent extends Agent {
    @Override
    protected void setup() {

        System.out.println(this.getLocalName()+" was born ");
        Object[] arguments = getArguments();
        for (Object argument : arguments) {
            addBehaviour((Behaviour) argument);
        }
    }
}
