package exchange;

import exchange.entities.ExchangeOrder;
import exchange.entities.ExchangeOrderCreator;
import exchange.entities.TradeAcceptor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyTradeLoader implements Runnable {

    private static int COUNT_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private TradeAcceptor tradeAcceptor;
    private ExchangeOrder order;

    public MyTradeLoader(TradeAcceptor tradeAcceptor, ExchangeOrder order) {
        this.tradeAcceptor = tradeAcceptor;
        this.order = order;
    }

    @Override
    public void run() {
        tradeAcceptor.acceptTradeData(order);
    }

    public static void main(String[] args) throws InterruptedException {

        ConcurrentHashMap<String, ExchangeOrder> exchangeOrders = ExchangeOrderCreator.createExchangeOrdersList();
        TradeAcceptor tradeAcceptor = new TradeAcceptor();

        ExecutorService executorService = Executors.newFixedThreadPool(COUNT_OF_PROCESSORS);

        long start = System.nanoTime();

        try {
            for (ExchangeOrder order : exchangeOrders.values()) {
                executorService.execute(new TradeLoader(tradeAcceptor, order));
            }
        } finally {
            executorService.shutdownNow();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

            long end = System.nanoTime();
            System.out.printf( "It takes is %d ms \n" ,(end - start) / 1000000);

            System.out.printf("Average exchange cases: %d \n", exchangeOrders.values().size());
        }


    }
}
