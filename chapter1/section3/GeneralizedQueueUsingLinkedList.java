package chapter1.section3;

public class GeneralizedQueueUsingLinkedList<T> {

    private Node<T> first, last;
    private int size;

    public boolean isEmpty() {
        return 0 == this.size;
    }

    public int size() {
        return this.size;
    }

    public void insert(final T value) {
        final Node<T> node = new Node<T>(value, null);
        if (this.isEmpty()) {
            this.first = this.last = node;
        } else {
            this.last.next = node;
            this.last = node;
        }
        ++this.size;
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
            Node<T> cur = this.first;
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
