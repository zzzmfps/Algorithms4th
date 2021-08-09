package chapter2.section5;

import chapter2.section4.PriorityQueue;
import convention.PriorityQueueConv;
import convention.base.AbstractSort.Mode;

public class StablePriorityQueue<T extends Comparable<T>> implements PriorityQueueConv<T> {

    private int counter = 0;
    private PriorityQueue<StableBox<T>> pq;

    public StablePriorityQueue(Mode mode) {
        this.pq = new PriorityQueue<>(mode);
    }

    @Override
    public boolean isEmpty() {
        return this.pq.isEmpty();
    }

    @Override
    public int size() {
        return this.pq.size();
    }

    @Override
    public void clear() {
        this.counter = 0;
        this.pq.clear();
    }

    @Override
    public void insert(T value) {
        this.pq.insert(new StableBox<T>(counter++, value));
    }

    @Override
    public T peek() {
        return this.pq.peek().getValue();
    }

    @Override
    public T delete() {
        return this.pq.delete().getValue();
    }

}
