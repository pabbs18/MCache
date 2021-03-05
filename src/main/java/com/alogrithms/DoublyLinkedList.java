package com.alogrithms.exceptions;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {
    DoublyLinkedLIstNode<E> dummyHead;
    DoublyLinkedLIstNode<E> dummyTail;

    public DoublyLinkedList(){
        dummyHead = new DoublyLinkedLIstNode<>(null);
        dummyTail = new DoublyLinkedLIstNode<>(null);

        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public void detachNode(DoublyLinkedLIstNode<E> node){
        if(node != null){
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void addNodeAtEnd(DoublyLinkedLIstNode<E> node){
        DoublyLinkedLIstNode<E> tailPrev = dummyTail.prev;
        tailPrev.next = node;
        node.next = dummyTail;
        dummyTail.prev = node;
        node.prev = tailPrev;
    }

    public DoublyLinkedLIstNode<E> addElementAtEnd(E element){
        if(element == null){
            throw new InvalidElementException;
        }
        DoublyLinkedLIstNode<E> newNode = new DoublyLinkedLIstNode<>(element);
        addNodeAtEnd(newNode);
        return newNode;
    }

    public boolean isListEmpty(){
        return dummyHead.next == dummyTail;
    }

    public DoublyLinkedLIstNode<E> getFirstNode() throws NoSuchElementException {
        if(isListEmpty()){
           return null;
        }
        return dummyHead.next;
    }

    public DoublyLinkedLIstNode<E> getLastNode() throws NoSuchElementException {
        if(isListEmpty()){
            return null;
        }
        return dummyTail.next;
    }

}
