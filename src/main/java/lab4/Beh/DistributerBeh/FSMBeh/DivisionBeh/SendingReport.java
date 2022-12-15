package lab4.Beh.DistributerBeh.FSMBeh.DivisionBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Config.DistributerCfg;
import lab4.Datas.DistributerData;
import lab4.XMLHelper;

public class SendingReport extends OneShotBehaviour {
    DistributerData data;

    public SendingReport(Agent a, DistributerData data) {
        super(a);
        this.data = data;
    }

    @Override
    public void action() {
        DistributerCfg cfg = XMLHelper.unMarshalAny(DistributerCfg.class, getAgent().getLocalName() + ".xml");
        ACLMessage minPrice = new ACLMessage(ACLMessage.INFORM);
        minPrice.addReceiver(new AID(cfg.getProducersName(), false));
        minPrice.setProtocol("IBoughtEnergyAfterDivision");
        minPrice.setContent(String.valueOf(data.getBestsPrices()));
        getAgent().send(minPrice);
    }
}
