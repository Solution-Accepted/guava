package com.google.common.hash;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.hash.LongAdder;
import com.google.common.cache.PackageSanityTests;
import junit.framework.TestCase;

/** Unit tests for {@link com.google.common.hash.LongAdder}. */
public class LongAdderTest extends TestCase {

    /**
     * No-op null-pointer test for {@link com.google.common.hash.LongAdder} to override the {@link PackageSanityTests}
     * version, which checks package-private methods that we don't want to have to annotate as {@code
     * Nullable} because we don't want diffs from jsr166e.
     */
    public void testNulls() {}

    public void testOverflows() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(Long.MAX_VALUE);
        assertThat(longAdder.sum()).isEqualTo(Long.MAX_VALUE);
        longAdder.add(1);
        // silently overflows; is this a bug?
        // See https://github.com/google/guava/issues/3503
        assertThat(longAdder.sum()).isEqualTo(-9223372036854775808L);
    }

    public void testIncrement() {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        assertThat(longAdder.sum()).isEqualTo(1);
    }

    public void testDevrement() {
        LongAdder longAdder = new LongAdder();
        longAdder.decrement();
        assertThat(longAdder.sum()).isEqualTo(-1);
    }

    public void testReset() {
        LongAdder longAdder = new LongAdder();
        longAdder.reset();
        assertThat(longAdder.sum()).isEqualTo(0);
    }
}
