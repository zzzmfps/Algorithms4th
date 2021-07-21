package chapter1.section3;

public class List<T> {

    private Node<T> first, last;
    private int N;

    public boolean isEmpty() {
        return 0 == this.N;
    }

    public int size() {
        return this.N;
    }

    public Node<T> first() {
        return this.first;
    }

    public Node<T> last() {
        return this.last;
    }

    public void add(final T value) {
        final Node<T> added = new Node<T>(value, null);
        if (0 == this.N) {
            this.first = this.last = added;
        } else {
            this.last.next = added;
            this.last = this.last.next;
        }
        ++this.N;
    }

    public T deleteHead() {
        if (0 == this.N) {
            return null;
        }
        final T deleted = this.first.value;
        if (1 == this.N) {
            this.first = this.last = null;
        } else {
            this.first = this.first.next;
        }
        --this.N;
        return deleted;
    }

    public T deleteTail() {
        if (0 == this.N) {
            return null;
        }
        final T deleted = this.last.value;
        if (1 == this.N) {
            this.first = this.last = null;
        } else {
            Node<T> cur = this.first;
            while (cur.next != this.last) {
                cur = cur.next;
            }
            cur.next = null;
            this.last = cur;
        }
        --this.N;
        return deleted;
    }

    public T delete(final int k) { // indexed from 0
        if (0 == k) {
            return this.deleteHead();
        }
        if (this.N - 1 == k) {
            return this.deleteTail();
        }
        if (k < 0 || k > this.N - 1) {
            return null;
        }
        Node<T> cur = this.first;
        for (int i = 1; i < k; ++i) {
            cur = cur.next;
        }
        final T deleted = cur.next.value;
        cur.next = cur.next.next;
        --this.N;
        return deleted;
    }

    public Node<T> find(final T value) {
        Node<T> cur = this.first;
        while (null != cur) {
            if (cur.value == value) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public void removeAfter(final Node<T> node) {
        if (null == node || null == node.next) {
            return;
        }
        if (this.first == node.next) {
            this.first = this.first.next;
        }
        if (this.last == node.next) {
            this.last = node;
        }
        node.next = node.next.next;
        --this.N;
    }

    public void insertAfter(final Node<T> node, final Node<T> newNode) {
        if (null == node || null == newNode) {
            return;
        }
        if (this.last == node) {
            this.last = newNode;
        }
        newNode.next = node.next;
        node.next = newNode;
        ++this.N;
    }

    public int remove(final T value) {
        int count = 0;
        Node<T> prev = new Node<T>(null, this.first);
        while (null != prev.next) {
            if (prev.next.value != value) {
                prev = prev.next;
                continue;
            }
            this.removeAfter(prev);
            ++count;
        }
        return count;
    }

    public static int max(Node<Integer> node) {
        int max = 0;
        while (null != node) {
            max = Math.max(max, node.value);
            node = node.next;
        }
        return max;
    }

    public static int maxWithRecursion(final Node<Integer> node) {
        if (null == node) {
            return 0;
        }
        return Math.max(node.value, maxWithRecursion(node.next));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(
                "List [N=" + N + ", first=" + first + ", last=" + last + "]\n\t");
        Node<T> it = this.first;
        while (null != it) {
            builder.append((it == this.first ? "" : " -> ") + it.value);
            it = it.next;
        }
        return builder.toString();
    }

}
