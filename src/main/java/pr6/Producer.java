package pr6;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Producer extends Agent {
    @Override
    protected void setup() {
        registration();
        CfgClass cfg = null;
        String cfgName = null;
        switch (getLocalName()) {
            case "Producer1":
                cfgName = "Cfg1";
                break;
            case "Producer2":
                cfgName = "Cfg2";
                break;
        }
        try {
            JAXBContext context = JAXBContext.newInstance(CfgClass.class);Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            cfg = (CfgClass) jaxbUnmarshaller.unmarshal(new
                    File("src/main/resources/" + cfgName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
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
