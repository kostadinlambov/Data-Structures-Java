package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private int size;


    private static class Node<E> {
        private Node<E> next;
        private E value;

        public Node(E element) {
            this.value = element;
        }
    }

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> currentElement = new Node<>(element);

        Node<E> currentHead = this.head;

        this.head = currentElement;
        this.head.next = currentHead;

        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> currentElement = new Node<>(element);

        Node<E> currentHead = this.head;

        if (currentHead == null) {
            this.head = currentElement;
        } else {
            while (currentHead.next != null) {
                currentHead = currentHead.next;
            }

            currentHead.next = currentElement;
        }

        this.size++;
    }

    @Override
    public E removeFirst() {
        checkIfListIsEmpty();

        Node<E> currentHead = this.head;
        this.head = currentHead.next;

        return currentHead.value;
    }

    @Override
    public E removeLast() {
        checkIfListIsEmpty();

        Node<E> currentHead = this.head;
        Node<E> previous = null;

        if(currentHead.next != null){
            while (currentHead.next != null) {
                previous = currentHead;
                currentHead = currentHead.next;
            }

            previous.next = null;
        }

        return currentHead.value;
    }

    @Override
    public E getFirst() {
        checkIfListIsEmpty();

        return this.head.value;
    }

    @Override
    public E getLast() {
        checkIfListIsEmpty();

        Node<E> currentHead = this.head;

        while(currentHead.next != null){
            currentHead = currentHead.next;
        }

        return currentHead.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> currentHead;

            @Override
            public boolean hasNext() {
                return this.currentHead != null;
            }

            @Override
            public E next() {
                E value = this.currentHead.value;
                this.currentHead = this.currentHead.next;

                return value;
            }
        };
    }

    private void checkIfListIsEmpty() {
        if (this.isEmpty()) {
            throw new IllegalStateException("List ist empty!");
        }
    }
}
