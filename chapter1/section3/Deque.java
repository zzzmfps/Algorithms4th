package chapter1.section3;

import java.util.Iterator;

import chapter1.section3.DoubleList.DoubleNode;

public class Deque<T> implements Iterable<T> {

    private DoubleNode<T> head, tail;
    private int N;

    public boolean isEmpty() {
        return 0 == this.N;
    }

    public int size() {
        return this.N;
    }

    @SuppressWarnings("unchecked")
    public void pushLeft(final T value) {
        final DoubleNode<T> node = new DoubleNode<T>(value);
        if (0 == this.N++) {
            this.tail = this.head = node;
        } else {
            this.head = (DoubleNode<T>) DoubleList.insertBefore(this.head, node);
        }
    }

    @SuppressWarnings("unchecked")
    public void pushRight(final T value) {
        final DoubleNode<T> node = new DoubleNode<T>(value);
        if (0 == this.N++) {
            this.head = this.tail = node;
        } else {
            this.tail = (DoubleNode<T>) DoubleList.insertAfter(this.tail, node);
        }
    }

    @SuppressWarnings("unchecked")
    public T popLeft() {
        if (0 == this.N) {
            return null;
        }
        final T left = this.head.getValue();
        if (1 == this.N--) {
            this.head = this.tail = null;
        } else {
            final DoubleNode<?> tmp = this.head.getNext();
            DoubleList.delete(this.head);
            this.head = (DoubleNode<T>) tmp;
        }
        return left;
    }

    @SuppressWarnings("unchecked")
    public T popRight() {
        if (0 == this.N) {
            return null;
        }
        final T right = this.tail.getValue();
        if (1 == this.N--) {
            this.head = this.tail = null;
        } else {
            final DoubleNode<?> tmp = this.tail.getPrev();
            DoubleList.delete(this.tail);
            this.tail = (DoubleNode<T>) tmp;
        }
        return right;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleListIterator<>(this.head);
    }

}
