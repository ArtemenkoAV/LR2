package lab4.Datas;

import jade.core.AID;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class ProducerData {
    private HashMap<AID, Double> producerLoad = new HashMap<>();
    private String winMsg;

}
