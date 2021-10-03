package chapter1.section3;

import java.util.ConcurrentModificationException;

import common.ListNode;

public class ListFailFastIterator<T> extends ListIterator<T> {

    private final int[] origin;
    private final int[] counts;

    public ListFailFastIterator(final ListNode<T> node, final int[] counts) {
        super(node);
        this.origin = new int[] { counts[0], counts[1] };
        this.counts = counts;
    }

    @Override
    public boolean hasNext() {
        this.checkCounts();
        return super.hasNext();
    }

    @Override
    public T next() {
        this.checkCounts();
        return super.next();
    }

    private void checkCounts() {
        if (this.origin[0] != this.counts[0]) {
            throw new ConcurrentModificationException("called push() while iterating");
        }
        if (this.origin[1] != this.counts[1]) {
            throw new ConcurrentModificationException("called pop() while iterating");
        }
    }

}
