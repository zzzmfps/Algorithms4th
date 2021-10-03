package chapter1.section3;

import java.util.Arrays;

import common.ListNode;

public class MoveToFront {

    private ListNode<Character> front;
    private final boolean[] exists;

    public MoveToFront(final String str) {
        this.exists = new boolean[128];
        for (int i = 0; i < str.length(); ++i) {
            this.readChar(str.charAt(i));
        }
    }

    public void readChar(final char c) {
        if (this.exists[c]) {
            this.moveChar(c);
            return;
        }
        this.exists[c] = true;
        final ListNode<Character> newHead = new ListNode<Character>(c, this.front);
        this.front = newHead;
    }

    private void moveChar(final char c) {
        ListNode<Character> cur = new ListNode<Character>('\0', this.front);
        while (null != cur.next) {
            if (cur.next.value == c) {
                break;
            }
            cur = cur.next;
        }
        if (cur.next == this.front) {
            return;
        }
        final ListNode<Character> tmp = cur.next;
        cur.next = tmp.next;
        tmp.next = this.front;
        this.front = tmp;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(
                "MoveToFront [exists=" + Arrays.toString(exists) + ", head=" + front + "]\n\t");
        ListNode<Character> it = this.front;
        while (null != it) {
            builder.append((it == this.front ? "" : " -> ") + it.value);
            it = it.next;
        }
        return builder.toString();
    }

}
