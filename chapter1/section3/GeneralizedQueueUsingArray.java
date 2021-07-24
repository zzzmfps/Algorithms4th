package chapter1.section3;

import java.util.Arrays;

import convention.QueueConv;

public class GeneralizedQueueUsingArray<T> implements QueueConv<T> {

    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public GeneralizedQueueUsingArray(final int cap) {
        this.array = (T[]) new Object[cap];
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
        return this.array[0];
    }

    @Override
    public T poll() {
        return this.delete(0);
    }

    public T delete(final int k) {
        if (k <= 0 || k > this.size) {
            return null;
        }
        final T value = this.array[k - 1];
        this.move(k);
        if (this.size < this.array.length / 4) {
            this.resize(this.array.length / 2);
        }
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

    private void move(final int k) {
        for (int i = k; i < this.size; ++i) {
            this.array[i - 1] = this.array[i];
        }
        this.array[--this.size] = null;
    }

    @Override
    public String toString() {
        return "GeneralizedQueueUsingArray [array=" + Arrays.toString(array) + ", size=" + size + "]";
    }

}
