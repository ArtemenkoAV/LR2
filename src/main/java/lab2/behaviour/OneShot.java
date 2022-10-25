package lab2.behaviour;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lr2.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OneShot extends OneShotBehaviour {
    private Random r = new Random();
    List<AID> receivers;


    public OneShot(Agent a, List<AID> receivers) {
        super(a);
        this.receivers = receivers;
    }



    @Override
    public void action() {
        double x = (Math.random()*1); //аргумент функции
        double step = 0.5;  //шаг
        double[] xValues = new double[]{x - step, x, x + step};
        ArrayList<Double> fxAgent1 = new ArrayList<>();
        for (double X : xValues) {
            fxAgent1.add(Function.funAgent1(X));
        }
        System.out.println(fxAgent1);
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        for(AID receiver:receivers){
            System.out.println(receiver);
            msg.addReceiver(receiver);
            msg.setProtocol("xAndDelta");
            msg.setContent(x+" "+step);
            myAgent.send(msg);
        }

    }
}
