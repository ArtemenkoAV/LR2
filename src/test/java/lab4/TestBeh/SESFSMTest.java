package lab4.TestBeh;

import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import lab4.Beh.ProducerBeh.SESParcer;
import lab4.Datas.ProducerData;
import lab4.DfHelper;

import java.util.ArrayList;

public class SESFSMTest extends FSMBehaviour {
    ProducerData producerData;

    public SESFSMTest(ProducerData producerData) {
        this.producerData = producerData;

        registerFirstState(new OneShotBehaviour() {
            @Override
            public void action() {
                ArrayList<String> AgentsInAllTrade = new ArrayList<>();
                AgentsInAllTrade.add("Production");
                DfHelper.registerItself(AgentsInAllTrade, getAgent());
            }
        }, "Registr");
        registerLastState(new SESParcer(getAgent(),20000, producerData), "Parcing");
        registerDefaultTransition("Registr", "Parcing");
    }

}
