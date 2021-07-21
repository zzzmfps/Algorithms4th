package chapter1.section3;

public class FixedCapacityStackOfStrings {

    private final String[] a;
    private int N;

    public FixedCapacityStackOfStrings(final int cap) {
        a = new String[cap];
    }

    public boolean isEmpty() {
        return 0 == this.N;
    }

    public boolean isFull() {
        return a.length == N;
    }

    public int size() {
        return N;
    }

    public void push(final String item) {
        a[N++] = item;
    }

    public String pop() {
        return a[--N];
    }

}
