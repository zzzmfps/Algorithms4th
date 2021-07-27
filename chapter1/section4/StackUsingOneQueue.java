package chapter1.section4;

import chapter1.section3.Queue;
import convention.StackConv;

public class StackUsingOneQueue<T> implements StackConv<T> {

    private Queue<T> queue = new Queue<>();

    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override
    public int size() {
        return this.queue.size();
    }

    @Override
    public void clear() {
        this.queue.clear();
    }

    @Override
    public void push(final T value) {
        this.queue.offer(value);
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        for (int i = this.queue.size(); i > 1; --i) {
            this.queue.offer(this.queue.poll());
        }
        T value = this.queue.peek();
        this.queue.offer(this.queue.poll());
        return value;
    }

    @Override
    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        for (int i = this.queue.size(); i > 1; --i) {
            this.queue.offer(this.queue.poll());
        }
        return this.queue.poll();
    }

    @Override
    public String toString() {
        return "StackUsingOneQueue [queue=" + queue + "]";
    }

}
