package lab4.TestBeh;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import lab4.Beh.ProducerBeh.TPPParcer;
import lab4.Datas.ProducerData;
import lab4.DfHelper;

import java.util.ArrayList;

public class TPPFSMTest extends FSMBehaviour {
    ProducerData producerData;

    public TPPFSMTest(ProducerData producerData) {
        this.producerData = producerData;

        registerFirstState(new OneShotBehaviour() {
            @Override
            public void action() {
                ArrayList<String> AgentsInAllTrade = new ArrayList<>();
                AgentsInAllTrade.add("Production");
                DfHelper.registerItself(AgentsInAllTrade, getAgent());
            }
        }, "Registr");
        registerLastState(new TPPParcer(getAgent(),20000, producerData), "Parcing");
        registerDefaultTransition("Registr", "Parcing");
    }

}
