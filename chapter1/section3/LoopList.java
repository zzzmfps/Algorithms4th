package chapter1.section3;

public class LoopList<T> {

    private Node<T> last;
    private int N;

    public boolean isEmpty() {
        return 0 == this.N;
    }

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

    public void push(final T value) {
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

    public T pop() {
        if (0 == this.N) {
            return null;
        }
        final T popped = front().value;
        if (this.last == this.front()) {
            this.last = null;
        } else {
            this.last.next = this.front().next;
        }
        --this.N;
        return popped;
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
