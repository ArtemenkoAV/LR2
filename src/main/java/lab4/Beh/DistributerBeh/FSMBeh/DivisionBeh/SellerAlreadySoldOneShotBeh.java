package lab4.Beh.DistributerBeh.FSMBeh.DivisionBeh;


import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import lab4.Beh.DistributerBeh.DistributerFSM;
import lab4.Datas.DistributerData;

public class SellerAlreadySoldOneShotBeh extends OneShotBehaviour {
    DistributerData data;

    public SellerAlreadySoldOneShotBeh(Agent a, DistributerData data) {
        super(a);
        this.data = data;
    }

    @Override
    public void action() {
        getAgent().addBehaviour(new DistributerFSM(getAgent(), data));
    }
}
