package chapter1.section3;

import java.util.Iterator;

public class ListIterator<T> implements Iterator<T> {

    private Node<T> current;

    public ListIterator(final Node<T> node) {
        this.current = node;
    }

    @Override
    public boolean hasNext() {
        return null != this.current;
    }

    @Override
    public T next() {
        final T value = this.current.value;
        this.current = this.current.next;
        return value;
    }

}
