package implementations;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void add() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.add(13);
        deque.add(14);
        deque.add(15);
        deque.add(16);
        deque.add(17);

    }

    @Test
    public void offer() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.offer(13);
        assertEquals(1, deque.size());
    }
    @Test
    public void addFirst() {

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addFirst(13);
        deque.addFirst(14);
        deque.addFirst(15);
        deque.addFirst(16);
        deque.addFirst(17);

    }

    @Test
    public void addLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(13);
        deque.addLast(14);
        deque.addLast(15);
        deque.addLast(16);
        deque.addLast(17);
    }

    @Test
    public void push() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.push(13);
        deque.push(14);
        deque.push(15);
        deque.push(16);
        deque.push(17);

        for (Integer integer : deque) {
            System.out.println(integer);
        }

    }

    @Test
    public void insert() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(13);
        deque.addLast(14);
        deque.addLast(15);
        deque.addLast(16);
        deque.addLast(17);


        deque.insert(8, 217);

        for (Integer integer : deque) {
            System.out.println(integer);
        }
    }

    @Test
    public void set() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(13);
        deque.addLast(14);
        deque.addLast(15);
        deque.addLast(16);
        deque.addLast(17);


        deque.set(4, 217);

        for (Integer integer : deque) {
            System.out.println(integer);
        }
    }

    @Test
    public void peek() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(13);
        deque.addLast(14);
        deque.addLast(15);
        deque.addLast(16);
        deque.addLast(17);


        Integer peek = deque.peek();

        System.out.println(peek);

        for (Integer integer : deque) {
            System.out.println(integer);
        }
    }

    @Test
    public void poll() {

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(13);
//        deque.addLast(14);
//        deque.addLast(15);
//        deque.addLast(16);
//        deque.addLast(17);

        Integer poll = deque.poll();

        System.out.println(poll);

        for (Integer integer : deque) {
            System.out.println(integer);
        }
    }

    @Test
    public void pop() {

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(13);
        deque.addLast(14);
        deque.addLast(15);
//        deque.addLast(16);
//        deque.addLast(17);

        Integer pop = deque.pop();

        System.out.println(pop);

        for (Integer integer : deque) {
            System.out.println(integer);
        }
    }

    @Test
    public void get() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(13);
        deque.addLast(14);
        deque.addLast(15);
        deque.addLast(16);
        deque.addLast(17);

        Integer pop = deque.get(1);

        System.out.println(pop);

        for (Integer integer : deque) {
            System.out.println(integer);
        }
    }

    @Test
    public void testGet() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(13);
        deque.addLast(14);
        deque.addLast(15);
        deque.addLast(16);
        deque.addLast(17);

        Integer get = deque.get(Integer.valueOf(14));

        System.out.println(get);

        for (Integer integer : deque) {
            System.out.println(integer);
        }
    }

    @Test
    public void remove() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(13);
        deque.addLast(14);
        deque.addLast(15);
        deque.addLast(16);
        deque.addLast(17);

        Integer remove = deque.remove(1);

        System.out.println(remove);

        for (Integer integer : deque) {
            System.out.println(integer);
        }
    }

    @Test
    public void testRemove() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(13);
//        deque.addLast(14);
//        deque.addLast(14);
//        deque.addLast(15);
//        deque.addLast(16);
//        deque.addLast(17);

        Integer remove = deque.remove(Integer.valueOf(13));

        System.out.println(remove);

        for (Integer integer : deque) {
            System.out.println(integer);
        }
    }

    @Test
    public void removeFirst() {
    }

    @Test
    public void removeLast() {
    }

    @Test
    public void size() {
    }

    @Test
    public void capacity() {
    }

    @Test
    public void trimToSize() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void iterator() {
    }
}