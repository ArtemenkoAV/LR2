package pr;

import lombok.Data;

@Data
public class AgentPrice {
    private int price;
    private String agentName;

    public AgentPrice(int price, String agentName) {
        this.price = price;
        this.agentName = agentName;
    }
}
