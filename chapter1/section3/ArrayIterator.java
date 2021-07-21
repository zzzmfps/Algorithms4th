package chapter1.section3;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {

    protected T[] array; // array.length is capacity
    private int current, count;

    public ArrayIterator(final T[] array, final int begin, final int size) {
        this.array = array;
        this.current = begin;
        this.count = size;
    }

    @Override
    public boolean hasNext() {
        return this.count > 0;
    }

    @Override
    public T next() {
        final T value = this.array[this.current];
        this.current = (this.current + 1) % this.array.length;
        --this.count;
        return value;
    }

}
