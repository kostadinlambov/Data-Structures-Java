package core;



import shared.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<Message> implements AbstractBinaryTree<Message> {
    private Message key;
    BinaryTree<Message> leftChild;
    BinaryTree<Message> rightChild;

    public BinaryTree(Message key, BinaryTree<Message> leftChild, BinaryTree<Message> rightChild) {
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public BinaryTree() {
        this.key = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    @Override
    public Message getKey() {
        return this.key;
    }

    @Override
    public BinaryTree<Message> getLeft() {
        return this.leftChild;
    }

//    public void setLeft(BinaryTree<Message> element ) {
//        BinaryTree<BinaryTree<Message>> binaryTreeBinaryTree = new BinaryTree<>(element, null, null);
//        this.leftChild.getLeft(). == binaryTreeBinaryTree;
//    }


    @Override
    public BinaryTree<Message> getRight() {
        return this.rightChild;
    }



    @Override
    public void setKey(Message key) {
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
    public List<AbstractBinaryTree<Message>> preOrder() {
        List<AbstractBinaryTree<Message>> result = new ArrayList<>();

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
    public List<AbstractBinaryTree<Message>> inOrder() {
        List<AbstractBinaryTree<Message>> result = new ArrayList<>();

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
    public List<AbstractBinaryTree<Message>> postOrder() {
        List<AbstractBinaryTree<Message>> result = new ArrayList<>();

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
}
