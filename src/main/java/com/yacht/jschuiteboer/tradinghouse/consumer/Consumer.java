package com.yacht.jschuiteboer.tradinghouse.consumer;

import com.yacht.jschuiteboer.tradinghouse.model.Product;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class Consumer {
    private final String name;

    public Consumer(String name) {
        this.name = name;
    }

    public void consume(Product product) {
        log.info("{} consumed: {}", this, product);
    }
}
