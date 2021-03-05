package com.cache.caches;


public interface Cache<Key, Value> {
    void put(Key key, Value value);
    Value get(Key key);
}
