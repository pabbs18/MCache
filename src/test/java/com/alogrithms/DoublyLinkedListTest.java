package com.alogrithms;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
    @Test
    void testDllAddition(){
        DoublyLinkedLIstNode<Integer> node1 = new DoublyLinkedLIstNode<>(1);
        DoublyLinkedLIstNode<Integer> node2 = new DoublyLinkedLIstNode<>(2);
        DoublyLinkedLIstNode<Integer> node3 = new DoublyLinkedLIstNode<>(3);
        DoublyLinkedLIstNode<Integer> node4 = new DoublyLinkedLIstNode<>(4);

        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();

        dll.addNodeAtEnd(node1);
        verifyDll(dll, ImmutableList.of(1));

        dll.addNodeAtEnd(node2);
        verifyDll(dll, ImmutableList.of(1,2));

        dll.addNodeAtEnd(node3);
        verifyDll(dll, ImmutableList.of(1,2,3));

        dll.addNodeAtEnd(node4);
        verifyDll(dll, ImmutableList.of(1,2,3,4));

        dll.addElementAtEnd(5);
        verifyDll(dll, ImmutableList.of(1,2,3,4,5));
    }
    @Test
    void dllNodeDetachment(){
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();

        DoublyLinkedLIstNode<Integer> node1 = dll.addElementAtEnd(1);
        DoublyLinkedLIstNode<Integer> node2 = dll.addElementAtEnd(2);
        DoublyLinkedLIstNode<Integer> node3 = dll.addElementAtEnd(3);
        DoublyLinkedLIstNode<Integer> node4 = dll.addElementAtEnd(4);
        DoublyLinkedLIstNode<Integer> node5 = dll.addElementAtEnd(5);

        verifyDll(dll, ImmutableList.of(1,2,3,4,5));

        dll.detachNode(node1);
        verifyDll(dll, ImmutableList.of(2,3,4,5));

        dll.detachNode(node2);
        verifyDll(dll, ImmutableList.of(3,4,5));

        dll.detachNode(node3);
        verifyDll(dll, ImmutableList.of(4,5));

        dll.detachNode(node4);
        verifyDll(dll, ImmutableList.of(5));

        dll.detachNode(null);
        verifyDll(dll, ImmutableList.of(5));
    }


    void verifyDll(DoublyLinkedList<Integer> dll, List<Integer> expectedListElements){
        DoublyLinkedLIstNode<Integer> currentNode = dll.getFirstNode();

        for(Integer element: expectedListElements){
            assertNotNull(currentNode);
            assertEquals(element, currentNode.getElement());
            currentNode = currentNode.getNext();
        }
        assertNull(currentNode.next);
    }
}