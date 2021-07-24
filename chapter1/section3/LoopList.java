package chapter1.section3;

import convention.ListConv;

public class LoopList<T> implements ListConv<T> {

    private Node<T> last;
    private int N;

    @Override
    public boolean isEmpty() {
        return 0 == this.N;
    }

    @Override
    public int size() {
        return this.N;
    }

    public Node<T> front() {
        if (null == this.last) {
            return null;
        }
        return this.last.next;
    }

    public Node<T> last() {
        return this.last;
    }

    @Override
    public void add(final T value) {
        final Node<T> node = new Node<T>(value, this.front());
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
        Node<T> cur = this.last;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.N) {
            return null;
        }
        Node<T> prev = this.last;
        for (int i = 0; i < index; i++) {
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
        Node<T> it = this.front();
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
