package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E element) {
            this.value = element;
        }
    }

    public Queue() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> newElement = new Node<>(element);

        if (this.head == null) {
            this.head = newElement;
        } else {
            Node<E> current = this.head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newElement;
        }

        this.size++;
    }

    @Override
    public E poll() {
        checkIFQueueIsEmpty();

        Node<E> currentHead = this.head;
        this.head = currentHead.next;
        this.size--;

        return currentHead.value;
    }

    @Override
    public E peek() {
        checkIFQueueIsEmpty();

        return this.head.value;
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
            Node<E> currentHead = head;

            @Override
            public boolean hasNext() {
                return currentHead != null;
            }

            @Override
            public E next() {
                E value = this.currentHead.value;
                this.currentHead = this.currentHead.next;

                return value;
            }
        };
    }

    private void checkIFQueueIsEmpty() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
    }
}
