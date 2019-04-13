package com.yacht.jschuiteboer.tradinghouse;

import com.yacht.jschuiteboer.tradinghouse.consumer.Consumer;
import com.yacht.jschuiteboer.tradinghouse.producer.Producer;
import com.yacht.jschuiteboer.tradinghouse.tradehouse.TradeHouse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public Producer producer1() {
        return new Producer("product 1", 234);
    }

    @Bean
    public Producer producer2() {
        return new Producer("product 2", 420);
    }

    @Bean
    public Producer producer3() {
        return new Producer("product 3", 100);
    }

    @Bean
    public Consumer consumer1() {
        return new Consumer("consumer 1");
    }

    @Bean
    public Consumer consumer2() {
        return new Consumer("consumer 2");
    }

    @Bean
    public TradeHouse tradeHouse1() {
        return new TradeHouse("trade house 1");
    }

    @Bean
    public TradeHouse tradeHouse2() {
        return new TradeHouse("trade house 2");
    }
}
