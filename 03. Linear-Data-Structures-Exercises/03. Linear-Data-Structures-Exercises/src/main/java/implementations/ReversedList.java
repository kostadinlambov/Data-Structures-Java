package implementations;

import interfaces.IReversedList;

import java.util.Iterator;

public class ReversedList<E> implements IReversedList<E> {
    private static final int INITIAL_CAPACITY = 2;
    private Object[] elements;
    private int size;
    private int capacity;

    public ReversedList() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
        this.capacity = INITIAL_CAPACITY;
    }

    @Override
    public void add(E element) {
        if (this.size == this.capacity) {
            grow();
        }

        this.elements[size++] = element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public E get(int index) {
//        if(this.size == 0){
//            return null;
//        }

        int actualIndex = this.size - 1 - index ;
        checkIndex(actualIndex);

        return (E) this.elements[actualIndex];
    }

    @Override
    public void removeAt(int index) {
//        if(this.size == 0){
//            return;
//        }

        int actualIndex = this.size - 1 - index ;
        checkIndex(actualIndex);

        shiftLeft(index);
    }

    private void shiftLeft(int index) {
        for (int i = index + 1; i < elements.length; i++) {
            this.elements[i - 1] = this.elements[i];
        }

        this.size--;
        this.elements[this.size] = null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = size - 1;

            @Override
            public boolean hasNext() {
                return this.index >= 0;
            }

            @Override
            public E next() {
                return get(index--);
            }
        };
    }

    private void grow() {
        capacity *= 2;
        Object[] newElements = new  Object[capacity];

        for (int i = 0; i < this.elements.length; i++) {
            newElements[i] = this.elements[i];
        }

        this.elements = newElements;
    }

    private void checkIndex(int index) {
        if(index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }
}
