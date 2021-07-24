package chapter1.section3;

import java.util.Iterator;

import convention.StackConv;

public class Stack<T> implements StackConv<T>, Iterable<T> {

    private Node<T> first;
    private int N;
    private final int[] counts = new int[2];

    public Stack() {
    }

    public Stack(final Stack<T> stack) {
        this.catenation(stack);
    }

    @Override
    public boolean isEmpty() {
        return 0 == this.N;
    }

    @Override
    public int size() {
        return this.N;
    }

    @Override
    public void push(final T value) {
        this.first = new Node<T>(value, this.first);
        ++this.N;
        ++this.counts[0];
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.first.value;
    }

    @Override
    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        final T value = this.peek();
        this.first = this.first.next;
        --this.N;
        ++this.counts[1];
        return value;
    }

    public void catenation(final Stack<T> stack) {
        final Stack<T> tmp = new Stack<>();
        while (!stack.isEmpty()) {
            tmp.push(stack.pop());
        }
        while (!tmp.isEmpty()) {
            stack.push(tmp.pop());
            this.push(stack.peek());
        }
    }

    public static Stack<String> copy(final Stack<String> stack) {
        return new Stack<>(stack);
    }

    @Override
    public Iterator<T> iterator() {
        return new ListFailFastIterator<>(this.first, this.counts);
    }

}
