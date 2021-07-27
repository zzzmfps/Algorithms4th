package chapter1.section4;

import chapter1.section3.Stack;
import convention.DequeConv;

public class DequeUsingThreeStacks<T> implements DequeConv<T> {

    private Stack<T> st1 = new Stack<>(); // as head
    private Stack<T> st2 = new Stack<>(); // as tail
    private Stack<T> st3 = new Stack<>();
    private int size1, size2, common;

    @Override
    public boolean isEmpty() {
        return 0 == this.size();
    }

    @Override
    public int size() {
        return this.size1 + this.size2 + this.common;
    }

    @Override
    public void clear() {
        this.st1.clear();
        this.st2.clear();
        this.size1 = this.size2 = this.common = 0;
    }

    @Override
    public void offerFirst(T value) {
        this.st1.push(value);
        ++this.size1;
    }

    @Override
    public void offerLast(T value) {
        this.st2.push(value);
        ++this.size2;
    }

    @Override
    public T peekFirst() {
        if (0 == this.size1 && 0 == this.common) {
            this.st1.clear();
            if (0 == this.size2) {
                this.st2.clear();
                return null;
            }
            for (int i = this.size2; i > 0; --i) {
                this.st1.push(this.st2.pop());
                this.st3.push(this.st1.peek());
            }
            this.st2.clear();
            while (!this.st3.isEmpty()) {
                this.st2.push(this.st3.pop());
            }
            this.common = this.st1.size();
            this.size1 = this.size2 = 0;
        }
        return this.st1.peek();
    }

    @Override
    public T peekLast() {
        if (0 == this.size2 && 0 == this.common) {
            this.st2.clear();
            if (0 == this.size1) {
                this.st1.clear();
                return null;
            }
            for (int i = this.size1; i > 0; --i) {
                this.st2.push(this.st1.pop());
                this.st3.push(this.st2.peek());
            }
            this.st1.clear();
            while (!this.st3.isEmpty()) {
                this.st1.push(this.st3.pop());
            }
            this.common = this.st2.size();
            this.size1 = this.size2 = 0;
        }
        return this.st2.peek();
    }

    @Override
    public T pollFirst() {
        this.peekFirst();
        if (this.st1.isEmpty()) {
            return null;
        }
        if (0 == this.size1) {
            --this.common;
        } else {
            --this.size1;
        }
        return this.st1.pop();
    }

    @Override
    public T pollLast() {
        this.peekLast();
        if (this.st2.isEmpty()) {
            return null;
        }
        if (0 == this.size2) {
            --this.common;
        } else {
            --this.size2;
        }
        return this.st2.pop();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                "DequeUsingStackAndSteque [common=" + common + ", size1=" + size1 + ", size2=" + size2 + "]");
        sb.append("\n\tStack1:");
        this.st1.forEach(v -> sb.append(" " + v));
        sb.append("\n\tStack2:");
        this.st2.forEach(v -> sb.append(" " + v));
        sb.append("\n\tStack3:");
        this.st3.forEach(v -> sb.append(" " + v));
        return sb.toString();
    }

}
