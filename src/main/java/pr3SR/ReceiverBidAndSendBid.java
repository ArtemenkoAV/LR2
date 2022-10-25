package pr3SR;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReceiverBidAndSendBid extends Behaviour {
    private MessageTemplate mt = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.CFP),
            MessageTemplate.MatchProtocol("Seller"));
    @Override
    public void action() {
        ACLMessage buyer = myAgent.receive(mt);
        if (buyer!= null){
            System.out.println("I reciv "+buyer.getSender()+ " " + buyer.getContent());

            ACLMessage seller =buyer.createReply();
            double Amount = Double.parseDouble(String.valueOf(buyer.getContent()));
            final int min = (int) Amount; // Минимальное число для диапазона
            final int max = (int) (Amount*6); // Максимальное число для диапазона
            final int rnd = rnd(min, max);
            seller.setPerformative(ACLMessage.PROPOSE);
            seller.setContent(String.valueOf(rnd));
            seller.setProtocol("Buyer");
            myAgent.send(seller);
            myAgent.addBehaviour(new WInOrLose());
        }
        else{
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
