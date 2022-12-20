package lab4.Beh.ProducerBeh;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lab4.Datas.ProducerData;

public class ReceiveTask extends Behaviour {
    ProducerData producerData;
    public ReceiveTask(ProducerData producerData) {
        this.producerData = producerData;
    }

    @Override
    public void action() {
        MessageTemplate msg = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol("topicName"));
        ACLMessage receive = getAgent().receive(msg);
        if(receive!=null){
            getAgent().addBehaviour(new SendPriceForTopic(receive.getContent(), producerData));
        }

        else {
            block();
        }


    }

    @Override
    public boolean done() {
        return false;
    }
}
