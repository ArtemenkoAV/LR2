package lab4.Datas;


import jade.core.AID;
import pr.AgentPrice;

import java.util.ArrayList;
import java.util.List;


public class Data {
    private AID topic;
    private int numberOfProducers;
    private List<AgentPrice> agentPrices = new ArrayList<>();
    private boolean samePrice;

    public AID getTopic() {
        return topic;
    }

    public void setTopic(AID topic) {
        this.topic = topic;
    }

    public int getNumberOfProducers() {
        return numberOfProducers;
    }

    public void setNumberOfProducers(int numberOfProducers) {
        this.numberOfProducers = numberOfProducers;
    }

    public List<AgentPrice> getAgentPrices() {
        return agentPrices;
    }

    public void setAgentPrices(List<AgentPrice> agentPrices) {
        this.agentPrices = agentPrices;
    }

    public boolean isSamePrice() {
        return samePrice;
    }

    public void setSamePrice(boolean samePrice) {
        this.samePrice = samePrice;
    }
}
