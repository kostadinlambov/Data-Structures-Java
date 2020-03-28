package implementations;

import interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {
    private E value;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E value, Tree<E>... subtrees) {
        this.value = value;
        this.parent = null;
        this.children = new ArrayList<>();

        for (Tree<E> subtree : subtrees) {
            this.children.add(subtree);
            subtree.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();

        if (this.value == null) {
            return result;
        }

        Deque<Tree<E>> childrenQueue = new ArrayDeque<>();

        childrenQueue.offer(this);

        while (!childrenQueue.isEmpty()) {
            Tree<E> currentNode = childrenQueue.poll();

            result.add(currentNode.value);

            for (Tree<E> child : currentNode.children) {
                childrenQueue.offer(child);
            }
        }

        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();

        if (this.value == null) {
            return result;
        }

        this.doDfs(this, result);

        return result;
    }

    @Override
    public List<E> orderStackDfs() {
        List<E> result = new ArrayList<>();

        if (this.value == null) {
            return result;
        }

        Deque<Tree<E>> toTraverse = new ArrayDeque<>();

        toTraverse.push(this);

        while (!toTraverse.isEmpty()) {
            Tree<E> currentNode = toTraverse.pop();

            for (Tree<E> node : currentNode.children) {
                toTraverse.push(node);
            }

            result.add(currentNode.value);
        }

        return result;
    }

    private void doDfs(Tree<E> currentNode, List<E> result) {

        for (Tree<E> child : currentNode.children) {
            doDfs(child, result);
        }

        result.add(currentNode.value);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        // DFS - recursive
        Tree<E> search = findElement(this, parentKey);

//        // BFS - iterative
////        Tree<E> search = find(parentKey);

        if (search == null) {
            throw new IllegalStateException();
        }

        search.children.add(child);
        child.parent = search;
    }

    // BFS - iterative
    private Tree<E> find(E parentKey) {
        Deque<Tree<E>> childrenQueue = new ArrayDeque<>();

        childrenQueue.offer(this);

        while (!childrenQueue.isEmpty()) {
            Tree<E> currentNode = childrenQueue.poll();

            if (currentNode.value.equals(parentKey)) {
                return currentNode;
            }

            for (Tree<E> child : currentNode.children) {
                childrenQueue.offer(child);
            }
        }

        return null;
    }

    // DFS - recursive
    private Tree<E> findElement(Tree<E> current, E parentKey) {

        if (current.value.equals(parentKey)) {
            return current;
        }

        for (Tree<E> child : current.children) {
            Tree<E> found = findElement(child, parentKey);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> toRemove = find(nodeKey);

        if (toRemove == null) {
            throw new IllegalArgumentException();
        }

        for (Tree<E> child : toRemove.children) {
            child.parent = null;
        }

        toRemove.children.clear();

        Tree<E> parent = toRemove.parent;

        if (parent != null) {
            parent.children.remove(toRemove);
        }

        toRemove.value = null;
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstNode = find(firstKey);
        Tree<E> secondNode = find(secondKey);

        if (firstNode == null || secondNode == null) {
            throw new IllegalArgumentException();
        }

        Tree<E> firstNodeParent = firstNode.parent;
        Tree<E> secondNodeParent = secondNode.parent;

        if (firstNodeParent == null) {
            swapRoot(secondNode);
            return;
        } else if (secondNodeParent == null) {
            swapRoot(firstNode);
            return;
        }

        secondNode.parent = firstNodeParent;
        firstNode.parent = secondNodeParent;

        // Add secondNode to firstNodeParent
        int firstIndex = firstNodeParent.children.indexOf(firstNode);
        int secondIndex = secondNodeParent.children.indexOf(secondNode);

        firstNodeParent.children.set(firstIndex, secondNode);
        secondNodeParent.children.set(secondIndex, firstNode);
    }

    private void swapRoot(Tree<E> node) {
        this.value = node.value;
        this.parent = null;
        this.children = node.children;
        node.parent = null;
    }
}



