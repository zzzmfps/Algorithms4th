package chapter1.section3;

import convention.ContainerConv;

public class Buffer implements ContainerConv<Character> {

    Stack<Character> prev = new Stack<>();
    Stack<Character> next = new Stack<>();

    @Override
    public boolean isEmpty() {
        return this.prev.isEmpty() && this.next.isEmpty();
    }

    @Override
    public int size() {
        return this.prev.size() + this.next.size();
    }

    @Override
    public void clear() {
        this.prev.clear();
        this.next.clear();
    }

    public void insert(final char c) {
        this.prev.push(c);
    }

    public char delete() {
        return this.prev.pop();
    }

    public void left(int k) {
        while (k > 0 && !this.prev.isEmpty()) {
            this.next.push(this.prev.pop());
            --k;
        }
    }

    public void right(int k) {
        while (k > 0 && !this.next.isEmpty()) {
            this.prev.push(this.next.pop());
            --k;
        }
    }

}
