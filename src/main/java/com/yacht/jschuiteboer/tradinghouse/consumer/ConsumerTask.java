package com.yacht.jschuiteboer.tradinghouse.consumer;

import com.yacht.jschuiteboer.tradinghouse.model.Product;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ConsumerTask implements Runnable {
    private final Consumer consumer;

    @Getter
    private final BlockingQueue<Product> queue;

    public ConsumerTask(Consumer consumer, BlockingQueue<Product> queue) {
        this.consumer = consumer;
        this.queue = queue;
    }

    //TODO: remove duplicate code in ProducerTask
    @Override
    public void run() {
        log.info("starting: {}", this.consumer);
        try {
            while(true) {
                Product product = queue.poll(2, TimeUnit.SECONDS);
                if (product == null) {
                    log.info("stopping: {}", this.consumer);
                    return;
                }
                this.consumer.consume(product);
            }
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
