package lab4.Beh.DistributerBeh.FSMBeh.DivisionBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Config.DistributerCfg;
import lab4.Datas.PriceForDistributerData;
import lab4.XMLHelper;

public class MinPriceTooLargeAfterDivision extends OneShotBehaviour {
    PriceForDistributerData priceForDistributerData;

    public MinPriceTooLargeAfterDivision(Agent a, PriceForDistributerData priceForDistributerData) {
        super(a);
        this.priceForDistributerData = priceForDistributerData;
    }

    @Override
    public void action() {
        DistributerCfg cfg = XMLHelper.unMarshalAny(DistributerCfg.class, getAgent().getLocalName()+".xml");
        ACLMessage minPrice = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
        minPrice.setContent(String.valueOf(priceForDistributerData.getMinPrice()));
        minPrice.addReceiver(new AID(cfg.getProducersName(), false));
        minPrice.setProtocol("MaxPriceTooLow");
        getAgent().send(minPrice);

    }
}
