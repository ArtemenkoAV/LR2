package lab4.Beh.DistributerBeh.FSMBeh.DivisionBeh;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lab4.Datas.DistributerData;
import lab4.Datas.PriceForDistributerData;

import java.util.ArrayList;

public class ChoosingBestPricesAfterDivision extends OneShotBehaviour {
    DistributerData data;
    PriceForDistributerData priceForDistributerData;
    ArrayList<Double> firstProducerPrices = new ArrayList<>();
    ArrayList<Double> secondProducerPrices = new ArrayList<>();
    ArrayList<Double> thirdProducerPrices = new ArrayList<>();
    ArrayList<Double> forthProducerPrices = new ArrayList<>();
    private int onEnd = 2;
    ArrayList<Double> allMinPricesAfterDivision = new ArrayList<>();

    public ChoosingBestPricesAfterDivision(Agent a, DistributerData data,
                                           PriceForDistributerData priceForDistributerData) {
        super(a);
        this.data = data;
        this.priceForDistributerData = priceForDistributerData;
    }

    @Override
    public int onEnd() {
        return onEnd;
    }
    @Override
    public void action() {
        for (int i = 0; i < priceForDistributerData.getPricesWithNames().size(); i++) {
            if (priceForDistributerData.getPricesWithNames().get(i).getName().equals("TPP")) {
                firstProducerPrices.add(priceForDistributerData.getPricesWithNames().get(i).getPrice());
            }
            if (priceForDistributerData.getPricesWithNames().get(i).getName().equals("TPP2")) {
                secondProducerPrices.add(priceForDistributerData.getPricesWithNames().get(i).getPrice());
            }
            if (priceForDistributerData.getPricesWithNames().get(i).getName().equals("SES")) {
                thirdProducerPrices.add(priceForDistributerData.getPricesWithNames().get(i).getPrice());
            }
            if (priceForDistributerData.getPricesWithNames().get(i).getName().equals("WES")) {
                forthProducerPrices.add(priceForDistributerData.getPricesWithNames().get(i).getPrice());
            }
        }
        if (firstProducerPrices.size() > 0) {
            double minprice = firstProducerPrices.get(0);
            for (int i = 1; i < firstProducerPrices.size(); i++) {
                if (minprice >= firstProducerPrices.get(i)) {
                    minprice = firstProducerPrices.get(i);
                }
            } allMinPricesAfterDivision.add(minprice);
            if (minprice <= data.getMaxPrice()) {
                onEnd = 1;
                ACLMessage win = new ACLMessage(ACLMessage.PROPOSE);
                win.addReceiver(new AID("TPP", false));
                win.setProtocol("WinnerAfterDiv");
                win.setContent("TPP " + minprice);
                getAgent().send(win);
            }
        }
        if (secondProducerPrices.size() > 0) {
            double minprice = secondProducerPrices.get(0);
            for (int i = 1; i < secondProducerPrices.size(); i++) {
                if (minprice >= secondProducerPrices.get(i)) {
                    minprice = secondProducerPrices.get(i);
                }
            } allMinPricesAfterDivision.add(minprice);
            if (minprice <= data.getMaxPrice()) {
                onEnd = 1;
                ACLMessage win = new ACLMessage(ACLMessage.PROPOSE);
                win.addReceiver(new AID("TPP2", false));
                win.setProtocol("WinnerAfterDiv");
                win.setContent("TPP2 " + minprice);
                getAgent().send(win);
            }
        }
        if (thirdProducerPrices.size() > 0) {
            double minprice = thirdProducerPrices.get(0);
            for (int i = 1; i < thirdProducerPrices.size(); i++) {
                if (minprice >= thirdProducerPrices.get(i)) {
                    minprice = thirdProducerPrices.get(i);
                }
            } allMinPricesAfterDivision.add(minprice);
            if (minprice <= data.getMaxPrice()) {
                onEnd = 1;
                ACLMessage win = new ACLMessage(ACLMessage.PROPOSE);
                win.addReceiver(new AID("SES", false));
                win.setProtocol("WinnerAfterDiv");
                win.setContent("SES " + minprice);
                getAgent().send(win);
            }
        }
        if (forthProducerPrices.size() > 0) {
            double minprice = forthProducerPrices.get(0);
            for (int i = 1; i < forthProducerPrices.size(); i++) {
                if (minprice >= forthProducerPrices.get(i)) {
                    minprice = forthProducerPrices.get(i);
                }
            } allMinPricesAfterDivision.add(minprice);
            if (minprice <= data.getMaxPrice()) {
                onEnd = 1;
                ACLMessage win = new ACLMessage(ACLMessage.PROPOSE);
                win.addReceiver(new AID("WES", false));
                win.setProtocol("WinnerAfterDiv");
                win.setContent("WES " + minprice);
                getAgent().send(win);
            }
        }
        priceForDistributerData.setMinPrice(allMinPricesAfterDivision.get(0));
        for (int i = 1; i < allMinPricesAfterDivision.size(); i++) {
            if ( priceForDistributerData.getMinPrice() <= allMinPricesAfterDivision.get(i)) {
                priceForDistributerData.setMinPrice(allMinPricesAfterDivision.get(i));

            }
        }


    }
}
