/*
 * Copyright (C) 2011 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.common.cache;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ExecutionError;
import com.google.common.util.concurrent.UncheckedExecutionException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

import junit.framework.TestCase;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.units.qual.K;

/**
 * Unit test for {@link AbstractLoadingCache}.
 *
 * @author Charles Fry
 */
public class AbstractLoadingCacheTest extends TestCase {

    public void testGetUnchecked_checked() {
        final Exception cause = new Exception();
        final AtomicReference<Object> valueRef = new AtomicReference<>();
        LoadingCache<Object, Object> cache =
                new AbstractLoadingCache<Object, Object>() {
                    @Override
                    public Object get(Object key) throws ExecutionException {
                        Object v = valueRef.get();
                        if (v == null) {
                            throw new ExecutionException(cause);
                        }
                        return v;
                    }

                    @Override
                    public Object getIfPresent(Object key) {
                        return valueRef.get();
                    }
                };

        try {
            cache.getUnchecked(new Object());
            fail();
        } catch (UncheckedExecutionException expected) {
            assertThat(expected).hasCauseThat().isEqualTo(cause);
        }

        Object newValue = new Object();
        valueRef.set(newValue);
        assertSame(newValue, cache.getUnchecked(new Object()));
    }

    public void testGetUnchecked_unchecked() {
        final RuntimeException cause = new RuntimeException();
        final AtomicReference<Object> valueRef = new AtomicReference<>();
        LoadingCache<Object, Object> cache =
                new AbstractLoadingCache<Object, Object>() {
                    @Override
                    public Object get(Object key) throws ExecutionException {
                        Object v = valueRef.get();
                        if (v == null) {
                            throw new ExecutionException(cause);
                        }
                        return v;
                    }

                    @Override
                    public Object getIfPresent(Object key) {
                        return valueRef.get();
                    }
                };

        try {
            cache.getUnchecked(new Object());
            fail();
        } catch (UncheckedExecutionException expected) {
            assertThat(expected).hasCauseThat().isEqualTo(cause);
        }

        Object newValue = new Object();
        valueRef.set(newValue);
        assertSame(newValue, cache.getUnchecked(new Object()));
    }

    public void testGetUnchecked_error() {
        final Error cause = new Error();
        final AtomicReference<Object> valueRef = new AtomicReference<>();
        LoadingCache<Object, Object> cache =
                new AbstractLoadingCache<Object, Object>() {
                    @Override
                    public Object get(Object key) throws ExecutionException {
                        Object v = valueRef.get();
                        if (v == null) {
                            throw new ExecutionError(cause);
                        }
                        return v;
                    }

                    @Override
                    public Object getIfPresent(Object key) {
                        return valueRef.get();
                    }
                };

        try {
            cache.getUnchecked(new Object());
            fail();
        } catch (ExecutionError expected) {
            assertThat(expected).hasCauseThat().isEqualTo(cause);
        }

        Object newValue = new Object();
        valueRef.set(newValue);
        assertSame(newValue, cache.getUnchecked(new Object()));
    }

    public void testGetUnchecked_otherThrowable() {
        final Throwable cause = new Throwable();
        final AtomicReference<Object> valueRef = new AtomicReference<>();
        LoadingCache<Object, Object> cache =
                new AbstractLoadingCache<Object, Object>() {
                    @Override
                    public Object get(Object key) throws ExecutionException {
                        Object v = valueRef.get();
                        if (v == null) {
                            throw new ExecutionException(cause);
                        }
                        return v;
                    }

                    @Override
                    public Object getIfPresent(Object key) {
                        return valueRef.get();
                    }
                };

        try {
            cache.getUnchecked(new Object());
            fail();
        } catch (UncheckedExecutionException expected) {
            assertThat(expected).hasCauseThat().isEqualTo(cause);
        }

        Object newValue = new Object();
        valueRef.set(newValue);
        assertSame(newValue, cache.getUnchecked(new Object()));
    }

    public void testGetAll() throws ExecutionException {
        final RuntimeException cause = new RuntimeException();
        final AtomicReference<Object> valueRef = new AtomicReference<>();
        LoadingCache<Object, Object> cache =
                new AbstractLoadingCache<Object, Object>() {
                    @Override
                    public Object get(Object key) throws ExecutionException {
                        Object v = valueRef.get();
                        if (v == null) {
                            throw new ExecutionException(cause);
                        }
                        return v;
                    }

                    @Override
                    public Object getIfPresent(Object key) {
                        return valueRef.get();
                    }
                };
        Map<Object, Object> expect = new HashMap();
        expect.put(10, 10);
        expect.put(20, 20);
        expect.put(30, 30);

        System.out.println(cache.getAll(ImmutableList.of(10,20,30)).get(10));
        System.out.println(ImmutableMap.copyOf(expect).get(10));

        assertSame(cache.getAll(ImmutableList.of(10,20,30)), ImmutableMap.copyOf(expect));

        Object newValue = new Object();
        valueRef.set(newValue);
        assertSame(newValue, cache.apply(new Object()));

    }

}
