package chapter1.section3;

public class QueueUsingStacks<T> {

    private final Stack<T> in = new Stack<>();
    private final Stack<T> out = new Stack<>();

    public boolean isEmpty() {
        return this.in.isEmpty() && this.out.isEmpty();
    }

    public int size() {
        return this.in.size() + this.out.size();
    }

    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        if (this.out.isEmpty()) {
            while (!this.in.isEmpty()) {
                // amortized O(1), because each element will be moved once exactly
                this.out.push(this.in.pop());
            }
        }
        return this.out.peek();
    }

    public void enqueue(final T value) {
        this.in.push(value);
    }

    public T dequeue() {
        if (null == this.peek()) {
            return null;
        }
        return this.out.pop();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(
                "QueueUsingStacks [in=" + this.in.size() + ", out=" + this.out.size() + "]");
        sb.append("\n\tStack [ in]:");
        this.in.forEach(v -> sb.append(" " + v));
        sb.append("\n\tStack [out]:");
        this.out.forEach(v -> sb.append(" " + v));
        return sb.toString();
    }

}
