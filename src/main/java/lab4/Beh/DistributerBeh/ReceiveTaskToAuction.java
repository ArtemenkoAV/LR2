package lab4.Beh.DistributerBeh;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lab4.Datas.DistributerData;

public class ReceiveTaskToAuction extends Behaviour {
    @Override
    public void action() {

        MessageTemplate task = MessageTemplate.and(MessageTemplate.MatchProtocol("Task"),
                MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        ACLMessage msg = myAgent.receive(task);
        if(msg != null){

            DistributerData data = new DistributerData();
            String[] parseData = msg.getContent().split(",");
            data.setLoad(Double.parseDouble(parseData[0]));
            data.setMaxPrice(Double.parseDouble((parseData[1])));
            System.out.println("I receive Load: "+ data.getLoad() + " and MaxPrice: "+ data.getMaxPrice());
            getAgent().addBehaviour(new DistributerFSM(getAgent(), data));
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
