package com.yacht.jschuiteboer.tradinghouse.producer;

import com.yacht.jschuiteboer.tradinghouse.model.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ProducerTask implements Runnable {
    private final Producer producer;
    private final BlockingQueue<Product> queue;

    public ProducerTask(Producer producer, BlockingQueue<Product> queue) {
        this.producer = producer;
        this.queue = queue;
    }

    //TODO: remove duplicate code in ConsumerTask
    @Override
    public void run() {
        log.info("starting: {}", this.producer);

        try {
            while(true) {
                Product product = producer.produce();
                if(!queue.offer(product, 2, TimeUnit.SECONDS)) {
                    log.info("stopping: {}", this.producer);
                    return;
                }
            }
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
