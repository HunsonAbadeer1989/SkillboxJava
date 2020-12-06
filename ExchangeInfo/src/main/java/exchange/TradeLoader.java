package exchange;

import exchange.entities.ExchangeOrder;
import exchange.entities.ExchangeOrderCreator;
import exchange.entities.TradeAcceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TradeLoader implements Runnable {

    private TradeAcceptor tradeAcceptor;
    private ExchangeOrder order;

    public TradeLoader(TradeAcceptor tradeAcceptor, ExchangeOrder order) {
        this.tradeAcceptor = tradeAcceptor;
        this.order = order;
    }

    @Override
    public void run() {
        tradeAcceptor.acceptTradeData(order);
    }

    public static void main(String[] args) {

        ConcurrentHashMap<String, ExchangeOrder> exchangeOrders = ExchangeOrderCreator.createExchangeOrdersList();

        List<Thread> threadList = new ArrayList<>();

        for(ExchangeOrder order : exchangeOrders.values()){
            TradeAcceptor tradeAcceptorInThread = new TradeAcceptor();
            Thread thread = new Thread(new TradeLoader(tradeAcceptorInThread, order));
            threadList.add(thread);
        }

        long start = System.nanoTime();

        threadList.forEach(Thread::start);

        long end = System.nanoTime();

        System.out.printf( "It takes is %d ms \n" ,(end - start) / 1000000);

    }
}
