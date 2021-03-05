package com.cache.policies;

import com.alogrithms.exceptions.DoublyLinkedLIstNode;
import com.alogrithms.exceptions.DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key>{

    private DoublyLinkedList<Key> dll;
    private Map<Key, DoublyLinkedLIstNode<Key>> key_NodeMapper;

    public LRUEvictionPolicy(){
        dll = new DoublyLinkedList<>();
        key_NodeMapper = new HashMap<>();
    }

    @Override
    public void keyIsAccessed(Key key) {
        if(key_NodeMapper.containsKey(key)){
            DoublyLinkedLIstNode<Key> dllNode =  key_NodeMapper.get(key);
            dll.detachNode(dllNode);
            dll.addNodeAtEnd(dllNode);
        }else{
            DoublyLinkedLIstNode<Key> newDllNode = new DoublyLinkedLIstNode<>(key);
            key_NodeMapper.put(key, newDllNode);
            dll.addNodeAtEnd(newDllNode);
        }
    }

    @Override
    public Key evictKey() {
        DoublyLinkedLIstNode<Key> firstNode = dll.getFirstNode();
        if(firstNode == null){
            return null;
        }
        dll.detachNode(firstNode);
        key_NodeMapper.remove(firstNode.getElement());
        return firstNode.getElement();
    }
}
