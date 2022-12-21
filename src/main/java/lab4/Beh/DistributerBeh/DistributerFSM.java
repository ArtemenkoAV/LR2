package lab4.Beh.DistributerBeh;


import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lab4.Beh.DistributerBeh.FSMBeh.*;
import lab4.Beh.DistributerBeh.FSMBeh.DivisionBeh.*;
import lab4.Datas.DistributerData;
import lab4.Datas.PriceForDistributerData;
import lab4.Datas.PriceWithNameForDistributerData;

public class DistributerFSM extends FSMBehaviour {
    PriceForDistributerData pricesForDistributerData = new PriceForDistributerData();
    DistributerData data;
    PriceWithNameForDistributerData bestPrice = new PriceWithNameForDistributerData(0.0, null);
    public DistributerFSM(Agent a, DistributerData data) {
        super(a);
        this.data = data;
        registerFirstState(new SendTopicNameForProducer(getAgent(), data), "SendTopic");
        registerState(new SendTaskForTopic(getAgent(), 4000,data), "SendTask");
        registerState(new DistributerParallelBeh(getAgent(), pricesForDistributerData, 7000),
                "ReceivingPrices");
        registerState(new ChoosingBestPrice(getAgent(), data, pricesForDistributerData, bestPrice),
                "ChooseBestPrice");
        registerState(new WaitingForConfirmParal(getAgent(),3000), "Wait");
        registerLastState(new IBoughtEnergy(bestPrice), "BoughtEnergy");
        registerLastState(new SellerAlreadySoldOneShotBeh(getAgent(),data),"Restart");
        registerState(new DivisionContract(getAgent(), data), "DivisionContract");
        registerState(new DistributerParallelBehAfterDivision(getAgent(), pricesForDistributerData, 5000),
                "ReceivePriceAfterDiv");
        registerState(new ChoosingBestPricesAfterDivision(getAgent(), data, pricesForDistributerData),
                "ChooseBestPriceAfterDiv");
        registerLastState(new TheyDontHaveEnergy(), "NoEnergy");
        registerLastState(new MinPriceTooLargeAfterDivision(getAgent(), pricesForDistributerData), "NewMaxPrice");
        registerState(new WaitingForConfirmAfterDivisionParall(getAgent(), 6000, data),
                "ConfirmAfterDivision");
        registerLastState(new SendingReport(getAgent(), data), "ReportBought");


        registerDefaultTransition("SendTopic", "SendTask");
        registerDefaultTransition("SendTask","ReceivingPrices");
        registerTransition("ReceivingPrices","ChooseBestPrice", 1);
        registerDefaultTransition("ChooseBestPrice", "Wait");
        registerTransition("Wait", "BoughtEnergy", 1);
        registerTransition("Wait", "Restart", 2);
        registerTransition("ReceivingPrices","DivisionContract", 2);
        registerDefaultTransition("DivisionContract", "ReceivePriceAfterDiv");
        registerTransition("ReceivePriceAfterDiv", "ChooseBestPriceAfterDiv", 1);
        registerTransition("ReceivePriceAfterDiv","NoEnergy", 2);
        registerTransition("ChooseBestPriceAfterDiv","ConfirmAfterDivision", 1);
        registerTransition("ChooseBestPriceAfterDiv","NewMaxPrice", 2);
        registerTransition("ConfirmAfterDivision","ReportBought", 1 );
        registerTransition("ConfirmAfterDivision","NoEnergy", 2 );

    }
}
