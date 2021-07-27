package chapter1.section4;

import convention.StackConv;

public class FixedCapacityStack<T> implements StackConv<T> {

    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public FixedCapacityStack(int cap) {
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
    public void clear() {
        for (int i = 0; i < this.size; ++i) {
            this.array[i] = null;
        }
        this.size = 0;
    }

    @Override
    public void push(T value) {
        if (this.size < this.array.length) {
            this.array[this.size++] = value;
        }
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.array[this.size - 1];
    }

    @Override
    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        T value = this.array[--this.size];
        this.array[this.size] = null;
        return value;
    }

}
