package TestAgent;

import jade.core.behaviours.Behaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import java.util.List;

public class Test {
    private AgentContainer mainContainer;
    public void startJade(List<String> services){

        ProfileImpl profileIMPL = new ProfileImpl();
        profileIMPL.setParameter("gui", "true");
        StringBuilder sb = new StringBuilder();
        for (String service : services) {
            sb.append(service).append(";");
        }
        profileIMPL.setParameter("services", sb.toString());
        Runtime.instance().setCloseVM(true);

        mainContainer = Runtime.instance().createMainContainer(profileIMPL);
    }
    public void createConsumerAgent(String name, Behaviour... bhs){
        try {
            AgentController newAgent = mainContainer.createNewAgent(name,
                    TestConsumer.class.getName(), bhs);
            newAgent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
    public void createProducerAgent(String name, Behaviour... bhs){
        try {
            AgentController newAgent = mainContainer.createNewAgent(name,
                    TestProducer.class.getName(), bhs);
            newAgent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
