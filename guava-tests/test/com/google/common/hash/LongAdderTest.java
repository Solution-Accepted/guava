package com.google.common.hash;

import static com.google.common.truth.Truth.assertThat;

import junit.framework.TestCase;

/**
 * Unit tests for {@link com.google.common.hash.LongAdder}.
 *
 * @author Wen-Chia, Yang
 */
public class LongAdderTest extends TestCase {
    private static final int TEST_INT_VALUE = 10;
    private static final double TEST_DOUBLE_VALUE = 10.0;

    public void testOverflows() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(Long.MAX_VALUE);
        assertThat(longAdder.sum()).isEqualTo(Long.MAX_VALUE);
        longAdder.add(1);
        assertThat(longAdder.sum()).isEqualTo(-9223372036854775808L);
    }

    public void testAdd() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(TEST_INT_VALUE);
        assertThat(longAdder.sum()).isEqualTo(TEST_INT_VALUE);
    }

    public void testIncrement() {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        assertThat(longAdder.sum()).isEqualTo(1);
    }

    public void testDecrement() {
        LongAdder longAdder = new LongAdder();
        longAdder.decrement();
        assertThat(longAdder.sum()).isEqualTo(-1);
    }

    public void testReset() {
        LongAdder longAdder = new LongAdder();
        longAdder.reset();
        assertThat(longAdder.sum()).isEqualTo(0);
    }

    public void testSumThenReset() {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        assertThat(longAdder.sum()).isEqualTo(1);

        longAdder.sumThenReset();
        assertThat(longAdder.sum()).isEqualTo(0);
    }

    public void testLongValue() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(TEST_INT_VALUE);
        assertThat(longAdder.longValue()).isEqualTo((long) TEST_INT_VALUE);
    }

    public void testIntValue() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(TEST_INT_VALUE);
        assertThat(longAdder.intValue()).isEqualTo(TEST_INT_VALUE);
    }

    public void testFloatValue() {
        LongAdder longAdder = new LongAdder();
        longAdder.add((int) TEST_DOUBLE_VALUE);
        assertThat(longAdder.floatValue()).isEqualTo((float) TEST_DOUBLE_VALUE);
    }

    public void testDoubleValue() {
        LongAdder longAdder = new LongAdder();
        longAdder.add((int) TEST_DOUBLE_VALUE);
        assertThat(longAdder.doubleValue()).isEqualTo(TEST_DOUBLE_VALUE);
    }

    public void testToString() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(TEST_INT_VALUE);
        assertThat(longAdder.toString()).isEqualTo(String.valueOf(TEST_INT_VALUE));
    }
}
