package lab2;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import lab2.behaviour.Beh;
import lab2.behaviour.CalculationFunctionValue;
import lab2.behaviour.OneShot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FunctionAgent extends Agent {
    List<AID> receivers = new ArrayList<>();


    @Override
    protected void setup() {
        addBehaviour(new CalculationFunctionValue());



        if (getLocalName().equals("Agent1")) {
            receivers.add(new AID("Agent2", false));
            receivers.add(new AID("Agent3", false));
            addBehaviour(new OneShot(this,  receivers));

        }

    }
//    public void ACL1(double x, double step){
//        int main_random;
//        main_random = r.nextInt(AGENTS.size());
//        System.out.println("Передача мейна, теперь мейн : " + AGENTS.get(main_random));
//        ACLMessage ACL1 = new ACLMessage(ACLMessage.INFORM);
//        ACL1.addReceiver(new AID(AGENTS.get(main_random), AID.ISLOCALNAME));
//        ACL1.setProtocol("You main");
//        ACL1.setContent(x + " " + step);
//
//        //создаем строку всех агентов для отправки
//        String Agent_str = "";
//        for (String agent : AGENTS){
//            if (Agent_str.equals("")){
//                Agent_str = agent;
//            }
//            else {
//                Agent_str = Agent_str.concat(" "+ agent);//concat - складывает строку (Agent_str) со строкой (" "+ agent)
//            }
//        }
//        ACL1.setOntology(Agent_str);
//        send(ACL1);  //отправляет ему сообщение
//    }




}
