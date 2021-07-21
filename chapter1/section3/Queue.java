package chapter1.section3;

public class Queue<T> {

    protected Node<T> front, last;

    public Queue() {
    }

    public Queue(final Queue<T> queue) {
        this.catenation(queue);
    }

    public boolean isEmpty() {
        return null == this.front;
    }

    public void enqueue(final T value) {
        final Node<T> node = new Node<T>(value, null);
        if (null == this.last) {
            this.front = this.last = node;
        } else {
            this.last.next = node;
            this.last = node;
        }
    }

    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        final T popped = this.front.value;
        if (this.front == this.last) {
            this.front = this.last = null;
        } else {
            this.front = this.front.next;
        }
        return popped;
    }

    public void catenation(final Queue<T> queue) {
        final Queue<T> tmp = new Queue<>();
        while (!queue.isEmpty()) {
            tmp.enqueue(queue.pop());
        }
        while (!tmp.isEmpty()) {
            final T value = tmp.pop();
            queue.enqueue(value);
            this.enqueue(value);
        }
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
