package chapter1.section3;

public class Buffer {

    Stack<Character> prev = new Stack<>();
    Stack<Character> next = new Stack<>();

    public void insert(final char c) {
        this.prev.push(c);
    }

    public char delete() {
        return this.prev.pop();
    }

    public void left(int k) {
        while (k > 0 && !this.prev.isEmpty()) {
            this.next.push(this.prev.pop());
            --k;
        }
    }

    public void right(int k) {
        while (k > 0 && !this.next.isEmpty()) {
            this.prev.push(this.next.pop());
            --k;
        }
    }

    public int size() {
        return this.prev.size() + this.next.size();
    }

}
