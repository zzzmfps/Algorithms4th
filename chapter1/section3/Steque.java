package chapter1.section3;

public class Steque<T> extends Queue<T> {

    public Steque() {
    }

    public Steque(final Steque<T> sq) {
        super(sq);
    }

    public void push(final T value) {
        final Node<T> node = new Node<T>(value, null);
        if (null == this.front) {
            this.front = this.last = node;
        } else {
            node.next = this.front;
            this.front = node;
        }
    }

    @Override
    public String toString() {
        return super.toString().replace("Queue", "Steque");
    }

}
