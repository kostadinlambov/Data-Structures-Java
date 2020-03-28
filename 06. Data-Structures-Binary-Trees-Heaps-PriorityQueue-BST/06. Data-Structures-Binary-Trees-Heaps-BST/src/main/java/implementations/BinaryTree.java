package implementations;

import interfaces.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    BinaryTree<E> leftChild;
    BinaryTree<E> rightChild;

    public BinaryTree(E key, BinaryTree<E> leftChild, BinaryTree<E> rightChild) {
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return this.leftChild;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return this.rightChild;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder result = new StringBuilder();

        String padding = this.createPadding(indent) + this.getKey();
        result.append(padding);

        if (this.getLeft() != null) {
            String preOrder = this.getLeft().asIndentedPreOrder(indent + 2);
            result.append(System.lineSeparator()).append(preOrder);
        }

        if (this.getRight() != null) {
            String preOrder = this.getRight().asIndentedPreOrder(indent + 2);
            result.append(System.lineSeparator()).append(preOrder);
        }

        return result.toString();
    }

    private String createPadding(int indent) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < indent; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();

        // PreOrder --> Adding the result BEFORE calling the two Recursions for LeftNode and RightNode
        result.add(this);

        if(this.getLeft() != null){
            result.addAll(this.getLeft().preOrder());
        }

        if(this.getRight() != null){
            result.addAll(this.getRight().preOrder());
        }

        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();

        if(this.getLeft() != null){
            result.addAll(this.getLeft().inOrder());
        }

        // inOrder --> Adding the result BETWEEN calling the two Recursions for LeftNode and RightNode
        result.add(this);

        if(this.getRight() != null){
            result.addAll(this.getRight().inOrder());
        }

        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();

        if(this.getLeft() != null){
            result.addAll(this.getLeft().postOrder());
        }

        if(this.getRight() != null){
            result.addAll(this.getRight().postOrder());
        }

        // PostOrder --> Adding the result AFTER calling the two Recursions for LeftNode and RightNode
        result.add(this);

        return result;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if(this.getLeft() != null){
            this.getLeft().forEachInOrder(consumer);
        }

        consumer.accept(this.getKey());

        if(this.getRight() != null){
            this.getRight().forEachInOrder(consumer);
        }
    }
}
