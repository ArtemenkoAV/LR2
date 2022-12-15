package pr.beh;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import pr.dataaaaaaaa;
import lab4.TopicHelper;

import java.util.ArrayList;
import java.util.List;

public class SendTopicName extends OneShotBehaviour {
    dataaaaaaaa data;

    public SendTopicName(dataaaaaaaa data) {
        this.data = data;
    }
    private List<AID> agents;

    @Override
    public void onStart() {
        agents = new ArrayList<>();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Production");
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(getAgent(), dfd);
            for (DFAgentDescription res : result) {
                agents.add(res.getName());
            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        data.setNumberOfProducers(agents.size());
    }

    @Override
    public void action() {
        AID topic = TopicHelper.createTopic(getAgent(), "topic");
        data.setTopic(topic);
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setProtocol("topicName");
        for (AID agent : agents) {
            msg.addReceiver(agent);
        }
        msg.setContent("topic");
        getAgent().send(msg);

    }
}
