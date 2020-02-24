package com.google.common.hash;

import static com.google.common.truth.Truth.assertThat;

import junit.framework.TestCase;

/** Unit tests for {@link com.google.common.hash.LongAdder}. */
public class LongAdderTest extends TestCase {

    public void testOverflows() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(Long.MAX_VALUE);
        assertThat(longAdder.sum()).isEqualTo(Long.MAX_VALUE);
        longAdder.add(1);
        // silently overflows; is this a bug?
        // See https://github.com/google/guava/issues/3503
        assertThat(longAdder.sum()).isEqualTo(-9223372036854775808L);
    }

    public void testAdd() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(10);
        assertThat(longAdder.sum()).isEqualTo(10);
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
}
