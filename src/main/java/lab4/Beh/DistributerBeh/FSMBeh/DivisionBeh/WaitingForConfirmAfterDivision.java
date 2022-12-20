package lab4.Beh.DistributerBeh.FSMBeh.DivisionBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lab4.Config.DistributerCfg;
import lab4.Datas.DistributerData;
import lab4.Datas.OnEnd;
import lab4.Datas.PriceWithNameForDistributerData;
import lab4.XMLHelper;

import java.util.ArrayList;

public class WaitingForConfirmAfterDivision extends Behaviour {
    OnEnd onEnd;
    DistributerData data;





    public WaitingForConfirmAfterDivision(Agent a, DistributerData data, OnEnd onEnd) {
        super(a);
        this.data = data;
        this.onEnd = onEnd;
    }


    @Override
    public void action() {
        MessageTemplate informConfirm = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL),
                MessageTemplate.MatchProtocol("ConfirmSelling"));
        ACLMessage congratulations = getAgent().receive(informConfirm);
        if (congratulations != null) {
            data.getBestsPrices().add(congratulations.getContent());
            onEnd.setOnEnd(1);
//            DistributerCfg cfg = XMLHelper.unMarshalAny(DistributerCfg.class, getAgent().getLocalName() + ".xml");
//            ACLMessage minPrice = new ACLMessage(ACLMessage.INFORM);
//            minPrice.addReceiver(new AID(cfg.getProducersName(), false));
//            minPrice.setProtocol("IBoughtEnergyAfterDivision");
//            minPrice.setContent(congratulations.getContent());
//            getAgent().send(minPrice);
        }
        else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
