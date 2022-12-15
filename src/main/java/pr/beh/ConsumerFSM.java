package pr.beh;

import jade.core.behaviours.FSMBehaviour;
import pr.dataaaaaaaa;

public class ConsumerFSM extends FSMBehaviour {

    public WinnerBeh winnerBeh;

    public ConsumerFSM(){
        dataaaaaaaa data = new dataaaaaaaa();


        winnerBeh = new WinnerBeh(data);
        registerFirstState(new SendTopicName(data), "firstState");
        registerState(new SendQuantity(getAgent(), 1000, data), "secondState");
        registerState(new ReceivePrarllelBeh(getAgent(), data), "thirdState");
        registerLastState(new WinnerBeh(data), "fourthState");

        registerDefaultTransition("firstState", "secondState");
        registerDefaultTransition("secondState", "thirdState");
        registerDefaultTransition("thirdState", "fourthState");
    }
}
