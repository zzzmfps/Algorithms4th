package chapter1.section3;

import convention.QueueConv;

public class QueueUsingStacks<T> implements QueueConv<T> {

    private final Stack<T> in = new Stack<>();
    private final Stack<T> out = new Stack<>();

    @Override
    public boolean isEmpty() {
        return this.in.isEmpty() && this.out.isEmpty();
    }

    @Override
    public int size() {
        return this.in.size() + this.out.size();
    }

    @Override
    public void clear() {
        this.in.clear();
        this.out.clear();
    }

    @Override
    public void offer(final T value) {
        this.in.push(value);
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        if (this.out.isEmpty()) {
            while (!this.in.isEmpty()) {
                // amortized O(1), because each element will be moved once exactly
                this.out.push(this.in.pop());
            }
        }
        return this.out.peek();
    }

    @Override
    public T poll() {
        if (null == this.peek()) {
            return null;
        }
        return this.out.pop();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(
                "QueueUsingStacks [in=" + this.in.size() + ", out=" + this.out.size() + "]");
        sb.append("\n\tStack [ in]:");
        this.in.forEach(v -> sb.append(" " + v));
        sb.append("\n\tStack [out]:");
        this.out.forEach(v -> sb.append(" " + v));
        return sb.toString();
    }

}
