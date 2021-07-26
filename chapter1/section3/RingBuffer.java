package chapter1.section3;

import convention.ContainerConv;

public class RingBuffer<T> implements ContainerConv<T> {

    private final T[] buffer;
    private int head, size;

    @SuppressWarnings("unchecked")
    public RingBuffer(final int n) {
        this.buffer = (T[]) new Object[n];
    }

    @Override
    public boolean isEmpty() {
        return 0 == this.size;
    }

    public boolean isFull() {
        return this.buffer.length == this.size;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; ++i) {
            this.buffer[(i + this.head) % this.buffer.length] = null;
        }
        this.size = 0;
    }

    public T consume() {
        if (this.isEmpty()) {
            return null;
        }
        final T value = this.buffer[this.head];
        this.head = (this.head + 1) % this.buffer.length;
        --this.size;
        return value;
    }

    public T produce(final T value) {
        if (this.isFull()) {
            return null;
        }
        this.buffer[(this.head + this.size) % this.buffer.length] = value;
        ++this.size;
        return value;
    }

}
