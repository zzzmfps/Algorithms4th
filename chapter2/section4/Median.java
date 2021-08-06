package chapter2.section4;

import convention.PriorityQueueConv;
import convention.base.AbstractSort.Mode;

public class Median<T extends Comparable<T>> implements PriorityQueueConv<T> {

    private PriorityQueue<T> pq1 = new PriorityQueue<>(Mode.Less); // <= median
    private PriorityQueue<T> pq2 = new PriorityQueue<>(Mode.Greater); // >= median

    @Override
    public boolean isEmpty() {
        return this.pq1.isEmpty() && this.pq2.isEmpty();
    }

    @Override
    public int size() {
        return this.pq1.size() + this.pq2.size();
    }

    @Override
    public void clear() {
        this.pq1.clear();
        this.pq2.clear();
    }

    @Override
    public void insert(T value) {
        if (!this.pq1.isEmpty() && value.compareTo(this.pq1.peek()) <= 0) {
            this.pq1.insert(value);
        } else {
            this.pq2.insert(value);
        }
        this.rebalance();
    }

    @Override
    public T peek() { // peek median
        return this.pq1.peek();
    }

    @Override
    public T delete() {
        T median = this.pq1.delete();
        this.rebalance();
        return median;
    }

    private void rebalance() {
        if (this.pq1.size() - this.pq2.size() > 1) {
            this.pq2.insert(this.pq1.delete());
        } else if (this.pq1.size() < this.pq2.size()) {
            this.pq1.insert(this.pq2.delete());
        }
    }

}
