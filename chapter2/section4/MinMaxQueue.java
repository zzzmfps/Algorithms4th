package chapter2.section4;

import convention.ContainerConv;
import convention.base.AbstractSort.Mode;

public class MinMaxQueue<T extends Comparable<T>> implements ContainerConv<T> {

    private PriorityQueue<T> pq1 = new PriorityQueue<>(Mode.Less);
    private PriorityQueue<T> pq2 = new PriorityQueue<>(Mode.Greater);
    private int size;

    @Override
    public boolean isEmpty() {
        return 0 == this.size;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.pq1.clear();
        this.pq2.clear();
        this.size = 0;
    }

    public void insert(T value) {
        this.pq1.insert(value);
        this.pq2.insert(value);
        ++this.size;
    }

    public T peekMax() {
        return this.pq1.peek();
    }

    public T peekMin() {
        return this.pq2.peek();
    }

    public T deleteMax() {
        T max = this.pq1.delete();
        if (0 == --this.size) {
            this.clear();
        }
        return max;
    }

    public T deleteMin() {
        T min = this.pq2.delete();
        if (0 == --this.size) {
            this.clear();
        }
        return min;
    }

}
