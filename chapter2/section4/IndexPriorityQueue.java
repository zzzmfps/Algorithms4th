package chapter2.section4;

import convention.IndexPriorityQueueConv;
import convention.base.AbstractPriorityQueue;
import convention.base.AbstractSort.Mode;

public class IndexPriorityQueue<T extends Comparable<T>> extends AbstractPriorityQueue<T>
        implements IndexPriorityQueueConv<T> {

    private T[] values; // real values
    private int[] index; // index of values in array `heap`

    private int[] heap; // heap, index of values in array `values`

    public IndexPriorityQueue(int cap) {
        super();
        this.init(cap);
    }

    public IndexPriorityQueue(int cap, Mode mode) {
        super(mode);
        this.init(cap);
    }

    private void init(int cap) {
        cap = Math.max(1, cap);
        this.values = this.newComparableArray(cap);
        this.heap = new int[cap + 1];
        this.index = new int[cap];
    }

    @Override
    public void clear() {
        super.clear();
        this.values = this.newComparableArray(this.values.length);
    }

    @Override
    public void insert(int k, T value) {
        if (!this.contains(k) && this.size < this.values.length) {
            this.values[k] = value;
            this.heap[++this.size] = k;
            this.index[k] = this.size;
            this.swim(this.size);
        }
    }

    @Override
    public void change(int k, T value) {
        if (this.contains(k)) {
            this.values[k] = value;
            this.swim(this.index[k]);
            this.sink(this.index[k]);
        } else {
            this.insert(k, value);
        }
    }

    @Override
    public boolean contains(int k) {
        return null != this.values[k];
    }

    @Override
    public T peek() {
        return this.values[this.heap[1]];
    }

    @Override
    public int peekIndex() {
        if (this.isEmpty()) {
            return -1;
        }
        return this.heap[1];
    }

    @Override
    public int delete() {
        if (this.isEmpty()) {
            return -1;
        }
        int index = this.heap[1];
        this.exch(1, this.size--);
        this.values[index] = null;
        this.sink(1);
        return index;
    }

    @Override
    public void delete(int k) {
        if (this.contains(k)) {
            this.exch(this.index[k], this.size--);
            this.values[k] = null;
            this.sink(this.index[k]);
        }
    }

    @Override
    protected void swim(int i) {
        while (i > 1 && this.compare(i / 2, i)) {
            this.exch(i / 2, i);
            i /= 2;
        }
    }

    @Override
    protected void sink(int i) {
        while (2 * i <= this.size) {
            int j = 2 * i;
            if (j < this.size && this.compare(j, j + 1)) {
                ++j;
            }
            if (!this.compare(i, j)) {
                break;
            }
            this.exch(i, j);
            i = j;
        }
    }

    @Override
    protected boolean less(int i, int j) {
        return this.values[this.heap[i]].compareTo(this.values[this.heap[j]]) < 0;
    }

    @Override
    protected boolean greater(int i, int j) {
        return this.values[this.heap[i]].compareTo(this.values[this.heap[j]]) > 0;
    }

    @Override
    protected void exch(int i, int j) {
        int ki = this.heap[i], kj = this.heap[j];
        this.heap[i] = kj;
        this.heap[j] = ki;
        this.index[kj] = i;
        this.index[ki] = j;
    }

}
