package lab4.Datas;

import jade.core.AID;
import lombok.Data;
import pr.AgentPrice;

import java.util.ArrayList;
import java.util.List;

@Data
public class DistributerData {
    private double maxPrice;
    private double load;
    private AID topic;
    private int numberOfProducers;
    private List<AgentPrice> agentPrices = new ArrayList<>();
    private boolean samePrice;
    private ArrayList<String> bestsPrices = new ArrayList<>();
}
