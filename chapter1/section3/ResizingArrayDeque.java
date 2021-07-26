package chapter1.section3;

import java.util.Iterator;

import convention.DequeConv;

public class ResizingArrayDeque<T> implements DequeConv<T>, Iterable<T> {

    private T[] array;
    private int begin, size;

    @SuppressWarnings("unchecked")
    public ResizingArrayDeque(final int capacity) {
        this.array = (T[]) new Object[Math.max(1, capacity)];
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
        for (int i = 0; i < this.size; ++i) {
            this.array[(i + this.begin) % this.array.length] = null;
        }
        this.begin = this.size = 0;
    }

    @Override
    public void offerLast(final T value) {
        if (this.size == this.array.length) {
            this.resize(2 * this.array.length);
        }
        int end = this.begin;
        if (this.size > 0) {
            end = (this.begin + this.size) % this.array.length;
        }
        ++this.size;
        this.array[end] = value;
    }

    @Override
    public T peekFirst() {
        return this.array[this.begin];
    }

    @Override
    public T pollFirst() {
        if (this.isEmpty()) {
            return null;
        }
        if (this.size == this.array.length / 4) {
            this.resize(this.array.length / 2);
        }
        final T value = this.array[this.begin];
        this.array[this.begin] = null;
        this.begin = (this.begin + 1) % this.array.length;
        --this.size;
        return value;
    }

    @Override
    public T peekLast() {
        return this.array[(this.begin + this.size - 1) % this.array.length];
    }

    @Override
    public void offerFirst(final T value) {
        if (this.size == this.array.length) {
            this.resize(2 * this.array.length);
        }
        if (this.size > 0) {
            this.begin = (this.begin + this.array.length - 1) % this.array.length;
        }
        ++this.size;
        this.array[this.begin] = value;
    }

    @Override
    public T pollLast() {
        if (this.isEmpty()) {
            return null;
        }
        if (this.size == this.array.length / 4) {
            this.resize(this.array.length / 2);
        }
        final int end = (this.begin + this.size - 1) % this.array.length;
        final T value = this.array[end];
        this.array[end] = null;
        --this.size;
        return value;
    }

    @SuppressWarnings("unchecked")
    private void resize(final int newSize) {
        final T[] newArray = (T[]) new Object[newSize];
        for (int i = 0; i < this.size; ++i) {
            newArray[i] = this.array[(this.begin + i) % this.array.length];
        }
        this.array = newArray;
        this.begin = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>(this.array, this.begin, this.size);
    }

}
