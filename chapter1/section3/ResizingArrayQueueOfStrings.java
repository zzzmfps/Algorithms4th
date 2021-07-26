package chapter1.section3;

import convention.QueueConv;

public class ResizingArrayQueueOfStrings<T> implements QueueConv<T> {

    private int begin, size;
    private int capacity;
    private T[] array;

    @SuppressWarnings("unchecked")
    public ResizingArrayQueueOfStrings(final int cap) {
        this.array = (T[]) new Object[cap];
        this.capacity = cap;
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
    public void offer(final T value) {
        if (this.capacity == this.size) {
            return;
        }
        this.array[(this.begin + this.size) % this.capacity] = value;
        ++this.size;
    }

    @Override
    public T peek() {
        return this.array[this.begin];
    }

    @Override
    public T poll() {
        if (this.isEmpty()) {
            return null;
        }
        final T ret = this.array[this.begin];
        this.begin = (1 + this.begin) % this.capacity;
        --this.size;
        return ret;
    }

    @SuppressWarnings("unchecked")
    public void resize(final int newSize) {
        if (0 >= newSize || this.capacity == newSize) {
            return;
        }
        final T[] newArray = (T[]) new Object[newSize];
        this.begin = 0;
        this.size = Math.min(this.size, newSize);
        for (int i = this.begin; i < this.size; ++i) {
            newArray[i] = this.array[(this.begin + i) % this.capacity];
        }
        this.capacity = newSize;
        this.array = newArray;
    }

}
