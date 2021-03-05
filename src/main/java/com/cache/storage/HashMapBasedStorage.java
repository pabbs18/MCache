package com.cache.storage;

import com.cache.exceptions.NotFoundException;
import com.cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage<Key , Value > implements Storage<Key , Value >{
    private Map<Key, Value> map;
    private final Integer capacity;

    public HashMapBasedStorage(Integer capacity){
        this.capacity = capacity;
        map = new HashMap<>();
    }

    @Override
    public void add(Key key, Value value) throws StorageFullException {
        if(isStorageFull()) throw new StorageFullException("Capacity Full !!");
        map.put(key, value);
    }

    @Override
    public void remove(Key key) throws NotFoundException {
        if(!map.containsKey(key)) throw new NotFoundException(key + " doesn't exist !!");
        map.remove(key);
    }

    @Override
    public Value get(Key key) throws NotFoundException {
        if(!map.containsKey(key)) throw new NotFoundException(key + " doesn't exist !!");
        return map.get(key);
    }

    private boolean isStorageFull(){
        if(map.size() == capacity){
            return true;
        }
        return false;
    }
}
