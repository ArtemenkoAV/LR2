package lab4.Beh.DistributerBeh.FSMBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Config.DistributerCfg;
import lab4.Datas.DistributerData;
import lab4.Datas.PriceForDistributerData;
import lab4.Datas.PriceWithNameForDistributerData;
import lab4.XMLHelper;

public class ChoosingBestPrice extends OneShotBehaviour {
    DistributerData data;
    PriceForDistributerData priceForDistributerData;
    PriceWithNameForDistributerData bestPrice;

    public ChoosingBestPrice(Agent a, DistributerData data, PriceForDistributerData priceForDistributerData, PriceWithNameForDistributerData bestPrice) {
        super(a);
        this.data = data;
        this.priceForDistributerData = priceForDistributerData;
        this.bestPrice = bestPrice;
    }

    @Override
    public void action() {
        bestPrice.setPrice(priceForDistributerData.getPricesWithNames().get(priceForDistributerData.getPricesWithNames().size()-1).getPrice());
        bestPrice.setName(priceForDistributerData.getPricesWithNames().get(priceForDistributerData.getPricesWithNames().size()-1).getName());
        if(bestPrice.getPrice()<data.getMaxPrice()){
            ACLMessage win = new ACLMessage(ACLMessage.PROPOSE);
            win.addReceiver(new AID(bestPrice.getName(), false));
            win.setProtocol("Winner");
            getAgent().send(win);}
        else{
            DistributerCfg cfg = XMLHelper.unMarshalAny(DistributerCfg.class, getAgent().getLocalName()+".xml");
            ACLMessage minPrice = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
            minPrice.setContent(String.valueOf(bestPrice.getPrice()));
            minPrice.addReceiver(new AID(cfg.getProducersName(), false));
            minPrice.setProtocol("MaxPriceTooLow");
            getAgent().send(minPrice);
        }

//            for(int i =0 ; i<priceForDistributerData.getPricesWithNames().size(); i++)
//                if(priceForDistributerData.getPricesWithNames().get(i).getName().equals(bestPrice.getName())){
//                    ACLMessage win = new ACLMessage(ACLMessage.PROPOSE);
//                    win.addReceiver(new AID(bestPrice.getName(), false));
//                    win.setProtocol("Winner");
//                    getAgent().send(win);}


    }
}
