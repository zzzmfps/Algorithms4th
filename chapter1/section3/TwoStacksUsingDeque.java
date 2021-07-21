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
            deque.pushLeft(value);
            ++this.st1;
        } else {
            deque.pushRight(value);
            ++this.st2;
        }
    }

    public T pop(final StackId id) {
        if (id == StackId.Stack1) {
            if (0 == this.st1) {
                return null;
            }
            --this.st1;
            return deque.popLeft();
        }
        if (0 == this.st2) {
            return null;
        }
        --this.st2;
        return deque.popRight();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(
                "TwoStackUsingDeque [deque=" + deque + ", st1=" + st1 + ", st2=" + st2 + "]");

        final Stack<T> tmp = new Stack<>();
        sb.append("\n\tStack1: ");
        for (int i = 0; i < this.st1; ++i) {
            tmp.push(this.deque.popLeft());
            sb.append(" " + tmp.peek());
        }
        while (!tmp.isEmpty()) {
            this.deque.pushLeft(tmp.pop());
        }

        sb.append("\n\tStack2: ");
        for (int i = 0; i < this.st2; ++i) {
            tmp.push(this.deque.popRight());
            sb.append(" " + tmp.peek());
        }
        while (!tmp.isEmpty()) {
            this.deque.pushRight(tmp.pop());
        }

        return sb.toString();
    }

}
