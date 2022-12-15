package pr.beh;

import jade.core.behaviours.OneShotBehaviour;
import pr.dataaaaaaaa;

public class WinnerBeh extends OneShotBehaviour {
    dataaaaaaaa data;

    public WinnerBeh(dataaaaaaaa data) {
        this.data = data;
    }

    @Override
    public void action() {
        int price1 = data.getAgentPrices().get(0).getPrice();
        int price2 = data.getAgentPrices().get(1).getPrice();
        if(price1==price2){
            System.out.println("The prices are the same"+ " " + price1);
            data.setSamePrice(true);
        }
        else if (price1<price2){
            System.out.println("Winner"+ " " + data.getAgentPrices().get(0).getAgentName());
        }
        else {
            System.out.println("Winner"+ " " + data.getAgentPrices().get(1).getAgentName());
        }
    }

    @Override
    public int onEnd() {
        if (data.isSamePrice()){
            return 2;
        } else {
            return 1;
        }

    }
}
