package com.cache.caches;

import com.cache.exceptions.NotFoundException;
import com.cache.exceptions.StorageFullException;
import com.cache.policies.EvictionPolicy;
import com.cache.storage.Storage;

public class LRUCache<Key, Value> implements Cache<Key,Value> {
    private final EvictionPolicy<Key> evictionPolicy;
    private final Storage<Key, Value> storage;

    public LRUCache(EvictionPolicy<Key> evictionPolicy, Storage<Key,Value> storage){
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    @Override
    public void put(Key key, Value value){
        try{
            storage.add(key,value);
            evictionPolicy.keyIsAccessed(key);
        }catch(StorageFullException storageFullException){
            System.out.println("Storage Full. Trying to make space...");
           Key keyToBeEvicted = evictionPolicy.evictKey();
           if(keyToBeEvicted == null){
               throw new IllegalStateException("Reached unexpected state: Storage full and still unable to evict");
           }
           storage.remove(keyToBeEvicted);
           put(key, value);
        }
    }

    @Override
    public Value get(Key key){
        try{
            Value value = storage.get(key);
            evictionPolicy.keyIsAccessed(key);
            return value;
        }catch(NotFoundException notFoundException){
            System.out.println("Key doesn't exist ");
            return null;
        }
    }
}
