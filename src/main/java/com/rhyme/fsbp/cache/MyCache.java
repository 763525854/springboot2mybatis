package com.rhyme.fsbp.cache;

import com.github.benmanes.caffeine.cache.*;
import com.google.common.graph.Graph;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Caffeine提供四种策略
 */
@SuppressWarnings({"rawtypes", "unchecked", "unused", "UnusedAssignment", "ConstantConditions"})
public class MyCache {
    /**
     * 手动加载cache
     *
     * @return
     */
    public Cache manual() {
        Cache cache = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).maximumSize(10_000).build();
        Object key = null;
        Object value = cache.getIfPresent(key);
        List list = new ArrayList();
        value = cache.get(key, o -> key != null);
        cache.put(key, value);
        cache.invalidate(key);
        cache.invalidateAll();
        ConcurrentMap concurrentMap = cache.asMap();
        return cache;
    }

    /**
     * 自动
     *
     * @return
     */
    public LoadingCache loading() {
        Object key = null;
        List<String> strings = null;
        LoadingCache cache = Caffeine.newBuilder().maximumSize(10_000).expireAfterWrite(10, TimeUnit.MINUTES).build(o -> strings.stream().iterator());
        Object value = cache.get(key);
        cache.getAll(strings);
        return cache;
    }

    /**
     * 异步手动
     *
     * @return
     */
    public AsyncCache asyncCache() {
        AsyncCache cache = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).maximumSize(10_000).buildAsync();
        Object key = "apple";
        CompletableFuture graph = cache.getIfPresent(key);
        graph = cache.get(key, k -> "haha");
        cache.put(key, graph);
        cache.synchronous().invalidate(key);
        return cache;
    }

    /**
     * 异步
     *
     * @return
     */
    public AsyncLoadingCache asyncLoadingCache() {
        Object key = null;
        AsyncLoadingCache cache = Caffeine.newBuilder().maximumSize(10_000).expireAfterWrite(10, TimeUnit.MINUTES).buildAsync(k -> "apple");
        CompletableFuture value = cache.get(key);
        CompletableFuture<Map> value2 = cache.getAll(new ArrayList());
        return cache;
    }

    /**
     * 自定义过期策略
     *
     * @param
     * @throws InterruptedException
     */
    public static void expire() throws InterruptedException {
        LoadingCache cache = Caffeine.newBuilder().expireAfter(new Expiry<Object, Object>() {
            @Override
            public long expireAfterCreate(@NonNull Object o, @NonNull Object o2, long l) {
                return 0;
            }

            @Override
            public long expireAfterUpdate(@NonNull Object o, @NonNull Object o2, long l, @NonNegative long l1) {
                return 0;
            }

            @Override
            public long expireAfterRead(@NonNull Object o, @NonNull Object o2, long l, @NonNegative long l1) {
                return 0;
            }
        }).maximumSize(5).build(k -> "a");
        cache.put("a1", "b1");
        cache.put("a2", "b2");
        cache.put("a3", "b3");
        cache.put("a4", "b4");
        cache.put("a5", "b5");
        cache.put("a6", "b6");
        cache.put("a7", "b7");
        System.out.println("");
        Thread.sleep(1000 * 30);
        for (int i = 1; i < 10; i++) {
            System.out.println(cache.get("a" + i));
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    /**
     * 移除侦听器(监听值是如何被移除的)
     */
    public void removalListeners() {
        Cache<Object, Object> cache = Caffeine.newBuilder().removalListener((Object key, Object value, RemovalCause cause) -> System.out.printf("key %s was removed (%s)%n", key, cause)).build();
        cache.put("apple","apple is goodd");
        cache.invalidate("apple");
        System.out.println("i am reveaosa");
    }

    public static void main1(String[] args) throws InterruptedException {
        MyCache myCache=new MyCache();
        myCache.removalListeners();
        System.out.println("asdasa");
        Thread.sleep(1000);
    }
}
