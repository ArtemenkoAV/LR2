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
    private boolean confirm;

    @Override
    public void action() {
        MessageTemplate informConfirm = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL),
                MessageTemplate.MatchProtocol("ConfirmSelling"));
        ACLMessage congratulations = getAgent().receive( informConfirm );
        if ( congratulations != null) {
            confirm = true;
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return confirm;
    }

}
