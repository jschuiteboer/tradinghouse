package com.yacht.jschuiteboer.tradinghouse.producer;

import com.yacht.jschuiteboer.tradinghouse.model.Product;
import com.yacht.jschuiteboer.tradinghouse.util.Argument;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
@ToString
public class Producer {
    private static final long MAX_PRODUCTION_TIME = TimeUnit.SECONDS.toMillis(1);

    private final String productName;
    private final long productionTime;

    public Producer(String productName, long productionTimeMillis) {
        Argument.requireSmallerThan(productionTimeMillis, MAX_PRODUCTION_TIME, "productionTimeMillis");

        this.productName = productName;
        this.productionTime = productionTimeMillis;
    }

    public Product produce() {
        log.info("{} starting production", this);

        this.simulateProductionTime();

        Product product = new Product(this.productName);

        log.info("{} produced: {}", this, product);

        return product;
    }

    private void simulateProductionTime() {
        try {
            Thread.sleep(this.productionTime);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
