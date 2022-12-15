package prAuc;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetResponder;

public class SellingAuctionBehaviour extends ContractNetResponder {
    private double price;
    public SellingAuctionBehaviour(Agent a, double price) {
        super(a,  MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.CFP),
                MessageTemplate.MatchProtocol("b")));
        this.price = price;
    }

    @Override
    protected ACLMessage handleCfp(ACLMessage cfp){
        System.out.println("I received propose "+ price);
        ACLMessage reply = cfp.createReply();
        reply.setPerformative(ACLMessage.PROPOSE);
        reply.setContent(price+"");
        return reply;

    }

    @Override
    protected void handleRejectProposal(ACLMessage cfp, ACLMessage propose, ACLMessage reject) {
        System.out.println("I lost an auction");

    }

    @Override
    protected ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage propose, ACLMessage accept) throws FailureException {
        ACLMessage reply = accept.createReply();
        if(propose.getContent().equals(accept.getContent())){
            reply.setPerformative(ACLMessage.INFORM);
            System.out.println("I won the auction");
        }else {
            System.out.println("I won the auction, but price inappropriate");
            reply.setPerformative(ACLMessage.FAILURE);
        }
        return reply;
    }
}
