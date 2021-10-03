package chapter1.section3;

import common.ListNode;
import convention.ContainerConv;

public class Steque<T> implements ContainerConv<T> {

    protected ListNode<T> front, last;
    protected int size;

    @Override
    public boolean isEmpty() {
        return null == this.front;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.front = this.last = null;
        this.size = 0;
    }

    public T front() {
        if (null == this.front) {
            return null;
        }
        return this.front.value;
    }

    public T last() {
        if (null == this.last) {
            return null;
        }
        return this.last.value;
    }

    public void push(final T value) {
        final ListNode<T> node = new ListNode<T>(value, null);
        if (null == this.last) {
            this.front = this.last = node;
        } else {
            this.last.next = node;
            this.last = node;
        }
        ++this.size;
    }

    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        ListNode<T> prev = new ListNode<T>(null, this.front);
        for (int i = 1; i < this.size; ++i) {
            prev = prev.next;
        }
        final T value = prev.next.value;
        prev.next = null;
        return value;
    }

    public void offer(final T value) {
        this.push(value);
    }

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
        --this.size;
        return polled;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Steque [front=" + front + ", last=" + last + "]\n\t");
        ListNode<T> it = this.front;
        while (null != it) {
            sb.append((it == this.front ? "" : " -> ") + it.value);
            it = it.next;
        }
        return sb.toString();
    }

}
