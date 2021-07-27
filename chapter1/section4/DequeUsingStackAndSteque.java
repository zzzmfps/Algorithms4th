package chapter1.section4;

import chapter1.section3.Stack;
import chapter1.section3.Steque;
import convention.DequeConv;

public class DequeUsingStackAndSteque<T> implements DequeConv<T> {

    private Stack<T> stack = new Stack<>(); // as head
    private Steque<T> steque = new Steque<>(); // as tail
    int size1, size2, common;

    @Override
    public boolean isEmpty() {
        return 0 == this.size();
    }

    @Override
    public int size() {
        return this.size1 + this.size2 + this.common;
    }

    @Override
    public void clear() {
        this.stack.clear();
        this.steque.clear();
        this.size1 = this.size2 = this.common = 0;
    }

    @Override
    public void offerFirst(T value) {
        this.stack.push(value);
        ++this.size1;
    }

    @Override
    public void offerLast(T value) {
        this.steque.offer(value);
        ++this.size2;
    }

    @Override
    public T peekFirst() {
        if (0 == this.size1 && 0 == this.common) {
            this.stack.clear();
            if (0 == this.size2) {
                this.steque.clear();
                return null;
            }
            Stack<T> tmp = new Stack<>();
            for (int i = this.steque.size() - this.size2; i > 0; --i) {
                this.steque.poll();
            }
            for (int i = this.size2; i > 0; --i) {
                tmp.push(this.steque.poll());
                this.steque.push(tmp.peek());
            }
            while (!tmp.isEmpty()) {
                this.stack.push(tmp.pop());
            }
            this.common = this.size2;
            this.size1 = this.size2 = 0;
        }
        return this.stack.peek();
    }

    @Override
    public T peekLast() {
        if (0 == this.size2 && 0 == this.common) {
            this.steque.clear();
            if (0 == this.size1) {
                this.stack.clear();
                return null;
            }
            Stack<T> tmp = new Stack<>();
            for (int i = this.size1; i > 0; --i) {
                tmp.push(this.stack.pop());
                this.steque.offer(tmp.peek());
            }
            this.stack.clear();
            while (!tmp.isEmpty()) {
                this.stack.push(tmp.pop());
            }
            this.common = this.size1;
            this.size1 = this.size2 = 0;
        }
        return this.steque.last();
    }

    @Override
    public T pollFirst() {
        this.peekFirst();
        if (0 == this.size1 && 0 == this.common) {
            return null;
        }
        T value = this.stack.pop();
        if (0 == this.size1) {
            this.steque.poll();
            --this.common;
        } else {
            --this.size1;
        }
        return value;
    }

    @Override
    public T pollLast() {
        this.peekLast();
        if (0 == this.size2 && 0 == this.common) {
            return null;
        }
        T value = this.steque.pop();
        if (0 == this.size2) {
            --this.common;
        } else {
            --this.size2;
        }
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                "DequeUsingStackAndSteque [common=" + common + ", size1=" + size1 + ", size2=" + size2 + "]");
        sb.append("\n\tStack:");
        this.stack.forEach(v -> sb.append(" " + v));
        sb.append("\n\tSteque:" + this.steque.toString().split("\n")[1]);
        return sb.toString();
    }

}
