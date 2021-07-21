package chapter1.section3;

import java.util.Iterator;

import chapter1.section3.DoubleList.DoubleNode;

public class DoubleListIterator<T> implements Iterator<T> {

    private DoubleNode<T> current;

    public DoubleListIterator(final DoubleNode<T> node) {
        this.current = node;
    }

    @Override
    public boolean hasNext() {
        return null != this.current;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T next() {
        final T value = this.current.getValue();
        this.current = (DoubleNode<T>) this.current.getNext();
        return value;
    }

}
