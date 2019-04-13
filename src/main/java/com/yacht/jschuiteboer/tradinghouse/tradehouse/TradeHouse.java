package com.yacht.jschuiteboer.tradinghouse.tradehouse;

import com.yacht.jschuiteboer.tradinghouse.model.Product;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@ToString
public class TradeHouse {
    private final String name;

    public TradeHouse(String name) {
        this.name = name;
    }

    public Product combineProducts(List<Product> partialProducts) {
        return partialProducts.stream().parallel()
                .reduce(Product::concat)
                .map(product -> product.concat(new Product(this.name)))
                .orElseThrow(() -> new IllegalStateException("no products were supplied"));
    }
}
