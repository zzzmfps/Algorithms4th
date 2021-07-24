package chapter1.section3;

import convention.QueueConv;

public class Queue<T> implements QueueConv<T> {

    protected Node<T> front, last;
    protected int size;

    public Queue() {
    }

    public Queue(final Queue<T> queue) {
        this.catenation(queue);
    }

    @Override
    public boolean isEmpty() {
        return null == this.front;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void offer(final T value) {
        final Node<T> node = new Node<T>(value, null);
        if (null == this.last) {
            this.front = this.last = node;
        } else {
            this.last.next = node;
            this.last = node;
        }
    }

    @Override
    public T peek() {
        return this.front.value;
    }

    @Override
    public T poll() {
        if (this.isEmpty()) {
            return null;
        }
        final T polled = this.front.value;
        if (this.front == this.last) {
            this.front = this.last = null;
        } else {
            this.front = this.front.next;
        }
        return polled;
    }

    public void catenation(final Queue<T> queue) {
        final Queue<T> tmp = new Queue<>();
        while (!queue.isEmpty()) {
            tmp.offer(queue.poll());
        }
        while (!tmp.isEmpty()) {
            final T value = tmp.poll();
            queue.offer(value);
            this.offer(value);
        }
        this.size = queue.size;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Queue [front=" + front + ", last=" + last + "]\n\t");
        Node<T> it = this.front;
        while (null != it) {
            sb.append((it == this.front ? "" : " -> ") + it.value);
            it = it.next;
        }
        return sb.toString();
    }

}
