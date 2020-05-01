package model;


import shared.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key) {
        this.key = key;
        this.children = new ArrayList<>();
    }

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.parent = null;
        this.children = new ArrayList<>();

        for (Tree<E> subtree : children) {
            this.children.add(subtree);
            subtree.parent = this;
        }
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder sb = new StringBuilder();

        return createStringFromTree(this, 0, sb);
    }

    private String createStringFromTree(Tree<E> currentNode, int spacesCount, StringBuilder sb) {
        String replace = new String(new char[spacesCount]).replace("\0", " ");

        sb.append(replace)
                .append(currentNode.getKey())
                .append(System.lineSeparator());

        spacesCount += 2;

        for (Tree<E> child : currentNode.children) {
            createStringFromTree(child, spacesCount, sb);
        }

        return sb.toString().trim();
    }

    @Override
    public List<E> getLeafKeys() {
        List<E> result = new ArrayList<>();

        getLeafNodes(this, result);

        return result;
    }

    private void getLeafNodes(Tree<E> currentNode, List<E> result) {
        if (currentNode.children.size() == 0) {
            result.add(currentNode.getKey());
        }

        for (Tree<E> child : currentNode.children) {
            getLeafNodes(child, result);
        }
    }

    @Override
    public List<E> getMiddleKeys() {
        List<E> result = new ArrayList<>();

        getMiddleNodes(this, result);

        return result;
    }

    private void getMiddleNodes(Tree<E> currentNode, List<E> result) {
        if (currentNode.children.size() > 0 && currentNode.parent != null) {
            result.add(currentNode.getKey());
        }

        for (Tree<E> child : currentNode.children) {
            getMiddleNodes(child, result);
        }
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        Map<Integer, Tree<E>> treeMap = new TreeMap<>();
        int depth = 0;

        findDeepestNode(this, treeMap, depth);

        return treeMap.get(treeMap.size() - 1);
    }

    private void findDeepestNode(Tree<E> currentNode, Map<Integer, Tree<E>> treeMap, int depth) {

        if (!treeMap.containsKey(depth)) {
            treeMap.put(depth, currentNode);
        }

        depth++;

        for (Tree<E> child : currentNode.children) {
            findDeepestNode(child, treeMap, depth);
        }
    }

    @Override
    public List<E> getLongestPath() {
        Tree<E> deepestLeftmostNode = getDeepestLeftmostNode();

        List<E> result = new ArrayList<>();

        findLongestPathReversed(deepestLeftmostNode, result);

        Collections.reverse(result);

        return result;
    }

    private void findLongestPathReversed(Tree<E> currentNode, List<E> result) {
        result.add(currentNode.getKey());

        while (currentNode.parent != null) {
            Tree<E> parent = currentNode.parent;
            result.add(parent.getKey());
            currentNode = parent;
        }
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<List<E>> result = new ArrayList<>();
        List<E> currentPath = new ArrayList<>();

        int[] currentSum = new int[]{0};

        findAllPathsWithGivenSum(this, result, currentPath, sum, currentSum);

        return result;
    }

    private void findAllPathsWithGivenSum(Tree<E> currentNode, List<List<E>> result, List<E> currentPath, int sum, int[] currentSum) {
        currentSum[0] += (Integer) currentNode.key;

        currentPath.add(currentNode.getKey());

        if (currentNode.children.size() == 0) {
            if (currentSum[0] == sum) {
                List<E> path = new ArrayList<>(currentPath);
                result.add(path);
            }
        }

        for (Tree<E> child : currentNode.children) {
            findAllPathsWithGivenSum(child, result, currentPath, sum, currentSum);
        }

        currentSum[0] -= (Integer) currentNode.key;
        currentPath.remove(currentNode.key);
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> result = new ArrayList<>();

        findAllSubtreesWithGivenSum(this, result, sum);

        return result;
    }

    private void findAllSubtreesWithGivenSum(Tree<E> currentNode, List<Tree<E>> result, int sum) {
        int[] currentSum = new int[]{0};

        findSumOfCurrentTree(currentNode, currentSum);

        if(currentSum[0] == sum){
            result.add(currentNode);
        }

        for (Tree<E> child : currentNode.children) {
            findAllSubtreesWithGivenSum(child, result, sum);
        }
    }

    private void findSumOfCurrentTree(Tree<E> currentNode, int[] currentSum) {
        currentSum[0] += (Integer) currentNode.getKey();

        for (Tree<E> child : currentNode.children) {
            findSumOfCurrentTree(child, currentSum);
        }
    }
}



