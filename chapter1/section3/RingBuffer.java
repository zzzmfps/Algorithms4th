package chapter1.section3;

public class RingBuffer<T> {

    private final T[] buffer;
    private int head, size;
    private final int N;

    @SuppressWarnings("unchecked")
    public RingBuffer(final int n) {
        this.buffer = (T[]) new Object[n];
        this.N = n;
    }

    public boolean isEmpty() {
        return 0 == this.size;
    }

    public boolean isFull() {
        return this.N == this.size;
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
