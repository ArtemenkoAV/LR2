package lab4.Beh.DistributerBeh.FSMBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Config.DistributerCfg;
import lab4.Datas.PriceWithNameForDistributerData;
import lab4.XMLHelper;

public class MinPriceTooLarge extends OneShotBehaviour {
    PriceWithNameForDistributerData bestPrice;

    public MinPriceTooLarge(Agent a, PriceWithNameForDistributerData bestPrice) {
        super(a);
        this.bestPrice = bestPrice;
    }

    @Override
    public void action() {
        DistributerCfg cfg = XMLHelper.unMarshalAny(DistributerCfg.class, getAgent().getLocalName()+".xml");
        ACLMessage minPrice = new ACLMessage(ACLMessage.INFORM);
        minPrice.setContent(String.valueOf(bestPrice.getPrice()));
        minPrice.addReceiver(new AID(cfg.getProducersName(), false));
        minPrice.setProtocol("MaxPriceTooLow");
        getAgent().send(minPrice);

    }
}
