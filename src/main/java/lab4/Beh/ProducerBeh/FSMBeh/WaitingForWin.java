package lab4.Beh.ProducerBeh.FSMBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lab4.Datas.OnEnd;
import lab4.Datas.ProducerData;

import java.util.ArrayList;

public class WaitingForWin extends Behaviour {
    String distributersName;
    ProducerData producerData;
    ArrayList<String> winners = new ArrayList<>();
    OnEnd onEnd;
    double neededLoad;
    public WaitingForWin(String distributersName, ProducerData producerData,  OnEnd onEnd, double neededLoad) {
        this.distributersName = distributersName;
        this.producerData = producerData;
        this.onEnd = onEnd;
        this.neededLoad = neededLoad;
    }
    private boolean iWon;



    @Override
    public void action() {
        MessageTemplate msg = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol("Winner"));
        ACLMessage winner = getAgent().receive(msg);
        if (winner != null) {
            producerData.setWinMsg(winner.getContent());
            if (producerData.getProducerLoad().get(getAgent().getAID()) >= neededLoad) {
                producerData.getProducerLoad().put(getAgent().getAID(), producerData.getProducerLoad().get(getAgent().getAID()) - neededLoad);
                ACLMessage confirm = winner.createReply();
//                ACLMessage confirm = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
//                confirm.addReceiver(new AID(distributersName, false));
                confirm.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                confirm.setProtocol("ConfirmSelling");
                confirm.setContent(String.valueOf(producerData.getWinMsg()));
                getAgent().send(confirm);
                iWon=true;
//            if (winner.getSender().getLocalName().equals(distributersName)) {
//
//                onEnd.setOnEnd(1);

//            } else {
//                block();
//            }
            }

        }
        else {
            block();
        }
    }

    @Override
    public boolean done() {


        return iWon;
    }
}
