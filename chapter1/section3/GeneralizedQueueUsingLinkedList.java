package chapter1.section3;

import common.ListNode;
import convention.QueueConv;

public class GeneralizedQueueUsingLinkedList<T> implements QueueConv<T> {

    private ListNode<T> first, last;
    private int size;

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
        this.first = this.last = null;
        this.size = 0;
    }

    @Override
    public void offer(final T value) {
        final ListNode<T> node = new ListNode<T>(value, null);
        if (this.isEmpty()) {
            this.first = this.last = node;
        } else {
            this.last.next = node;
            this.last = node;
        }
        ++this.size;
    }

    @Override
    public T peek() {
        return this.first.value;
    }

    @Override
    public T poll() {
        return this.delete(0);
    }

    public T delete(final int k) {
        if (k <= 0 || k > this.size) {
            return null;
        }
        T value = null;
        if (1 == k) {
            value = this.first.value;
            this.first = this.first.next;
        } else {
            ListNode<T> cur = this.first;
            for (int i = 2; i < k; ++i) {
                cur = cur.next;
            }
            value = cur.next.value;
            cur.next = cur.next.next;
        }
        --this.size;
        return value;
    }

    @Override
    public String toString() {
        return "GeneralizedQueue [first=" + first + ", last=" + last + ", size=" + size + "]";
    }

}
