package chapter1.section3;

import java.util.Iterator;

import convention.QueueConv;
import edu.princeton.cs.algs4.StdRandom;

public class RandomQueue<T> implements QueueConv<T>, Iterable<T> {

    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public RandomQueue(final int capacity) {
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
    public void offer(final T value) {
        if (this.size == this.array.length) {
            this.resize(2 * this.array.length);
        }
        this.array[this.size++] = value;
    }

    @Override
    public T peek() {
        return this.sample();
    }

    public T sample() {
        if (this.isEmpty()) {
            return null;
        }
        return this.array[StdRandom.uniform(this.size)];
    }

    @Override
    public T poll() {
        if (this.isEmpty()) {
            return null;
        }
        if (this.size == this.array.length / 4) {
            this.resize(this.array.length / 2);
        }
        final int index = StdRandom.uniform(this.size);
        final T value = this.array[index];
        this.array[index] = this.array[this.size - 1];
        this.array[--this.size] = null;
        return value;
    }

    @SuppressWarnings("unchecked")
    private void resize(final int newSize) {
        final T[] newArray = (T[]) new Object[newSize];
        for (int i = 0; i < this.size; ++i) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRandomIterator<>(this.array, this.size);
    }

}
