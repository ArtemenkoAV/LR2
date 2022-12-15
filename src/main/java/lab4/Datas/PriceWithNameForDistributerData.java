package lab4.Datas;

import lombok.Data;

@Data
public class PriceWithNameForDistributerData {
    private double price;
    private String name;

    public PriceWithNameForDistributerData(double price, String name) {
        this.price = price;
        this.name = name;
    }
}
