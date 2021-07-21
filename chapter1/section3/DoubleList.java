package chapter1.section3;

/**
 * With nested class and static methods. Strange requirements.
 */
public class DoubleList {

    public static class DoubleNode<T> {

        private final T value;
        private DoubleNode<?> prev, next;

        public DoubleNode(final T v) {
            this.value = v;
        }

        public T getValue() {
            return this.value;
        }

        public DoubleNode<?> getPrev() {
            return this.prev;
        }

        public DoubleNode<?> getNext() {
            return this.next;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder(
                    "DoubleNode [next=" + (null != next) + ", prev=" + (null != prev) + ", value=" + value + "]");
            sb.append("\n\tprev: [" + this.value + "]");
            DoubleNode<?> tmp = this.prev;
            while (null != tmp) {
                sb.append(" -> " + tmp.value);
                tmp = tmp.prev;
            }
            sb.append("\n\tnext: [" + this.value + "]");
            tmp = this.next;
            while (null != tmp) {
                sb.append(" -> " + tmp.value);
                tmp = tmp.next;
            }
            return sb.toString();
        }

    }

    public static DoubleNode<?> insertBefore(final DoubleNode<?> pivot, final DoubleNode<?> node) {
        if (null == pivot || null == node) {
            return null;
        }
        if (null != pivot.prev) {
            node.prev = pivot.prev;
            pivot.prev.next = node;
        }
        node.next = pivot;
        pivot.prev = node;
        return node;
    }

    public static DoubleNode<?> insertAfter(final DoubleNode<?> pivot, final DoubleNode<?> node) {
        if (null == pivot || null == node) {
            return null;
        }
        if (null != pivot.next) {
            node.next = pivot.next;
            pivot.next.prev = node;
        }
        pivot.next = node;
        node.prev = pivot;
        return node;
    }

    public static boolean delete(DoubleNode<?> node) {
        if (null == node) {
            return false;
        }
        if (node.prev == node.next) {
            node = null;
        } else if (null == node.prev) {
            node.next.prev = null;
        } else if (null == node.next) {
            node.prev.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        return true;
    }

}
