package convention.base;

import convention.ContainerConv;
import convention.base.AbstractSort.Mode;

public abstract class AbstractPriorityQueue<T> implements ContainerConv<T> {

    protected Mode mode;
    protected int size;

    public AbstractPriorityQueue() {
        this.mode = Mode.Less; // max heap
    }

    public AbstractPriorityQueue(final Mode mode) {
        this.mode = mode;
    }

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
        this.size = 0;
    }

    protected abstract void swim(int i);

    protected abstract void sink(int i);

    protected boolean compare(final int i, final int j) {
        if (this.mode == Mode.Less) {
            return this.less(i, j);
        }
        return this.greater(i, j);
    }

    protected abstract boolean less(final int i, final int j);

    protected abstract boolean greater(final int i, final int j);

    protected abstract void exch(final int i, final int j);

    @SuppressWarnings("unchecked")
    protected T[] newComparableArray(final int length) {
        return (T[]) new Comparable[length];
    }

    protected T[] newComparableArray(final T[] input) {
        T[] array = this.newComparableArray(input.length + 1);
        for (int i = 1; i < array.length; ++i) {
            array[i] = input[i - 1];
        }
        return array;
    }

}
