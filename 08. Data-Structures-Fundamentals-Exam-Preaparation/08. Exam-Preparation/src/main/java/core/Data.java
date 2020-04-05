package core;

import interfaces.Entity;
import interfaces.Repository;

import java.util.*;
import java.util.stream.Collectors;


public class Data implements Repository {
    private Queue<Entity> data;
    private Entity root;

    public Data() {
        this.data = new PriorityQueue<>();
        this.root = null;
    }

    public Data(Data other) {
        this.root = other.root;
        this.data = new PriorityQueue<>(other.data);
    }

    @Override
    public void add(Entity entity) {
        if(this.root == null){
            this.root = entity;
        }else{
            this.getById(entity.getParentId()).addChild(entity);
        }

        this.data.offer(entity);
    }

    @Override
    public Entity getById(int id) {
        List<Entity> entities = new ArrayList<>(this.data);

        for (Entity entity : entities) {
            if (entity.getId() == id) {
                return entity;
            }
        }

        return null;
    }

    @Override
    public List<Entity> getByParentId(int id) {
        List<Entity> entities = new ArrayList<>(this.data);

        List<Entity> result = new ArrayList<>();

        for (Entity entity : entities) {
            if (entity.getParentId() == id) {
                result.add(entity);
            }
        }

        return result;
    }

    @Override
    public List<Entity> getAll() {
        return new ArrayList<>(this.data);
    }

    @Override
    public Repository copy() {
        return new Data(this);
    }

    @Override
    public List<Entity> getAllByType(String type) {
        if(!type.equals("User") && !type.equals("Invoice") && !type.equals("StoreClient")){
           throw new IllegalArgumentException ("Illegal type: " + type);
        }

        List<Entity> entities = new ArrayList<>(this.data);

        return entities.stream()
                .filter(entity -> entity.getClass().getSimpleName().equals(type))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Entity peekMostRecent() {
        if(this.size() == 0){
            throw new  IllegalStateException("Operation on empty Data");
        }

        return this.data.peek();
    }

    @Override
    public Entity pollMostRecent() {
        if(this.size() == 0){
            throw new  IllegalStateException("Operation on empty Data");
        }

        return this.data.poll();
    }

    @Override
    public int size() {
        return this.data.size();
    }
}
