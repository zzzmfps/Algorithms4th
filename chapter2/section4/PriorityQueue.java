package chapter2.section4;

import java.util.Arrays;

import convention.PriorityQueueConv;
import convention.base.AbstractPriorityQueue;
import convention.base.AbstractSort.Mode;
import edu.princeton.cs.algs4.StdOut;

public class PriorityQueue<T extends Comparable<T>> extends AbstractPriorityQueue<T> implements PriorityQueueConv<T> {

    private static final int MIN_LENGTH = 4;

    private boolean detail;
    private T[] heap; // heap[0] as sentinel

    public PriorityQueue() {
        super();
        this.heap = this.newComparableArray(PriorityQueue.MIN_LENGTH);
    }

    public PriorityQueue(Mode mode) {
        super(mode);
        this.heap = this.newComparableArray(PriorityQueue.MIN_LENGTH);
    }

    public PriorityQueue(int length, Mode mode) {
        super(mode);
        this.heap = this.newComparableArray(length + 1);
    }

    public PriorityQueue(T[] input, Mode mode) {
        super(mode);
        this.heap = this.newComparableArray(input);
        this.size = input.length;
        Heap.make(this.heap, 1, this.heap.length, mode);
    }

    public void requireDetails(boolean flag) {
        this.detail = flag;
    }

    @Override
    public void clear() {
        super.clear();
        this.heap = this.newComparableArray(PriorityQueue.MIN_LENGTH);
        if (this.detail) {
            StdOut.println("clear : " + this.toString());
        }
    }

    @Override
    public void insert(final T value) {
        this.insert(value, false);
    }

    public void insert(final T value, boolean useBinarySearch) {
        if (this.size + 1 == this.heap.length) {
            this.resize(2 * this.heap.length);
        }
        this.heap[++this.size] = value;
        if (useBinarySearch) {
            this.swimBinary(this.size);
        } else {
            this.swim(this.size);
        }
        if (this.detail) {
            StdOut.println("insert: " + this.toString());
        }
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.heap[1];
    }

    @Override
    public T delete() {
        return this.delete(false);
    }

    public T delete(boolean useFloyd) {
        if (this.isEmpty()) {
            return null;
        }
        final T value = this.heap[1];
        this.exch(1, this.size);
        this.heap[this.size--] = null;
        if (useFloyd) {
            this.sinkFloyd(1);
        } else {
            this.sink(1);
        }
        if (this.size < this.heap.length / 4 && this.heap.length > PriorityQueue.MIN_LENGTH) {
            this.resize(this.heap.length / 2);
        }
        if (this.detail) {
            StdOut.println("delete: " + this.toString());
        }
        return value;
    }

    @Override
    protected void swim(int i) {
        this.heap[0] = this.heap[i];
        while (i > 1 && this.compare(i / 2, 0)) {
            this.heap[i] = this.heap[i / 2];
            i /= 2;
        }
        this.heap[i] = this.heap[0];
    }

    private void swimBinary(int i) {
        int l = 0, mid, r = Double.valueOf(Math.log(i) / Math.log(2.0)).intValue();
        int[] ids = new int[r + 1];
        for (int j = ids.length - 1, k = i; j > -1; --j, k /= 2) {
            ids[j] = k;
        }
        while (l < r) {
            mid = l + (r - l) / 2;
            if (this.compare(ids[mid], i)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (l == ids.length - 1) {
            return;
        }
        this.heap[0] = this.heap[i];
        for (int j = ids.length - 1; j > l; --j) {
            this.heap[ids[j]] = this.heap[ids[j - 1]];
        }
        this.heap[ids[l]] = this.heap[0];
    }

    @Override
    protected void sink(int i) {
        this.heap[0] = this.heap[i];
        int j;
        while ((j = 2 * i) <= this.size) {
            if (j < this.size && this.compare(j, j + 1)) {
                ++j;
            }
            if (!this.compare(0, j)) {
                break;
            }
            this.heap[i] = this.heap[j];
            i = j;
        }
        this.heap[i] = this.heap[0];
    }

    private void sinkFloyd(int i) {
        this.heap[0] = this.heap[i];
        while (2 * i <= this.size) {
            int j = 2 * i;
            if (j < this.size && this.compare(j, j + 1)) {
                ++j;
            }
            this.heap[i] = this.heap[j];
            i = j;
        }
        while (i > 1 && this.compare(i / 2, 0)) {
            this.heap[i] = this.heap[i / 2];
            i /= 2;
        }
        this.heap[i] = this.heap[0];
    }

    @Override
    protected boolean less(final int i, final int j) {
        return this.heap[i].compareTo(this.heap[j]) < 0;
    }

    @Override
    protected boolean greater(final int i, final int j) {
        return this.heap[i].compareTo(this.heap[j]) > 0;
    }

    @Override
    protected void exch(final int i, final int j) {
        final T value = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = value;
    }

    private void resize(final int newSize) {
        final T[] newHeap = this.newComparableArray(newSize);
        for (int i = 1; i <= this.size; ++i) {
            newHeap[i] = this.heap[i];
        }
        this.heap = newHeap;
    }

    @Override
    public String toString() {
        return "PriorityQueue [size=" + this.size + ", heap=" + Arrays.toString(this.heap) + "]";
    }

}
