package exchange;

import exchange.entities.ExchangeOrder;
import exchange.entities.TradeAcceptor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

public class TradeAcceptorTest {

    private final List<ExchangeOrder> exchangeOrders = new ArrayList<>();
    private TradeAcceptor tradeAcceptor;

    @Before
    public void setUpToTradeAcceptor(){
        tradeAcceptor = new TradeAcceptor();

        ExchangeOrder exchangeOrder1 = new ExchangeOrder("iId1", "eID1", 2.0);
        ExchangeOrder exchangeOrder2 = new ExchangeOrder("iId1", "eID1", 5.0);
        ExchangeOrder exchangeOrder3 = new ExchangeOrder("iId1", "eID1", 25.0);
        ExchangeOrder exchangeOrder4 = new ExchangeOrder("iId1", "eID1", 1.0);

        exchangeOrders.add(exchangeOrder1);
        exchangeOrders.add(exchangeOrder2);
        exchangeOrders.add(exchangeOrder3);
        exchangeOrders.add(exchangeOrder4);

        exchangeOrders.forEach(tradeAcceptor::acceptTradeData);
    }

    @Test
    public void acceptTradeData() {
        ExchangeOrder actualOrder = tradeAcceptor.getOrderMap().get("iId1");
        ExchangeOrder expectOrder = new ExchangeOrder("iId1", "eID1", 25.0);

        assertEquals(expectOrder, actualOrder);
    }

}