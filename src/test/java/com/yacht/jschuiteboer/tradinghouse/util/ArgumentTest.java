package com.yacht.jschuiteboer.tradinghouse.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class ArgumentTest<T extends Number> {
    private final Comparable<T> value;
    private final T other;
    private final boolean isLarger;
    private final boolean isEqual;
    private final boolean isSmaller;

    @Parameters(name = "{0}, {1}")
    public static Object[][] parameters() {
        return new Object[][] {
            { 1, 0, true, false, false, },
            { 1, 1, false, true, false, },
            { 0, 1, false, false, true, },
        };
    }

    public ArgumentTest(Comparable<T> value, T other, boolean isLarger, boolean isEqual, boolean isSmaller) {
        this.value = value;
        this.other = other;
        this.isLarger = isLarger;
        this.isEqual = isEqual;
        this.isSmaller = isSmaller;
    }

    @Test
    public void testRequireLargerThan() {
        try {
            Argument.requireLargerThan(this.value, this.other, "test");
            if(!isLarger) fail();
        } catch(IllegalArgumentException ex) {
            if(isLarger) fail();
        }
    }

    @Test
    public void testRequireEqualTo() {
        try {
            Argument.requireEqualTo(this.value, this.other, "test");
            if(!isEqual) fail();
        } catch(IllegalArgumentException ex) {
            if(isEqual) fail();
        }
    }

    @Test
    public void testRequireSmallerThan() {
        try {
            Argument.requireSmallerThan(this.value, this.other, "test");
            if(!isSmaller) fail();
        } catch(IllegalArgumentException ex) {
            if(isSmaller) fail();
        }
    }
}
