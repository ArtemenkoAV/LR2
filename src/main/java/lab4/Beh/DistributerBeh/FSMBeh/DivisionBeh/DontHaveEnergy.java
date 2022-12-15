package lab4.Beh.DistributerBeh.FSMBeh.DivisionBeh;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Config.DistributerCfg;
import lab4.XMLHelper;

public class DontHaveEnergy extends OneShotBehaviour {
    @Override
    public void action() {
        DistributerCfg cfg = XMLHelper.unMarshalAny(DistributerCfg.class, getAgent().getLocalName()+".xml");
        ACLMessage minPrice = new ACLMessage(ACLMessage.INFORM);
        minPrice.addReceiver(new AID(cfg.getProducersName(), false));
        minPrice.setProtocol("DontHaveEnergy");
        getAgent().send(minPrice);
        System.out.println(getAgent().getLocalName()+" didn't buy any energy for "+cfg.getProducersName()+" because seller don't have enough energy");
    }

}
