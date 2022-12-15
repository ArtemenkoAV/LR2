package lab4.Beh.ConsumerBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Config.ConsumerCfg;
import lab4.Datas.ConsumerData;
import lab4.TimeHelper;
import lab4.XMLHelper;

import java.util.List;

public class SendingTaskToAuction extends WakerBehaviour {
    double maxPrice;
    ConsumerData data;

    public SendingTaskToAuction(Agent a, long timeout, ConsumerData data) {
        super(a, timeout);
//        this.maxPrice = maxPrice;
        this.data = data;
    }


    @Override
    protected void onWake() {
        ConsumerCfg consumerCfg = XMLHelper.unMarshalAny(ConsumerCfg.class, getAgent().getLocalName() + ".xml");

        List<Double> load = consumerCfg.getLoad();
        System.out.println(getAgent().getLocalName()+": My current load is "+load.get(TimeHelper.getActualHour())
                +" it's "+TimeHelper.getActualHour()+":00 O'Clock");
        ACLMessage task = new ACLMessage(ACLMessage.REQUEST);
        data.setLoad(load.get(TimeHelper.getActualHour()));
        task.setContent(load.get(TimeHelper.getActualHour()) + ","+ data.getMaxPrice());

        task.setProtocol("Task");
        task.addReceiver(new AID (consumerCfg.getDistributerName(), false));
        getAgent().send(task);


    }
}
