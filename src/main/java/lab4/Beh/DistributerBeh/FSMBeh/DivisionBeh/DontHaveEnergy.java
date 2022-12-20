package lab4.Beh.DistributerBeh.FSMBeh.DivisionBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Config.DistributerCfg;
import lab4.XMLHelper;

public class DontHaveEnergy extends OneShotBehaviour {
    public DontHaveEnergy(Agent a) {
        super(a);
    }

    @Override
    public void action() {
        DistributerCfg cfg = XMLHelper.unMarshalAny(DistributerCfg.class, getAgent().getLocalName()+".xml");
        ACLMessage noEnergy = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
        noEnergy.setProtocol("noEnergy");
        noEnergy.addReceiver(new AID(cfg.getProducersName(), false));
        getAgent().send(noEnergy);
        System.out.println(getAgent().getLocalName()+" didn't buy any energy for "+cfg.getProducersName()+" because seller don't have enough energy");
    }

}
