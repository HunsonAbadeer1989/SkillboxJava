package exchange.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class TradeAcceptor {

    public ConcurrentHashMap<String, ExchangeOrder> orderMap;

    public TradeAcceptor() {
        orderMap = new ConcurrentHashMap<>();
    }

    public void acceptTradeData(Object tradeObject) {
        if (tradeObject != null) {

            String[] tempObjects = tradeObject.toString().trim().split(","); /* Split given object
                                                                                       & collect values in array */

            if (tempObjects[0] != null && !tempObjects[0].trim().isEmpty()
                    && tempObjects[1] != null && !tempObjects[1].trim().isEmpty()) {

                double tempFilledQty = Double.parseDouble(tempObjects[2].trim());

                if (!orderMap.containsKey(tempObjects[0])) { // Case when object absent in Map
                    orderMap.put(tempObjects[0].trim(),
                            new ExchangeOrder(tempObjects[0].trim(),
                                    tempObjects[1].trim(),
                                    tempFilledQty));
                }
                else {                                      // Case when object already in Map
                    orderMap.put(tempObjects[0].trim(),
                            new ExchangeOrder(tempObjects[0].trim(),
                                    tempObjects[1].trim(),
                                    Math.max(tempFilledQty, orderMap.get(tempObjects[0]).getFilledQty())));
                }
                System.out.println(orderMap.get(tempObjects[0]));
            }
        } else {
            System.out.println("TradeObject dose not exist");

        }
    }
}
