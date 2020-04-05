package core;

import interfaces.Buffer;
import interfaces.Entity;
import model.BaseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Loader implements Buffer {
    private List<Entity> elements = new ArrayList<>();

    @Override
    public void add(Entity entity) {
        this.elements.add(entity);
    }

    @Override
    public Entity extract(int id) {
        for (int i = 0; i < this.elements.size(); i++) {
            Entity currentEntity = this.elements.get(i);
            if (currentEntity.getId() == id) {
                this.elements.remove(currentEntity);
                return currentEntity;
            }
            ;

        }

        return null;
    }

    @Override
    public Entity find(Entity entity) {
        int index = this.elements.indexOf(entity);
        if (index != -1) {
            return this.elements.get(index);
        }

        return null;
    }

    @Override
    public boolean contains(Entity entity) {
        return this.elements.contains(entity);
    }

    @Override
    public int entitiesCount() {
        return this.elements.size();
    }

    @Override
    public void replace(Entity oldEntity, Entity newEntity) {
        int indexOfOldEntity = this.elements.indexOf(oldEntity);
        if (indexOfOldEntity == -1) {
            throw new IllegalStateException("Entity not found");
        }

        this.elements.set(indexOfOldEntity, newEntity);
    }

    @Override
    public void swap(Entity first, Entity second) {
        int indexOfFirstEntity = this.elements.indexOf(first);
        if (indexOfFirstEntity == -1) {
            throw new IllegalStateException("Entities  not found");
        }

        int indexOfSecondEntity = this.elements.indexOf(second);
        if (indexOfSecondEntity == -1) {
            throw new IllegalStateException("Entities  not found");
        }

        Collections.swap(this.elements, indexOfFirstEntity, indexOfSecondEntity);
    }

    @Override
    public void clear() {
        this.elements.clear();
    }

    @Override
    public Entity[] toArray() {
        Entity[] tempArr = new Entity[this.elements.size()];

        return this.elements.toArray(tempArr);
    }

    @Override
    public List<Entity> retainAllFromTo(BaseEntity.Status lowerBound, BaseEntity.Status upperBound) {
        int ordinalLower = lowerBound.ordinal();
        int ordinalUpper = upperBound.ordinal();

        return this.elements
                .stream()
                .filter(element ->
                        element.getStatus().ordinal() >= ordinalLower &&
                                element.getStatus().ordinal() <= ordinalUpper)
                .collect(Collectors.toList());
    }

    @Override
    public List<Entity> getAll() {
        return new ArrayList<>(this.elements);
    }

    @Override
    public void updateAll(BaseEntity.Status oldStatus, BaseEntity.Status newStatus) {
        for (Entity currentEntity : this.elements) {
            if (currentEntity.getStatus().equals(oldStatus)) {
                currentEntity.setStatus(newStatus);
            }
        }
    }

    @Override
    public void removeSold() {

        this.elements.removeIf(next -> next.getStatus().equals(BaseEntity.Status.SOLD));
//        for (int i = 0; i < this.elements.size(); i++) {
//            Entity currentEntity = this.elements.get(i);
//
//        }
    }

    @Override
    public Iterator<Entity> iterator() {
        return this.elements.iterator();
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < this.elements.size();
    }
}
