package chapter1.section3;

public class ResizingArrayQueueOfStrings<T> {

    private int begin, size;
    private int capacity;
    private T[] array;

    @SuppressWarnings("unchecked")
    public ResizingArrayQueueOfStrings(final int cap) {
        this.array = (T[]) new Object[cap];
        this.capacity = cap;
    }

    public boolean isEmpty() {
        return 0 == this.size;
    }

    public int size() {
        return this.size;
    }

    public boolean push(final T value) {
        if (this.capacity == this.size) {
            return false;
        }
        this.array[(this.begin + this.size) % this.capacity] = value;
        ++this.size;
        return true;
    }

    public T pop() {
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
