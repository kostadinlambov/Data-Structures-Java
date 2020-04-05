package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextMessage implements Message {
    private int weight;
    private String text;
    private int parentId;
    private List<Message> children;

    public TextMessage(int weight, String text) {
        this.weight = weight;
        this.text = text;
        this.children = new ArrayList<>();
    }

    public TextMessage(int weight, String text, int parentId) {
        this.weight = weight;
        this.text = text;
        this.children = new ArrayList<>();
        this.parentId = parentId;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
       this.text = text;
    }

    @Override
    public int getParentId() {
        return this.parentId;
    }

    @Override
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public void addChild(Message child) {
        this.children.add(child);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextMessage that = (TextMessage) o;
        return weight == that.weight &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, text);
    }

//    @Override
//    public int compareTo(TextMessage o) {
//        return Integer.compare(this.weight, o.weight);
//    }

    @Override
    public int compareTo(Message o) {
        return Integer.compare(this.weight, o.getWeight());
    }
}
