package lab4.Beh.ProducerBeh;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lab4.Datas.ProducerData;
import lab4.TopicHelper;

public class SendPriceForTopic extends Behaviour {
    String topicName;
    String content;
    AID topic;
    ProducerData producerData;

    public SendPriceForTopic(String topicName, ProducerData producerData) {
        this.topicName=topicName;
        this.producerData = producerData;
    }

    @Override
    public void action() {
        MessageTemplate mtt = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.PROPOSE),
                MessageTemplate.MatchProtocol("Task"));
        topic = TopicHelper.createTopic(getAgent(), topicName);
        ACLMessage receive = getAgent().receive(mtt);
        if(receive !=null) {
            double neededLoad = Double.parseDouble(receive.getContent());
            getAgent().addBehaviour(new ProducerFSM(getAgent(), receive.getSender().getLocalName(), neededLoad, producerData, topic));
            System.out.println(getAgent().getLocalName() + ": " + receive.getContent());
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
