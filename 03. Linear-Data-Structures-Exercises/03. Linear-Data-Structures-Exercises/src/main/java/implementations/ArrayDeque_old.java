package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque_old<E> implements Deque<E> {
    private final int INITIAL_CAPACITY = 7;
    private int size;
    private int tail;
    private int head;
    private Object[] elements;

    public ArrayDeque_old() {
        this.elements = new Object[INITIAL_CAPACITY];
        int middle = INITIAL_CAPACITY / 2;
        head = tail = middle;
    }

    @Override
    public void add(E element) {
        if (this.size == 0) {
            this.elements[this.tail] = element;
        } else {
            if (this.tail == this.elements.length - 1) {
                this.elements = grow();
            }
            this.elements[++this.tail] = element;
        }

        this.size++;
    }

    @Override
    public void offer(E element) {
        add(element);
    }

    @Override
    public void addFirst(E element) {
        if (this.size == 0) {
            this.elements[this.head] = element;
        } else {
            if (this.head == 0) {
                this.elements = grow();
            }
            this.elements[--this.head] = element;
        }

        this.size++;
    }

    @Override
    public void addLast(E element) {
        add(element);
    }

    @Override
    public void push(E element) {
        addLast(element);
    }

    @Override
    public void insert(int index, E element) {
        index += this.head;

        checkIfIndexIsValid(index);

        shiftRight(index);

        this.elements[index] = element;

        this.size++;
    }

    @Override
    public void set(int index, E element) {
        index += this.head;
        checkIfIndexIsValid(index);

        this.elements[index] = element;
    }

    @Override
    public E peek() {
        if(!this.isEmpty()){
            return getAt(this.tail);
        }

        return null;
    }

    @Override
    public E poll() {
        if (this.isEmpty()) {
            return null;
        }

        E element = getAt(this.head);

        this.elements[this.head] = null;
        if (this.tail != this.head) {
            this.head++;
        }

        this.size--;

        return element;
    }

    @Override
    public E pop() {
        if (this.size == 0) {
            return null;
        }

        E element =  getAt(this.tail);

        this.elements[this.tail] = null;

        if (this.tail != this.head) {
            this.tail--;
        }

        this.size--;

        return element;
    }

    @Override
    public E get(int index) {
        index += this.head;

        checkIfIndexIsValid(index);

        return getAt(index);
    }

    @Override
    public E get(Object object) {
        for (int i = head; i <= tail; i++) {
            if (this.elements[i].equals(object))
                return getAt(i);
        }

        return null;
    }

    @Override
    public E remove(int index) {
        index += this.head;

        checkIfIndexIsValid(index);

        E element = getAt(index);

        shiftLeft(index);

        return element;
    }

    @Override
    public E remove(Object object) {
        for (int i = head; i <= tail; i++) {
            if (this.elements[i].equals(object)) {
                E element = getAt(i);
                shiftLeft(i);
                return element;
            }
        }

        return null;
    }

    @Override
    public E removeFirst() {
        return poll();
    }

    @Override
    public E removeLast() {
        return pop();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public void trimToSize() {
        if (this.size == 0) {
            this.elements = null;
        } else {
            Object[] newElements = new Object[this.size];

            int index = 0;
            for (int i = head; i <= tail; i++) {
                newElements[index] = this.elements[i];
                index++;
            }

            this.elements = newElements;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = head;

            @Override
            public boolean hasNext() {
                return index <= tail;
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private Object[] grow() {
        int newCapacity = this.elements.length * 2 + 1;

        Object[] newElements = new Object[newCapacity];

        int middle = newCapacity / 2;
        int begin = middle - this.size / 2;
        int index = this.head;

        for (int i = begin; index <= this.tail; i++) {
            newElements[i] = this.elements[index++];

        }

        this.head = begin;
        this.tail = this.head + this.size - 1;

        return newElements;
    }

    private void shiftRight(int index) {
        if (this.tail == this.elements.length - 1) {
            this.elements = grow();
        }

        for (int i = tail; i >= index; i--) {
            this.elements[i +1] = elements[i];
        }

        this.tail++;
    }

    private void shiftLeft(int index) {
        for (int i = index; i <= tail; i++) {
            this.elements[i] = elements[i + 1];
        }

        this.elements[this.tail] = null;
        if (this.head != this.tail) {
            this.tail--;
            this.size--;
        }

    }

    private void checkIfIndexIsValid(int index) {
        if (index < head || index > tail) {
            throw new IndexOutOfBoundsException("Invalid index!");
        }
    }

    @SuppressWarnings("unchecked")
    private E getAt(int index){
        return  (E) this.elements[index];
    }
}
