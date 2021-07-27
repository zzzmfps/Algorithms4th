package chapter1.section4;

import chapter1.section3.Stack;
import convention.ContainerConv;

public class StequeUsingTwoStacks<T> implements ContainerConv<T> {

    private Stack<T> stack1 = new Stack<>();
    private Stack<T> stack2 = new Stack<>();
    private int size1, size2, common;

    @Override
    public boolean isEmpty() {
        return 0 == this.size();
    }

    @Override
    public int size() {
        return this.size1 + this.size2;
    }

    @Override
    public void clear() {
        this.stack1.clear();
        this.stack2.clear();
        this.size1 = this.size2 = this.common = 0;
    }

    public void push(T value) {
        this.stack2.push(value);
        ++this.size2;
    }

    public T pop() {
        if (0 == this.size2) {
            this.stack2.clear();
            return null;
        }
        if (this.size2 <= this.common) { // share common element with stack1
            --this.size1;
            --this.common;
        }
        --this.size2;
        return this.stack2.pop();
    }

    public void offer(T value) {
        this.push(value);
    }

    public T poll() { // amortized O(1)
        if (0 == this.size1) {
            this.stack1.clear();
            if (0 == this.size2) {
                this.stack2.clear();
                return null;
            }
            // each element will be at most moved and cleared once
            Stack<T> tmp = new Stack<>();
            for (int i = 0; i < this.size2; ++i) {
                tmp.push(this.stack2.pop());
            }
            this.stack2.clear();
            while (!tmp.isEmpty()) {
                this.stack1.push(tmp.pop());
                this.stack2.push(this.stack1.peek());
            }
            this.common = this.size1 = this.size2;
        }
        --this.size1;
        --this.size2;
        --this.common;
        return this.stack1.pop();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                "StequeUsingTwoStacks [common=" + common + ", size1=" + size1 + ", size2=" + size2 + "]");
        sb.append("\n\tStack1:");
        this.stack1.forEach(v -> sb.append(" " + v));
        sb.append("\n\tStack2:");
        this.stack2.forEach(v -> sb.append(" " + v));
        sb.append("\n");
        return sb.toString();
    }

}
