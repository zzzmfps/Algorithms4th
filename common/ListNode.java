package common;

public class ListNode<T> {

    public T value;
    public ListNode<T> next;

    public ListNode(final T v) {
        this.value = v;
        this.next = null;
    }

    public ListNode(final T v, final ListNode<T> n) {
        this.value = v;
        this.next = n;
    }

    @Override
    public String toString() {
        return "Node [next=" + (null != next) + ", value=" + value + "]";
    }

}
