package lab4.Beh.DistributerBeh.FSMBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lab4.Beh.DistributerBeh.DistributerFSM;
import lab4.Config.DistributerCfg;
import lab4.Datas.DistributerData;
import lab4.Datas.PriceWithNameForDistributerData;
import lab4.XMLHelper;

public class WaitingForConfirm extends Behaviour {
    PriceWithNameForDistributerData bestPrice;
    DistributerData data;

    public WaitingForConfirm(Agent a, PriceWithNameForDistributerData bestPrice, DistributerData data) {
        super(a);
        this.bestPrice = bestPrice;
        this.data = data;
    }

    private int confirm;

    @Override
    public int onEnd() {
        confirm = 1;
        return confirm;
    }

    @Override
    public void action() {
        MessageTemplate informConfirm = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL),
                MessageTemplate.MatchProtocol("ConfirmSelling"));
        ACLMessage congratulations = getAgent().receive( informConfirm );
        if ( congratulations != null) {
            DistributerCfg cfg = XMLHelper.unMarshalAny(DistributerCfg.class, getAgent().getLocalName()+".xml");
            ACLMessage minPrice = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
            minPrice.addReceiver(new AID(cfg.getProducersName(), false));
            minPrice.setProtocol("IBoughtEnergy");
            minPrice.setContent(bestPrice.getName()+"price = " + bestPrice.getPrice());
            getAgent().send(minPrice);
            confirm = 1;
//            getAgent().addBehaviour(new DistributerFSM(getAgent(), data));
//            confirm = true;
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return confirm == 1;
    }
}
