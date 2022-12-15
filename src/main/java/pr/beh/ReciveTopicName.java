package pr.beh;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import pr.CfgClasse;

public class ReciveTopicName extends Behaviour {
    CfgClasse cfg;
    private MessageTemplate mt = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
            MessageTemplate.MatchProtocol("topicName"));
    public ReciveTopicName(CfgClasse cfg) {
        this.cfg = cfg;
    }

    @Override
    public void action() {

        ACLMessage receive = getAgent().receive(mt);
        if(receive!=null){
            System.out.println(getAgent().getLocalName() + " " +
                    receive.getContent());
            getAgent().addBehaviour(new SendPrice(receive.getContent(), cfg));

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
