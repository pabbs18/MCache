package com.cache.caches;

import com.cache.exceptions.NotFoundException;
import com.cache.factories.CacheFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {
    Cache<Integer, Integer> cache;
    @BeforeEach
    void setUp() {
        cache = new CacheFactory<Integer, Integer>().createLRUCache(3);
    }

    @Test
    public void testPutAndGet(){
        cache.put(1,1);
        cache.put(2,2);

        //accessing the least used element
        assertEquals(1, cache.get(1));

        cache.put(3,3);
        cache.get(3);

        //This step evicts the least used element i.e 2
        cache.put(4,4);
        //cache.get(2);

        //2 is already evicted
       assertNull(cache.get(2));
    }
}