package chapter1.section3;

import java.util.Iterator;

import convention.BagConv;

public class RandomBag<T> implements BagConv<T> {

    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public RandomBag(final int capacity) {
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
            this.array[i] = null;
        }
        this.size = 0;
    }

    @Override
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
