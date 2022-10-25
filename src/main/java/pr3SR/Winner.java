package pr3SR;

import jade.lang.acl.ACLMessage;

import java.util.List;

public class Winner {
    List<ACLMessage> messages;
    double maxPrice = 0;
    ACLMessage winerMessages;

    public Winner(List<ACLMessage> messages) {
        this.messages = messages;
    }

    public ACLMessage win(){
        for (ACLMessage message:messages){
            double prices = Double.parseDouble(message.getContent());
            if(maxPrice==0){
                maxPrice =prices;
                winerMessages =message;
            }
            else if(maxPrice<prices){
                maxPrice = prices;
                winerMessages=message;
            }
        }
        return winerMessages;
    }
}
