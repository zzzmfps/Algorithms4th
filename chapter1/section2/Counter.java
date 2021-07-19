package chapter1.section2;

public class Counter {

    private final String id;
    private int count = 0;

    public Counter(final String id) {
        this.id = id;
    }

    public void increment() {
        ++this.count;
    }

    public int tally() {
        return this.count;
    }

    @Override
    public String toString() {
        return "Counter [count=" + count + ", id=" + id + "]";
    }

}
