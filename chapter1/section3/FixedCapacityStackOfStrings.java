package chapter1.section3;

import convention.StackConv;

public class FixedCapacityStackOfStrings implements StackConv<String> {

    private final String[] a;
    private int N;

    public FixedCapacityStackOfStrings(final int cap) {
        this.a = new String[cap];
    }

    @Override
    public boolean isEmpty() {
        return 0 == this.N;
    }

    public boolean isFull() {
        return this.a.length == this.N;
    }

    @Override
    public int size() {
        return this.N;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.N; ++i) {
            this.a[i] = null;
        }
        this.N = 0;
    }

    @Override
    public void push(final String item) {
        this.a[this.N++] = item;
    }

    @Override
    public String peek() {
        return this.a[this.N - 1];
    }

    @Override
    public String pop() {
        return this.a[--this.N];
    }

}
