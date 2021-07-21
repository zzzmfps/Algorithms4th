package chapter1.section3;

public class Node<T> {

    public T value;
    public Node<T> next;

    public Node(final T v, final Node<T> n) {
        this.value = v;
        this.next = n;
    }

    @Override
    public String toString() {
        return "Node [next=" + (null != next) + ", value=" + value + "]";
    }

}
