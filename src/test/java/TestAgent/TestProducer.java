package TestAgent;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import lab4.XMLHelper;
import pr.CfgClasse;
import pr.beh.ReciveTopicName;

public class TestProducer extends Agent {
    @Override
    protected void setup() {
        registration();
        CfgClasse cfg = null;
        cfg = XMLHelper.unMarshalAny(CfgClasse.class, "target/cfg_files/" + getLocalName());
        addBehaviour(new ReciveTopicName(cfg));

    }

    public void registration() {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Production");
        sd.setName(getLocalName());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}
