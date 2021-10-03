package chapter1.section3;

import java.util.Iterator;

import common.ListNode;

public class ListIterator<T> implements Iterator<T> {

    private ListNode<T> current;

    public ListIterator(final ListNode<T> node) {
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
