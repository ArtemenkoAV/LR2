package lr2.agent;

import jade.core.Agent;
import lr2.behaviour.AcceptOfInitiation;
import lr2.behaviour.AcceptRequestAndCalcFunction;
import lr2.behaviour.TransferOfInitiation;

public class FunctionAgent extends Agent {
    double x = Math.random();
    double delta = 0.5;
    @Override
    protected void setup() {

        if (getLocalName().equals("Agent1")){
            addBehaviour(new TransferOfInitiation(this,15000, x, delta));
        }
        addBehaviour((new AcceptRequestAndCalcFunction()));
        addBehaviour((new AcceptOfInitiation()));



    }
}
