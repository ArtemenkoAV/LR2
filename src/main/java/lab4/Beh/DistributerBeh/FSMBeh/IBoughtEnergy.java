package lab4.Beh.DistributerBeh.FSMBeh;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Config.DistributerCfg;
import lab4.Datas.PriceWithNameForDistributerData;
import lab4.XMLHelper;

public class IBoughtEnergy extends OneShotBehaviour {
    PriceWithNameForDistributerData bestPrice;

    public IBoughtEnergy(PriceWithNameForDistributerData bestPrice) {
        this.bestPrice = bestPrice;
    }

    @Override
    public void action() {
        DistributerCfg cfg = XMLHelper.unMarshalAny(DistributerCfg.class, getAgent().getLocalName()+".xml");
        ACLMessage minPrice = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
        minPrice.addReceiver(new AID(cfg.getProducersName(), false));
        minPrice.setProtocol("IBoughtEnergy");
        minPrice.setContent(bestPrice.getName()+": price = " + bestPrice.getPrice());
        getAgent().send(minPrice);

    }
}
