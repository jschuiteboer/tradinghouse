package com.yacht.jschuiteboer.tradinghouse.util;

public class Argument {
    private Argument() {}

    public static <T extends Number> void requireLargerThan(Comparable<T> value, T minValue, String name) {
        if(value.compareTo(minValue) <= 0) {
            String msg = String.format("argument '%s' must be larger than %d, but was %d", name, minValue, value);
            throw new IllegalArgumentException(msg);
        }
    }

    public static <T extends Number> void requireSmallerThan(Comparable<T> value, T maxValue, String name) {
        if(value.compareTo(maxValue) >= 0) {
            String msg = String.format("argument '%s' must be smaller than %d, but was %d", name, maxValue, value);
            throw new IllegalArgumentException(msg);
        }
    }

    public static <T extends Number> void requireEqualTo(Comparable<T> value, T other, String name) {
        if(value.compareTo(other) != 0) {
            String msg = String.format("argument '%s' must be equal to %d, but was %d", name, other, value);
            throw new IllegalArgumentException(msg);
        }
    }
}
