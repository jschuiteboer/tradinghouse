package com.yacht.jschuiteboer.tradinghouse;

import com.yacht.jschuiteboer.tradinghouse.consumer.Consumer;
import com.yacht.jschuiteboer.tradinghouse.consumer.ConsumerTask;
import com.yacht.jschuiteboer.tradinghouse.model.Product;
import com.yacht.jschuiteboer.tradinghouse.producer.Producer;
import com.yacht.jschuiteboer.tradinghouse.producer.ProducerTask;
import com.yacht.jschuiteboer.tradinghouse.tradehouse.TradeHouse;
import com.yacht.jschuiteboer.tradinghouse.tradehouse.TradeHouseTask;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootApplication
public class TradeHouseApplication implements ApplicationRunner {
    private final List<TradeHouse> tradeHouses;
    private final List<Producer> producers;
    private final List<Consumer> consumers;

    public TradeHouseApplication(List<TradeHouse> tradeHouses, List<Producer> producers, List<Consumer> consumers) {
        this.tradeHouses = tradeHouses;
        this.producers = producers;
        this.consumers = consumers;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Runnable> runnables = new ArrayList<>();
        List<BlockingQueue<Product>> producerQueues = new ArrayList<>();
        List<BlockingQueue<Product>> consumerQueues = new ArrayList<>();

        for(Producer producer : producers) {
            BlockingQueue<Product> producerQueue = new SynchronousQueue<>();
            producerQueues.add(producerQueue);
            runnables.add(new ProducerTask(producer, producerQueue));
        }

        for(Consumer consumer : consumers) {
            BlockingQueue<Product> consumerQueue = new SynchronousQueue<>();
            consumerQueues.add(consumerQueue);
            runnables.add(new ConsumerTask(consumer, consumerQueue));
        }

        for(TradeHouse tradeHouse : tradeHouses) {
            runnables.add(new TradeHouseTask(tradeHouse, producerQueues, consumerQueues));
        }

        ExecutorService executor = Executors.newFixedThreadPool(20);
        for(Runnable runnable : runnables) {
            executor.execute(runnable);
        }
        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        SpringApplication.run(TradeHouseApplication.class, args);
    }
}
