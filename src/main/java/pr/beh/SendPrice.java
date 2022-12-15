package pr.beh;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import pr.CfgClasse;
import lab4.TopicHelper;

public class SendPrice extends Behaviour {
    String topicName;
    String content;
    AID topic;
    CfgClasse cfg;
    boolean finish;
    private MessageTemplate mtt = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.PROPOSE),
            MessageTemplate.MatchProtocol("quantity"));
    public SendPrice(String topicName, CfgClasse cfg) {
        this.topicName = topicName;
        this.cfg = cfg;
    }

    @Override
    public void action() {
        topic = TopicHelper.createTopic(getAgent(), topicName);
        ACLMessage receive = getAgent().receive(mtt);
        if(receive !=null){
            System.out.println(getAgent().getLocalName() + ": " + receive.getContent());
            content = receive.getContent();
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setProtocol("price");
            int price = cfg.getA()*Integer.parseInt(content) + cfg.getB();
            System.out.println(getAgent().getLocalName() + " price: " + price);
            msg.setContent(String.valueOf(price));
            msg.addReceiver(topic);
            getAgent().send(msg);



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
