package exchange.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

@Setter
@Getter
public class ExchangeOrderCreator { //CREATE RANDOM EXCHANGE CASES

    private static final String INTERNAL = "iId";
    private static final String EXCHANGE = "eID";

    private static final int EXCHANGE_CASES = 1_000_000; // ALL AMOUNT OF EXCHANGE CASES
    private static final int MAX_FILLED_QTY = 500; // MAXIMUM VALUE filledQTY in EXCHANGE OBJECT
    private static final int ORDER_CASES = 4;
    private static final int SWITCH_CASES = 3;

    public static ConcurrentHashMap<String, ExchangeOrder> exchangeOrdersCHashMap = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, ExchangeOrder> createExchangeOrdersList() {
        for (int i = 1; i <= EXCHANGE_CASES; i++) {

            for (int j = 1; j <= (int) (Math.random() * ORDER_CASES) + 1; j++) { /* several random cases for
                                                                                               each Exchange object */
                double filledQty = (Math.random() * MAX_FILLED_QTY + 1);
                int exchangeCase = (int) (Math.random() * SWITCH_CASES) + 1;
                ExchangeOrder newExchangeOrder;

                switch (exchangeCase) {
                    case (1):
                        newExchangeOrder =
                                new ExchangeOrder(INTERNAL + i, "", Math.round(filledQty));
                        exchangeOrdersCHashMap.put(newExchangeOrder.toString(), newExchangeOrder);
                        break;

                    case (2):
                        newExchangeOrder =
                                new ExchangeOrder("", EXCHANGE + i, Math.round(filledQty));
                        exchangeOrdersCHashMap.put(newExchangeOrder.toString(), newExchangeOrder);
                        break;

                    default:
                        newExchangeOrder =
                                new ExchangeOrder(INTERNAL + i, EXCHANGE + i,
                                        Math.round(filledQty));
                        exchangeOrdersCHashMap.put(newExchangeOrder.toString(), newExchangeOrder);
                        break;
                }
            }
        }
        return exchangeOrdersCHashMap;
    }
}
