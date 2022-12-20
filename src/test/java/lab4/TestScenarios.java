package lab4;

import jade.core.behaviours.OneShotBehaviour;
import lab4.Beh.DistributerBeh.ReceiveTaskToAuction;
import lab4.Beh.ProducerBeh.ReceiveTask;
import lab4.Datas.ConsumerData;
import lab4.Datas.ProducerData;
import lab4.TestBeh.ConsumerFSMTest;
import lab4.TestBeh.SESFSMTest;
import lab4.TestBeh.TPPFSMTest;
import lab4.TestBeh.WESFSMTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class TestScenarios extends TestUtil{
    ProducerData producerData = new ProducerData();
    ConsumerData consumerData = new ConsumerData();
    @Test
    public void scenario1() throws InterruptedException {
        List<String> services = new ArrayList<>();
        services.add("jade.core.messaging.TopicManagementService");
        services.add("jade.core.event.NotificationService");

        startJade(services);
        ConsumerFSMTest behaviourToTest = new ConsumerFSMTest(consumerData, 12, 50);
        CreateAgent("Mpei", behaviourToTest);
        CreateAgent("TPP",new TPPFSMTest(producerData), new ReceiveTask(producerData));
        CreateAgent("WES",new WESFSMTest(producerData), new ReceiveTask(producerData));
        CreateAgent("ThirdDistributer", new ReceiveTaskToAuction());
        sleep(35000);

        Assertions.assertEquals(2,behaviourToTest.onEnd());


    }
    @Test
    public void scenario2() throws InterruptedException {
        List<String> services = new ArrayList<>();
        services.add("jade.core.messaging.TopicManagementService");
        services.add("jade.core.event.NotificationService");

        startJade(services);
        ConsumerFSMTest behaviourToTest = new ConsumerFSMTest(consumerData, 9, 100);
        CreateAgent("Mpei", behaviourToTest);
        CreateAgent("TPP",new TPPFSMTest(producerData), new ReceiveTask(producerData));
        CreateAgent("SES",new SESFSMTest(producerData), new ReceiveTask(producerData));
        CreateAgent("TPP2",new TPPFSMTest(producerData), new ReceiveTask(producerData));
        CreateAgent("ThirdDistributer", new ReceiveTaskToAuction());
        sleep(35000);

        Assertions.assertEquals(1,behaviourToTest.onEnd());


    }
    @Test
    public void scenario3() throws InterruptedException {
        List<String> services = new ArrayList<>();
        services.add("jade.core.messaging.TopicManagementService");
        services.add("jade.core.event.NotificationService");

        startJade(services);
        ConsumerFSMTest behaviourToTest = new ConsumerFSMTest(consumerData,18, 100);
        CreateAgent("Mpei", behaviourToTest);
        CreateAgent("TPP",new TPPFSMTest(producerData), new ReceiveTask(producerData));
        CreateAgent("SES",new SESFSMTest(producerData), new ReceiveTask(producerData));
        CreateAgent("WES",new WESFSMTest(producerData), new ReceiveTask(producerData));
        CreateAgent("ThirdDistributer", new ReceiveTaskToAuction());
        sleep(50000);

        Assertions.assertEquals(4,behaviourToTest.onEnd());


    }

}
