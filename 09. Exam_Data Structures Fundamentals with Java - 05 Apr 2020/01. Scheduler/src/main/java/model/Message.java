package model;

public interface Message extends Comparable<Message> {
    int getWeight();
    void setWeight(int weight);
    String getText();
    void setText(String text);
    int getParentId();
    void setParentId(int parentId);

    void addChild(Message child);

}
