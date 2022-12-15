package lab4.Beh.DistributerBeh;


import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lab4.Beh.DistributerBeh.FSMBeh.ChoosingBestPrice;
import lab4.Beh.DistributerBeh.FSMBeh.DistributerParallelBeh;
import lab4.Beh.DistributerBeh.FSMBeh.DivisionBeh.*;
import lab4.Beh.DistributerBeh.FSMBeh.ReceivingPricesFromProducer;
import lab4.Beh.DistributerBeh.FSMBeh.WaitingForConfirm;
import lab4.Datas.DistributerData;
import lab4.Datas.PriceForDistributerData;
import lab4.Datas.PriceWithNameForDistributerData;
import pr.beh.SendQuantity;
import pr.beh.SendTopicName;

public class DistributerFSM extends FSMBehaviour {
    PriceForDistributerData pricesForDistributerData = new PriceForDistributerData();
    DistributerData data;
    PriceWithNameForDistributerData bestPrice = new PriceWithNameForDistributerData(0.0, null);
    public DistributerFSM(Agent a, DistributerData data) {
        super(a);
        this.data = data;
        registerFirstState(new SendTopicNameForProducer(getAgent(), data), "firstState");
        registerState(new SendTaskForTopic(getAgent(), 1000,data), "secondState");
//        registerLastState(new ReceivingPricesFromProducer(getAgent(), pricesForDistributerData), "RECEIVINGPRICES");
        registerState(new DistributerParallelBeh(getAgent(), pricesForDistributerData, 5000), "RECEIVINGPRICES");
        registerState(new ChoosingBestPrice(getAgent(), data, pricesForDistributerData, bestPrice), "CHOOSINGBEST");
        registerLastState(new WaitingForConfirm(getAgent(),bestPrice,data), "THEEND");
        registerState(new DivisionContract(getAgent(), data, pricesForDistributerData), "DIVISIONCONTRACT");
        registerState(new DistributerParallelBehAfterDivision(getAgent(), pricesForDistributerData, 5000), "RECEIVINGPRICESAFTERDIVIS");
        registerState(new ChoosingBestPricesAfterDivision(getAgent(), data, pricesForDistributerData), "PRICESAFTERDIVISION");
        registerLastState(new TheyDontHaveEnergy(), "THEYDONTHAVEENERGY");
        registerLastState(new MinPriceTooLargeAfterDivision(), "MIN");
        registerState(new WaitingForConfirmAfterDivisionParall(getAgent(), 10000, data), "ConfirmAfterDivision");
        registerLastState(new SendingReport(getAgent(), data), "REPORTBOUGHT");
        registerLastState(new DontHaveEnergy(), "NOENERGY");


        registerDefaultTransition("firstState", "secondState");
        registerDefaultTransition("secondState","RECEIVINGPRICES" );
        registerTransition("RECEIVINGPRICES","CHOOSINGBEST", 1);
        registerDefaultTransition("CHOOSINGBEST", "THEEND");
        registerTransition("RECEIVINGPRICES","DIVISIONCONTRACT", 2);
        registerDefaultTransition("DIVISIONCONTRACT", "RECEIVINGPRICESAFTERDIVIS");
        registerTransition("RECEIVINGPRICESAFTERDIVIS", "PRICESAFTERDIVISION", 1);
        registerTransition("RECEIVINGPRICESAFTERDIVIS","THEYDONTHAVEENERGY", 2);
        registerDefaultTransition("PRICESAFTERDIVISION","ConfirmAfterDivision");
        registerTransition("ConfirmAfterDivision","REPORTBOUGHT", 1 );
        registerTransition("ConfirmAfterDivision","NOENERGY", 2 );
//        registerDefaultTransition("MIN","THEEND2");

    }
}
