package core;

import model.Order;
import shared.Shop;

import java.util.*;

public class OnlineShop implements Shop {
    List<Order> orderList;

    public OnlineShop() {
        this.orderList = new ArrayList<>();
    }

    @Override
    public void add(Order order) {
        this.orderList.add(order);
    }

    @Override
    public Order get(int index) {
        checkIndex(index);

        return this.orderList.get(index);
    }

    @Override
    public int indexOf(Order order) {
        return this.orderList.indexOf(order);
    }

    @Override
    public Boolean contains(Order order) {
        return this.orderList.contains(order);
    }

    @Override
    public Boolean remove(Order order) {
        return this.orderList.remove(order);
    }

    @Override
    public Boolean remove(int id) {
        for (Order currentOrder : this.orderList) {
            if (currentOrder.getId() == id) {
                boolean remove = this.orderList.remove(currentOrder);
                return remove;
            }
        }

        return false;
    }

    @Override
    public void set(int index, Order order) {
        checkIndex(index);

        this.orderList.set(index, order);
    }

    @Override
    public void set(Order oldOrder, Order newOrder) {
        // TODO: performance optimization

        boolean contains = this.orderList.contains(oldOrder);

        if (!contains) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < this.orderList.size(); i++) {
            Order currentOrder = this.orderList.get(i);
            if (currentOrder.equals(oldOrder)) {
                this.orderList.set(i, newOrder);
                return;
            }
        }
    }

    @Override
    public void clear() {
        this.orderList.clear();
    }

    @Override
    public Order[] toArray() {
        Order[] containerArr = new Order[this.orderList.size()];

        return this.orderList.toArray(containerArr);
    }

    @Override
    public void swap(Order first, Order second) {
        int indexOfFirst = this.orderList.indexOf(first);

        if (indexOfFirst == -1) {
            throw new IndexOutOfBoundsException();
        }

        int indexOfSecond = this.orderList.indexOf(second);

        if (indexOfSecond == -1) {
            throw new IndexOutOfBoundsException();
        }

        Collections.swap(this.orderList, indexOfFirst, indexOfSecond);
    }

    @Override
    public List<Order> toList() {
        return new ArrayList<>(this.orderList);
    }

    @Override
    public void reverse() {
        List<Order> reversedOrderList = new ArrayList<>();

        for (int i = this.orderList.size() - 1; i >= 0; i--) {
            Order currentOrder = this.orderList.get(i);
            reversedOrderList.add(currentOrder);
        }

        this.orderList = new ArrayList<>(reversedOrderList);
    }

    @Override
    public void insert(int index, Order order) {
        checkIndex(index);

        if (this.orderList.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        Deque<Order> deque = new ArrayDeque<>();

        for (int i = 0; i < this.orderList.size(); i++) {
            Order currentOrder = this.orderList.get(i);

            if (i == index) {
                deque.addLast(order);
            }

            deque.addLast(currentOrder);
        }

        this.orderList = new ArrayList<>(deque);
    }

    @Override
    public Boolean isEmpty() {
        return this.orderList.isEmpty();
    }

    @Override
    public int size() {
        return this.orderList.size();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.orderList.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

}
