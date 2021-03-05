package com.cache.factories;

import com.cache.caches.LRUCache;
import com.cache.policies.LRUEvictionPolicy;
import com.cache.storage.HashMapBasedStorage;

public class CacheFactory<Key,Value> {
    public LRUCache<Key, Value> createLRUCache(final int capacity){
        return new LRUCache(new LRUEvictionPolicy<Key>(), new HashMapBasedStorage<Key, Value>(capacity));
    }
}
