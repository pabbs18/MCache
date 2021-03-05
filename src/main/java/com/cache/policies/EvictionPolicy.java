package com.cache.policies;

public interface EvictionPolicy<Key> {
    void keyIsAccessed(Key key);

    Key evictKey();
}
