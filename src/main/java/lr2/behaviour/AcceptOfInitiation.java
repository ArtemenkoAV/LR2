package lr2.behaviour;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lr2.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AcceptOfInitiation extends Behaviour {
    private List<String> agents = Arrays.asList("Agent1", "Agent2", "Agent3");
    List<AID> receivers = new ArrayList<>();
    boolean finish;
    double x;
    double delta;
    private ArrayList<Double> fXAgent1 = new ArrayList<>(),
            fXAgent2 = new ArrayList<>(),
            fXAgent3 = new ArrayList<>();
    private MessageTemplate mt = MessageTemplate.or(
            MessageTemplate.MatchProtocol("Function"),
            MessageTemplate.MatchProtocol("Initiator"));
    private Random r = new Random();

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null){
            if (msg.getProtocol().equals("Initiator")){
                ArrayList<String> xDelta = new ArrayList<>(Arrays.asList(msg.getContent().split(" ")));
                x = Double.parseDouble(xDelta .get(0));
                delta = Double.parseDouble(xDelta .get(1));
                double[] xValue = new double[]{x - delta, x, x + delta};
                System.out.println("I am initiator "+myAgent.getLocalName());
                for (double X : xValue) {
                    switch (myAgent.getLocalName()) {
                        case "Agent1":
                            fXAgent1.add(Function.funAgent1(X));
                            break;
                        case "Agent2":
                            fXAgent2.add(Function.funAgent2(X));
                            break;
                        case "Agent3":
                            fXAgent3.add(Function.funAgent3(X));
                            break;
                    }
                }
                for (String agent : agents) {
                    if (!myAgent.getLocalName().equals(agent)) {
                        receivers.add(new AID(agent, false));
                    }
                }
//                System.out.println(receivers);
                myAgent.addBehaviour(new SendXDelta(myAgent,1000, receivers,x,delta));

//                for(AID receiver:receivers) {
////                    System.out.println(receiver.getLocalName());
//                    ACLMessage transInit = new ACLMessage(ACLMessage.REQUEST);
//                    transInit.setProtocol("CalcFunction");
//                    transInit.setContent(x + " " + delta);
//                    transInit.addReceiver(receiver);
//                    myAgent.send(transInit);
//                }
            }

            if (msg.getProtocol().equals("Function")) {
//                System.out.println("Value function sender " + msg.getSender().getLocalName() + " : " + msg.getContent());
                switch (msg.getSender().getLocalName()) {
                    case "Agent1":
                        for (String Fx1 : msg.getContent().split(" ")) {
                            fXAgent1.add(Double.parseDouble(Fx1));
                        }
                        break;
                    case "Agent2":
                        for (String Fx2 : msg.getContent().split(" ")) {
                            fXAgent2.add(Double.parseDouble(Fx2));
                        }
                        break;
                    case "Agent3":
                        for (String Fx3 : msg.getContent().split(" ")) {
                            fXAgent3.add(Double.parseDouble(Fx3));
                        }
                        break;
                }

            }

            if (fXAgent1.size() == 3 && fXAgent2.size() == 3 && fXAgent3.size() == 3) {
                ArrayList<Double> func = new ArrayList<>();// Формирование суммарного массива;
                for (int i = 0; i < fXAgent1.size(); i++) {  // суммирует отдельно значения функций от { x - step, x, x + step}
                    func.add(fXAgent1.get(i) + fXAgent2.get(i) + fXAgent3.get(i));
                }
//                System.out.println("a1 " + fXAgent1);
//                System.out.println("a2 " + fXAgent2);
//                System.out.println("a3 " + fXAgent3);
                fXAgent1.clear();
                fXAgent2.clear();
                fXAgent3.clear();
                receivers.clear();

                // Если достаточная точность расчетов достигнута, то завершаем поведение
                if (delta < 0.01) {
                    System.out.println("Max value function: " + String.format("%.4f", func.get(1)));
                    System.out.println("X   : " + String.format("%.4f", x));
                    System.out.println("Delta: " + String.format("%.4f", delta));
                    finish = true;
                } else {
                    double maxFx = func.get(0);
                    int idMax = func.indexOf(maxFx); //Возвращает индекс первого вхождения указанного элемента
                    for (int i = 0; i < func.size(); i++) {
                        if (func.get(i) > maxFx) {
                            maxFx = func.get(i);
                            idMax = i;
                        }
                    }
                    switch (idMax) {
                        case 0:
                            x = x - delta;
                            break;
                        case 1:
                            delta = delta / 2;
                            break;
                        case 2:
                            x = x + delta;
                            break;
                    }
//                    System.out.println(idMax);
                    System.out.println("Intermediate value of the function: " + String.format("%.4f", func.get(idMax)));
                    System.out.println("X   : " + String.format("%.4f", x));
                    System.out.println("Delta: " + String.format("%.4f", delta));

                    myAgent.addBehaviour(new TransferOfInitiation(myAgent,1000, x, delta));

                }
            }

        }
        else{
            block();
        }
    }
    @Override
    public boolean done() {
        return finish;
    }
}
