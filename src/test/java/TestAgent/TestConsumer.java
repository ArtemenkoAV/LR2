package TestAgent;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class TestConsumer extends Agent {
    @Override
    protected void setup() {
        Object[] arguments = getArguments();
        for (Object argument : arguments) {
            addBehaviour((Behaviour) argument);
        }
    }
}
