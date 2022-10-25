package lec4;

import jade.core.Agent;

public class HelloAgent extends Agent {
    @Override
    protected void setup() {
        System.out.println("Hello");
        this.addBehaviour(new PeriodicPrintingBehaviour(this, 1000));

    }
}
