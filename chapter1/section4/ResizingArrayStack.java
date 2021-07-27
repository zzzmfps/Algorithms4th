package chapter1.section4;

import java.util.Iterator;

import chapter1.section3.ArrayIterator;
import convention.StackConv;

public class ResizingArrayStack<T> implements StackConv<T>, Iterable<T> {

    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public ResizingArrayStack(int cap) {
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
        if (this.size == this.array.length) {
            this.resize(this.array.length * 2);
        }
        this.array[this.size++] = value;
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
        if (this.size < this.array.length / 4) {
            this.resize(this.array.length / 2);
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        for (int i = 0; i < this.size; ++i) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>(this.array, 0, this.size);
    }

}
