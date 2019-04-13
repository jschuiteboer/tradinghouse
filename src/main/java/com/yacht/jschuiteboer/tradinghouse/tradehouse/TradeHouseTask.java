package com.yacht.jschuiteboer.tradinghouse.tradehouse;

import com.yacht.jschuiteboer.tradinghouse.model.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

@Slf4j
public class TradeHouseTask implements Runnable {
    private final TradeHouse tradeHouse;
    private final List<BlockingQueue<Product>> producerQueues;
    private final List<BlockingQueue<Product>> consumerQueues;

    public TradeHouseTask(TradeHouse tradeHouse,
                          List<BlockingQueue<Product>> producerQueues,
                          List<BlockingQueue<Product>> consumerQueues) {
        this.tradeHouse = tradeHouse;
        this.producerQueues = producerQueues;
        this.consumerQueues = consumerQueues;
    }

    @Override
    public void run() {
        log.info("starting: {}", this.tradeHouse);

        List<Product> partialProducts = producerQueues.stream().parallel()
                .map(queue -> takeFromQueue(queue))
                .collect(Collectors.toList());

        Product product = this.tradeHouse.combineProducts(partialProducts);

        this.consumerQueues.stream().parallel()
                .forEach(queue -> putInQueue(queue, product));

        log.info("stopping: {}", this.tradeHouse);
    }

    private <T> T takeFromQueue(BlockingQueue<T> queue) {
        try {
            return queue.take();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private <T> void putInQueue(BlockingQueue<T> queue, T item) {
        try {
            queue.put(item);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
