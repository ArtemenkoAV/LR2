package lab4.Datas;

import lombok.Data;

import java.util.ArrayList;
@Data
public class PriceForDistributerData {
    ArrayList<PriceWithNameForDistributerData> pricesWithNames = new ArrayList<>();
    private double minPrice;
}
