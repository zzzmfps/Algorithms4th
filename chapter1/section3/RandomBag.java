package chapter1.section3;

import java.util.Iterator;

public class RandomBag<T> implements Iterable<T> {

    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public RandomBag(final int capacity) {
        this.array = (T[]) new Object[Math.max(1, capacity)];
    }

    public boolean isEmpty() {
        return 0 == this.size;
    }

    public int size() {
        return this.size;
    }

    public void add(final T value) {
        if (this.size == this.array.length) {
            this.resize();
        }
        this.array[this.size++] = value;
    }

    @SuppressWarnings("unchecked")
    private void resize() { // only increase the size
        final T[] newArray = (T[]) new Object[2 * this.array.length];
        for (int i = 0; i < this.array.length; ++i) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRandomIterator<>(this.array, this.size);
    }

}
