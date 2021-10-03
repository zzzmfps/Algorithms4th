package chapter1.section3;

import common.ListNode;
import convention.ListConv;

public class LoopList<T> implements ListConv<T> {

    private ListNode<T> last;
    private int N;

    @Override
    public boolean isEmpty() {
        return 0 == this.N;
    }

    @Override
    public int size() {
        return this.N;
    }

    @Override
    public void clear() {
        this.last = null;
        this.N = 0;
    }

    public ListNode<T> front() {
        if (null == this.last) {
            return null;
        }
        return this.last.next;
    }

    public ListNode<T> last() {
        return this.last;
    }

    @Override
    public void add(final T value) {
        final ListNode<T> node = new ListNode<T>(value, this.front());
        if (null == this.last) {
            this.last = node;
            this.last.next = this.last;
        } else {
            node.next = this.last.next;
            this.last.next = node;
            this.last = node;
        }
        ++this.N;
    }

    @Override
    public T get(int index) {
        if (index >= this.N) {
            return null;
        }
        ListNode<T> cur = this.last;
        for (int i = 0; i < index; ++i) {
            cur = cur.next;
        }
        return cur.value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.N) {
            return null;
        }
        ListNode<T> prev = this.last;
        for (int i = 0; i < index; ++i) {
            prev = prev.next;
        }
        T removed = prev.next.value;
        if (1 == this.N) {
            this.last = null;
        } else {
            prev.next = prev.next.next;
            this.last = prev;
        }
        --this.N;
        return removed;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoopList [N=" + N + ", last=" + last + "]\n\t");
        ListNode<T> it = this.front();
        while (true) {
            sb.append((it == this.front() ? "" : " -> ") + it.value);
            it = it.next;
            if (it == this.front()) {
                break;
            }
        }
        return sb.toString();
    }

}
