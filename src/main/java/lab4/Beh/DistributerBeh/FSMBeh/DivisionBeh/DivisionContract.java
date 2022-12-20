package lab4.Beh.DistributerBeh.FSMBeh.DivisionBeh;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Beh.DistributerBeh.FSMBeh.DistributerParallelBeh;
import lab4.Datas.DistributerData;
import lab4.Datas.PriceForDistributerData;

public class DivisionContract extends OneShotBehaviour {
    DistributerData data;

    public DivisionContract(Agent a, DistributerData data) {
        super(a);
        this.data = data;

    }

    @Override
    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.CFP);
        msg.setContent(String.valueOf(data.getLoad()/2));
        msg.setProtocol("Task");
        msg.addReceiver(data.getTopic());
        System.out.println("New task load "+data.getLoad()/2);
        getAgent().send(msg);
//        getAgent().addBehaviour(new DistributerParallelBeh(getAgent(), pricesForDistributerData, 10000));

    }
}
