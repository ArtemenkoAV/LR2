package lab4.Agent;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import lab4.Beh.ProducerBeh.ReceiveTask;
import lab4.Config.TPPCfg;
import lab4.Datas.ProducerData;
import lab4.XMLHelper;

public class ProducerAgent extends Agent {
    ProducerData producerData = new ProducerData();
    @Override
    protected void setup() {
        registration();
        String cfgName = null;
        switch (getLocalName()){
            case "TPP":
            case "TPP2":
                TPPCfg tppCfg = XMLHelper.unMarshalAny(TPPCfg.class, getLocalName() + ".xml");
                producerData.getProducerLoad().put(getAID(),tppCfg.getA());
//                System.out.println(getLocalName()+" storage is "+producerData.getProducerLoad().get(getAID()));

                break;
        }
        addBehaviour(new ReceiveTask(producerData));


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
