package lab4.Beh.ProducerBeh.FSMBeh;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lab4.Datas.OnEnd;
import lab4.Datas.ProducerData;

import java.util.ArrayList;

public class WaitingForWinAfterDiv extends Behaviour {
    String distributersName;
    ProducerData producerData;
    ArrayList<String> winners = new ArrayList<>();
    OnEnd onEnd;
    double neededLoad;
    public WaitingForWinAfterDiv(String distributersName, ProducerData producerData,  OnEnd onEnd, double neededLoad) {
        this.distributersName = distributersName;
        this.producerData = producerData;
        this.onEnd = onEnd;
        this.neededLoad = neededLoad;
    }
    private boolean iWon;



    @Override
    public void action() {
        MessageTemplate msg = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.PROPOSE),
                MessageTemplate.MatchProtocol("WinnerAfterDiv"));
        ACLMessage winner = getAgent().receive(msg);
        if (winner != null) {
            if (winner.getSender().getLocalName().equals(distributersName)) {

                producerData.setWinMsg(winner.getContent());
                iWon= true;
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
