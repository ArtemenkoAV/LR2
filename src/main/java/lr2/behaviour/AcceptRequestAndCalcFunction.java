package lr2.behaviour;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lr2.Function;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AcceptRequestAndCalcFunction extends Behaviour {
    double x;
    double delta;
    private MessageTemplate mt = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
            MessageTemplate.MatchProtocol("CalcFunction"));
    private Random r = new Random();
    boolean finish;



    @Override
    public void action() {

        ACLMessage xAndDelta = myAgent.receive(mt);
        if(xAndDelta!=null){
//            System.out.println(xAndDelta.getContent());
            ArrayList<String> xDelta = new ArrayList<>(Arrays.asList(xAndDelta.getContent().split(" ")));//{ x, step}
            x = Double.parseDouble(xDelta.get(0));
            delta = Double.parseDouble(xDelta.get(1));
            double[] xValue = new double[]{x - delta, x, x + delta};
            ArrayList<Double> fX = new ArrayList<>();
//            System.out.println(myAgent.getLocalName());
            for (double X : xValue) {
                switch (myAgent.getLocalName()) {
                    case "Agent1":
                        fX.add(Function.funAgent1(X));
                        break;
                    case "Agent2":
                        fX.add(Function.funAgent2(X));
                        break;
                    case "Agent3":
                        fX.add(Function.funAgent3(X));
                        break;
                }
            }
//            System.out.println("Value function " + myAgent.getLocalName() + " : " + fX);
            ACLMessage function = new ACLMessage(ACLMessage.REQUEST);
            function.setProtocol("Function");
            function.setContent(fX.get(0) + " " + fX.get(1) + " " + fX.get(2));
            function.addReceiver(new AID(xAndDelta.getSender().getLocalName(), false));
            myAgent.send(function);




        }
        else {
            block();
        }


    }

    @Override
    public boolean done() {
        return finish;
    }
}
