package com.yacht.jschuiteboer.tradinghouse.model;

import lombok.Value;

@Value
public class Product {
    private static final String SEPARATOR = "-";

    private final String contents;

    public Product concat(Product other) {
        return new Product(this.contents.concat(SEPARATOR).concat(other.contents));
    }
}
