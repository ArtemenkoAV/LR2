package lab4.Beh.DistributerBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import lab4.Config.ConsumerCfg;
import lab4.Config.DistributerCfg;
import lab4.Datas.DistributerData;
import lab4.TopicHelper;
import lab4.XMLHelper;

import java.util.ArrayList;
import java.util.List;

public class SendTopicNameForProducer extends OneShotBehaviour {
    DistributerData data;
    DistributerCfg distributerCfg = XMLHelper.unMarshalAny(DistributerCfg.class, getAgent().getLocalName() + ".xml");
    public SendTopicNameForProducer(Agent a, DistributerData data) {
        super(a);
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
        AID topic = TopicHelper.createTopic(getAgent(), distributerCfg.getTopicName());
        data.setTopic(topic);
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setProtocol("topicName");
        for (AID agent : agents) {
            msg.addReceiver(agent);
        }
        msg.setContent(distributerCfg.getTopicName());
        getAgent().send(msg);

    }
}
