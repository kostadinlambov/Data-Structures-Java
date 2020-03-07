package implementations;

import interfaces.List;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_SIZE = 16;
    private Object[] elements;
    private int capacity;
    private int size;

    public ArrayList() {
        this.elements = new Object[INITIAL_SIZE];
        this.capacity = INITIAL_SIZE;
        this.size = 0;
    }

    @Override
    public boolean add(E element) {
        if (this.size == capacity) {
            grow();
        }

        this.elements[size++] = element;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        checkIndex(index);

        if (this.size == capacity) {
            grow();
        }

        shiftRight(index);

        this.elements[index] = element;
        size++;

        return true;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return (E) this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        Object previousElement = this.elements[index];

        this.elements[index] = element;

        return (E) previousElement;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        if (this.size <  this.elements.length / 3 ) {
            shrink();
        }

        Object elementToRemove = this.elements[index];

        shiftLeft(index);

        size--;

        return (E) elementToRemove;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {
            if (element.equals(this.elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < this.size; i++) {
            if (element.equals(this.elements[i])) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private void grow() {
        this.capacity = this.capacity * 2;

        Object[] temp = new Object[this.capacity];
        for (int i = 0; i < this.elements.length; i++) {
            temp[i] = this.elements[i];
        }

        this.elements = temp;
    }

    private void shrink() {
        this.capacity = this.capacity / 2;

        Object[] temp = new Object[this.capacity];

        for (int i = 0; i < capacity; i++) {
            temp[i] = this.elements[i];
        }

        this.elements = temp;
    }

    private void shiftRight(int index) {
        for (int i = this.size - 1; i >= index; i--) {
            this.elements[i + 1] = this.elements[i];
        }
    }

    private void shiftLeft(int index) {
        for (int i = index; i < size; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        this.elements[size - 1] = null;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
