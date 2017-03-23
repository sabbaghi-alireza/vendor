package vendor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alireza on 3/23/2017.
 */
public class Engine {

    public static Map<Integer, String> coinsItem;
    public static Map<String, Integer> itemQuantity;

    public Engine() {
        coinsItem = new HashMap<>();
        itemQuantity = new HashMap<>();
        initialize();
    }

    private void initialize() {
        coinsItem.put(5, "Tic tac");
        coinsItem.put(10, "M&M");
        coinsItem.put(20, "Lollipop");
        coinsItem.put(50, "Chocolate");

        itemQuantity.put("Tic tac", 20);
        itemQuantity.put("M&M", 10);
        itemQuantity.put("Lollipop", 5);
        itemQuantity.put("Chocolate", 2);
    }

    public void process(List<Integer> denominationList) throws NotValidDenominationException {

        //first, validate denomination size
        for (Integer coin : denominationList) {
            if (!coinsItem.keySet().contains(coin)) {
                throw new NotValidDenominationException("The denomination of size " + coin + " is not valid !");
            }
        }

        List<String> itemsUsed = new ArrayList<>();
        Integer moneyTotal = 0;
        //main process
        for (Integer coin : denominationList) {
            String item = coinsItem.get(coin);
            Integer quantity = itemQuantity.get(item);

            if (quantity > 0) {
                itemsUsed.add(item);
                itemQuantity.put(item, (quantity - 1));
                moneyTotal += coin;
            }
        }

        for (String item : itemsUsed) {
            System.out.println(item);
        }

        double moneyInDollars = ((double)moneyTotal) / 100;
        String summary = reportAvailableItems(moneyInDollars);
        System.out.println(summary);
    }

    private String reportAvailableItems(double moneyInDollars) {
        StringBuilder buffer = new StringBuilder();
        for (String item : itemQuantity.keySet()){
            buffer.append(item).append(": ").append(itemQuantity.get(item)).append(", ");
        }
        buffer.append("Money: ").append("$").append(moneyInDollars);
        return buffer.toString();
    }
}
