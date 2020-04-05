package core;

import com.sun.source.tree.Tree;
import model.Message;
import shared.AbstractBinarySearchTree;
import shared.AbstractBinarySearchTree.Node;
import shared.AbstractBinaryTree;
import shared.DataTransferSystem;

import java.util.*;

public class MessagingSystem implements DataTransferSystem {
    private Queue<Message> messages;
    private int messagesSize;
    private AbstractBinarySearchTree<Message> bst;

    public MessagingSystem() {
        this.messages = new PriorityQueue<>();
        this.messagesSize = 0;
        this.bst = null;
    }

    @Override
    public void add(Message message) {
        Boolean contains = this.contains(message);

        this.messagesSize++;

        if (contains) {
            throw new IllegalArgumentException();
        }

        this.messages.add(message);

        if (this.bst == null) {
            this.bst = new BinarySearchTree<>(new Node<>(message));
        } else {
            bst.insert(message);
        }
    }

    @Override
    public Message getByWeight(int weight) {
        for (Message message : messages) {
            if (message.getWeight() == weight) {
                return message;
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public Message getLightest() {
        if (this.size() == 0) {
            throw new IllegalArgumentException();
        }

        return this.messages.peek();
    }

    @Override
    public Message getHeaviest() {
        if (this.size() == 0) {
            throw new IllegalArgumentException();
        }

        Queue<Message> tempMessages = new PriorityQueue<>(Comparator.comparingInt(Message::getWeight).reversed());

        tempMessages.addAll(this.messages);

        return tempMessages.peek();
    }

    @Override
    public Message deleteLightest() {
        if (this.size() == 0) {
            throw new IllegalArgumentException();
        }

        Message poll = this.messages.poll();
        this.messagesSize--;

        return poll;
    }

    @Override
    public Message deleteHeaviest() {
        if (this.size() == 0) {
            throw new IllegalArgumentException();
        }

        Queue<Message> tempMessages = new PriorityQueue<>(Comparator.comparingInt(Message::getWeight).reversed());

        tempMessages.addAll(this.messages);

        Message poll = tempMessages.poll();
        this.messagesSize--;

        return poll;
    }

    @Override
    public Boolean contains(Message message) {
        return this.messages.contains(message);
    }

    @Override
    public List<Message> getOrderedByWeight() {
        List<Message> messages = new ArrayList<>(this.messages);

        messages.sort(Comparator.comparingInt(Message::getWeight));

        return messages;
    }

    @Override
    public List<Message> getPostOrder() {
        return postOrder(this.bst.getRoot());
    }

    public List<Message> postOrder(Node<Message> currentNode) {
        List<Message> result = new ArrayList<>();

        if (currentNode.leftChild != null) {
            Node<Message> left = currentNode.leftChild;
            result.addAll(postOrder(left));
        }

        if (currentNode.rightChild != null) {
            Node<Message> right = currentNode.rightChild;
            result.addAll(postOrder(right));
        }

        result.add(currentNode.value);

        return result;
    }

    @Override
    public List<Message> getPreOrder() {
        return preOrder(this.bst.getRoot());
    }

    public List<Message> preOrder(Node<Message> currentNode) {
        List<Message> result = new ArrayList<>();

        result.add(currentNode.value);

        if (currentNode.leftChild != null) {
            Node<Message> left = currentNode.leftChild;
            result.addAll(postOrder(left));
        }

        if (currentNode.rightChild != null) {
            Node<Message> right = currentNode.rightChild;
            result.addAll(postOrder(right));
        }

        return result;
    }

    @Override
    public List<Message> getInOrder() {
        return inOrder(this.bst.getRoot());
    }

    public List<Message> inOrder(Node<Message> currentNode) {
        List<Message> result = new ArrayList<>();


        if (currentNode.leftChild != null) {
            Node<Message> left = currentNode.leftChild;
            result.addAll(postOrder(left));
        }

        result.add(currentNode.value);

        if (currentNode.rightChild != null) {
            Node<Message> right = currentNode.rightChild;
            result.addAll(postOrder(right));
        }

        return result;
    }

    @Override
    public int size() {
        return this.messagesSize;
    }
}
