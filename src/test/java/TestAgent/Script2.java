package TestAgent;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pr.beh.ConsumerFSM;

import java.util.ArrayList;
import java.util.List;

public class Script2  extends TestUtil {
    @Test
    public void script_difPrice(){
        List<String> services = new ArrayList<>();
        services.add("jade.core.messaging.TopicManagementService");
        services.add("jade.core.event.NotificationService");
        startJade(services);
        createProducerAgent("script2Producer1");
        createProducerAgent("script2Producer2");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ConsumerFSM fsm = new ConsumerFSM();
        createConsumerAgent("consumer", fsm);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, fsm.winnerBeh.onEnd());
    }
}
