package lr2.behaviour;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TransferOfInitiation extends WakerBehaviour {
    private List<String> agents = Arrays.asList("Agent1", "Agent2", "Agent3");
    private Random r = new Random();
    double x;
    double delta;

    public TransferOfInitiation(Agent a,long time, double x, double delta) {
        super(a, time);
        this.x = x;
        this.delta = delta;
    }


    @Override
    protected void onWake() {
        int initiator;
        System.out.println("Begin X " + x);
        System.out.println("Begin X " + delta);
        initiator = r.nextInt(agents.size());

        while (agents.get(initiator).equals(myAgent.getLocalName())){
            initiator = r.nextInt(agents.size());
        }
//        System.out.println("This initiator " + agents.get(initiator));
        ACLMessage transInit = new ACLMessage(ACLMessage.INFORM);
        transInit.setProtocol("Initiator");
        transInit.setContent(x + " " + delta);
        transInit.addReceiver(new AID(agents.get(initiator), false));
        myAgent.send(transInit);


    }

//    @Override
//    public void action() {
//        int initiator;
//        System.out.println("Begin X " + x);
//        System.out.println("Begin X " + delta);
//        initiator = r.nextInt(agents.size());
//
////        while (agents.get(initiator).equals(myAgent.getLocalName())){
////            initiator = r.nextInt(agents.size());
////        }
////        System.out.println("This initiator " + agents.get(initiator));
//        ACLMessage transInit = new ACLMessage(ACLMessage.INFORM);
//        transInit.setProtocol("Initiator");
//        transInit.setContent(x + " " + delta);
//        transInit.addReceiver(new AID(agents.get(initiator), false));
//        myAgent.send(transInit);
//    }
}
