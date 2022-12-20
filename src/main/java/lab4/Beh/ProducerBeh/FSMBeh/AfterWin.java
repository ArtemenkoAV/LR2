package lab4.Beh.ProducerBeh.FSMBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Datas.ProducerData;

public class AfterWin extends OneShotBehaviour {
    String distributersName;
    double neededLoad;
    ProducerData producerData;

    public AfterWin(Agent a,String distributersName, double neededLoad, ProducerData producerData) {
        super(a);
        this.distributersName = distributersName;
        this.neededLoad = neededLoad;
        this.producerData = producerData;
    }

    @Override
    public void action() {

        if (producerData.getProducerLoad().get(getAgent().getAID()) >= neededLoad){
            producerData.getProducerLoad().put(getAgent().getAID(), producerData.getProducerLoad()
                    .get(getAgent().getAID()) - neededLoad);
            ACLMessage confirm = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
            confirm.addReceiver(new AID(distributersName, false));
            confirm.setProtocol("ConfirmSelling");
            confirm.setContent(String.valueOf(producerData.getWinMsg()));
            getAgent().send(confirm);
            System.out.println(getAgent().getLocalName()+" sold energy to "+distributersName);
        } else {
            System.out.println(getAgent().getLocalName()+" already sold all his energy");
        }


    }
}
