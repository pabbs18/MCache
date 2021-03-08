package com.cache.policies;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class LRUEvictionPolicyTest {
    private LRUEvictionPolicy<Integer> lruEvictionPolicy;

    @BeforeEach
    void setUp(){
        lruEvictionPolicy = new LRUEvictionPolicy<>();
    }

    @Test
    void testNoKeyToEvictInitially(){
        assertNull(lruEvictionPolicy.evictKey());
    }

    @Test
    void testKeyIsEvictedInTheOrderOfTheirAccess() {
        lruEvictionPolicy.keyIsAccessed(1);
        lruEvictionPolicy.keyIsAccessed(2);
        lruEvictionPolicy.keyIsAccessed(3);
        lruEvictionPolicy.keyIsAccessed(4);

        assertEquals(1, lruEvictionPolicy.evictKey());
        assertEquals(2, lruEvictionPolicy.evictKey());
        assertEquals(3, lruEvictionPolicy.evictKey());
        assertEquals(4, lruEvictionPolicy.evictKey());
    }

    @Test
    void TestReaccessingKeyPreventsItFromEviction() {
        lruEvictionPolicy.keyIsAccessed(1);
        lruEvictionPolicy.keyIsAccessed(2);
        lruEvictionPolicy.keyIsAccessed(3);
        lruEvictionPolicy.keyIsAccessed(4);
        lruEvictionPolicy.keyIsAccessed(5);
        lruEvictionPolicy.keyIsAccessed(3);
        lruEvictionPolicy.keyIsAccessed(2);
        lruEvictionPolicy.keyIsAccessed(1);

        assertEquals(4,lruEvictionPolicy.evictKey());
        assertEquals(5,lruEvictionPolicy.evictKey());
        assertEquals(3,lruEvictionPolicy.evictKey());
        assertEquals(2,lruEvictionPolicy.evictKey());
        assertEquals(1,lruEvictionPolicy.evictKey());
    }
}