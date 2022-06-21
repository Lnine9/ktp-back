package com.ktp.main.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface StringCacheService {
    <K, V> void add(K key, V value);

    <K, V> void add(K key, V value, long timeout, TimeUnit unit);

    <K, SK, V> void addHashCache(K key, SK subKey, V value);

    <K, SK, V> void addHashCache(K key, SK subKey, V value, long timeout, TimeUnit unit);

    <K, SK> Object getHashCache(K key, SK subKey);

    <K, V> V getObject(K key, Class<V> clazz);

    <K, V> List<V> getList(K key, Class<V> clazz);

    <K> String get(K key);

    void delete(String key);

    void delete(Collection<String> keys);

    byte[] dump(String key);

    Boolean hasKey(String key);

    Boolean expire(String key, long timeout, TimeUnit unit);

    Boolean expireAt(String key, Date date);

    Boolean persist(String key);

    Long getExpire(String key, TimeUnit unit);

    Long getExpire(String key);
}
