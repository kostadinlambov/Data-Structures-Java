package core;

import model.Task;
import shared.Scheduler;

import java.util.*;

public class ProcessScheduler implements Scheduler {
    Deque<Task> tasks;

    public ProcessScheduler() {
        tasks = new ArrayDeque<>();
    }

    @Override
    public void add(Task task) {
        this.tasks.add(task);
    }

    @Override
    public Task process() {
        return this.tasks.poll();
    }

    @Override
    public Task peek() {
        return this.tasks.peek();
    }

    @Override
    public Boolean contains(Task task) {
        return this.tasks.contains(task);
    }

    @Override
    public int size() {
        return this.tasks.size();
    }

    @Override
    public Boolean remove(Task task) {
        boolean remove = this.tasks.remove(task);

        if(!remove){
            throw new IllegalArgumentException("Failed to remove task!");
        }

        return true;
    }

    @Override
    public Boolean remove(int id) {
        for (Task task : tasks) {
            if(task.getId() == id){
                return this.tasks.remove(task);
            }
        }

        throw new IllegalArgumentException("Failed to remove task!");
    }

    @Override
    public void insertBefore(int id, Task task) {
        Deque<Task> result = new ArrayDeque<>();

        for (Task currentTask : tasks) {
            if(currentTask.getId() == id){
                result.add(task);
            }

            result.add(currentTask);
        }

        if(result.size() == this.size()){
            throw new IllegalArgumentException();
        }

        this.tasks = result;
    }

    @Override
    public void insertAfter(int id, Task task) {
        Deque<Task> result = new ArrayDeque<>();

        for (Task currentTask : tasks) {
            result.add(currentTask);

            if(currentTask.getId() == id){
                result.add(task);
            }

        }

        if(result.size() == this.size()){
            throw new IllegalArgumentException();
        }

        this.tasks = result;
    }

    @Override
    public void clear() {
        this.tasks.clear();
    }

    @Override
    public Task[] toArray() {
        Task[] taskArr = new Task[this.size()];

        return this.tasks.toArray(taskArr);
    }

    @Override
    public void reschedule(Task first, Task second) {
        Boolean containsFirst = this.contains(first);
        if(!containsFirst){
            throw new IllegalArgumentException();
        }

        Boolean containsSecond = this.contains(second);
        if(!containsSecond){
            throw new IllegalArgumentException();
        }

        Task temp = first;
        first = second;
        second = temp;
    }

    @Override
    public List<Task> toList() {
        return new ArrayList<>(this.tasks);
    }

    @Override
    public void reverse() {
        Deque<Task> tempDeq= new ArrayDeque<>();

        for (Task currentTask : tasks) {
            tempDeq.addFirst(currentTask);
        }

        this.tasks  = tempDeq;
    }

    @Override
    public Task find(int id) {
        for (Task task : tasks) {
            if(task.getId() == id){
                return task;
            }
        }

        throw new IllegalArgumentException("Failed to remove task!");
    }

    @Override
    public Task find(Task task) {
        for (Task currentTask : tasks) {
            if(currentTask.equals(task)){
                return task;
            }
        }

        throw new IllegalArgumentException("Failed to remove task!");
    }
}
