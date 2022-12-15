package prAuc;


import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;
import lab4.DfHelper;

import java.util.Date;
import java.util.List;
import java.util.Vector;

public class BuyingAuctionBehaviour extends ContractNetInitiator {
    private int onEnd;
    private ACLMessage bestMsq;
    public BuyingAuctionBehaviour(Agent a) {
        super(a, new ACLMessage(ACLMessage.CFP));
    }

    @Override
    protected Vector prepareCfps(ACLMessage cfp) {
        List<AID> sellers = DfHelper.searchForAgents("seller", myAgent);
        sellers.forEach(cfp::addReceiver);
        cfp.setProtocol("b");
        cfp.setReplyByDate(new Date(System.currentTimeMillis() + 2000));
        return super.prepareCfps(cfp);
    }

    @Override
    protected void handleAllResponses(Vector response, Vector acceptances){

        double bestPrice=0;
        for (Object r : response){
            ACLMessage resp = (ACLMessage) r;
            double offer = Double.parseDouble(resp.getContent());
            if (offer <=0){
                continue;
            }
            if (bestMsq == null || offer < bestPrice){
                bestMsq = resp;
                bestPrice = offer;
            }
        }
        if (bestMsq != null) {
            ACLMessage winnerMsg = bestMsq.createReply();
            winnerMsg.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
            winnerMsg.setContent(bestPrice+"");
            winnerMsg.setReplyByDate(new Date(System.currentTimeMillis()+2000 ));
            acceptances.add(winnerMsg);
        }
        for( Object respons : response){
            if(respons != bestMsq){
                ACLMessage reply = ((ACLMessage)respons).createReply();
                reply.setPerformative(ACLMessage.REJECT_PROPOSAL);

            }
        }
    }

    @Override
    protected void handleAllResultNotifications(Vector resultNotifications) {
        if(resultNotifications.isEmpty()){
            System.out.println("no accept answer");
            onEnd = 1;
        }else{
             ACLMessage resNot = (ACLMessage)resultNotifications.get(0);
             if (resNot.getPerformative()== ACLMessage.INFORM){
                 System.out.println("Auction was successful " + bestMsq.getSender().getLocalName() +" "+ bestMsq.getContent());
             }else{
                 System.out.println("Winner sent failure msg"+ bestMsq.getSender().getLocalName() +" "+ bestMsq.getContent());
             }
        }
    }

    @Override
    public int onEnd() {
        return super.onEnd();
    }
}
