package pr;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import lab4.XMLHelper;
import pr.beh.ReciveTopicName;

public class Producer extends Agent {
    @Override
    protected void setup() {
        registration();
        CfgClasse cfg = null;
        String cfgName = null;
        switch (getLocalName()){
            case "Producer1":
                cfgName="newCfg1.xml";
                break;
            case "Producer2":
                cfgName="newCfg2.xml";
                break;
        }
        cfg = XMLHelper.unMarshalAny(CfgClasse.class, "src/main/resources/" + cfgName);
        addBehaviour(new ReciveTopicName(cfg));
    }
    public void registration(){
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
