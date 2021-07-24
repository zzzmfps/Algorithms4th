package chapter1.section3;

public class TwoStacksUsingDeque<T> {

    public enum StackId {
        Stack1, Stack2
    };

    private final Deque<T> deque = new Deque<>();
    private int st1, st2;

    public boolean isEmpty(final StackId id) {
        return 0 == this.size(id);
    }

    public int size(final StackId id) {
        return (id == StackId.Stack1 ? this.st1 : this.st2);
    }

    public void push(final StackId id, final T value) {
        if (id == StackId.Stack1) {
            deque.offerFirst(value);
            ++this.st1;
        } else {
            deque.offerLast(value);
            ++this.st2;
        }
    }

    public T pop(final StackId id) {
        if (id == StackId.Stack1) {
            if (0 == this.st1) {
                return null;
            }
            --this.st1;
            return deque.pollFirst();
        }
        if (0 == this.st2) {
            return null;
        }
        --this.st2;
        return deque.pollLast();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(
                "TwoStackUsingDeque [deque=" + deque + ", st1=" + st1 + ", st2=" + st2 + "]");

        final Stack<T> tmp = new Stack<>();
        sb.append("\n\tStack1: ");
        for (int i = 0; i < this.st1; ++i) {
            tmp.push(this.deque.pollFirst());
            sb.append(" " + tmp.peek());
        }
        while (!tmp.isEmpty()) {
            this.deque.offerFirst(tmp.pop());
        }

        sb.append("\n\tStack2: ");
        for (int i = 0; i < this.st2; ++i) {
            tmp.push(this.deque.pollLast());
            sb.append(" " + tmp.peek());
        }
        while (!tmp.isEmpty()) {
            this.deque.offerLast(tmp.pop());
        }

        return sb.toString();
    }

}
