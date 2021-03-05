package com.alogrithms.exceptions;

import lombok.Getter;

@Getter
public class DoublyLinkedLIstNode<E> {
    DoublyLinkedLIstNode<E> next;
    DoublyLinkedLIstNode<E> prev;
    E element;

    public DoublyLinkedLIstNode(E element) {
        this.next = null;
        this.prev = null;
        this.element = element;
    }
}
