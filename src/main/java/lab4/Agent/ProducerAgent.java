package lab4.Agent;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import lab4.Beh.ProducerBeh.ReceiveTask;
import lab4.Beh.ProducerBeh.SESParcer;
import lab4.Beh.ProducerBeh.TPPParcer;
import lab4.Beh.ProducerBeh.WESParcer;
import lab4.Config.TPPCfg;
import lab4.Datas.ProducerData;
import lab4.DfHelper;
import lab4.XMLHelper;

import java.util.ArrayList;

public class ProducerAgent extends Agent {
    ProducerData producerData = new ProducerData();
    @Override
    protected void setup() {
        registration();
        if(this.getLocalName().equals("TPP") ){
            addBehaviour(new TPPParcer(this, 75000, producerData));
        }
        if(this.getLocalName().equals("TPP2") ){
            addBehaviour(new TPPParcer(this, 75000, producerData));
        }
        if(this.getLocalName().equals("SES")){
            addBehaviour(new SESParcer(this, 75000, producerData));
        }
        if(this.getLocalName().equals("WES")){
            addBehaviour(new WESParcer(this, 75000, producerData));
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
