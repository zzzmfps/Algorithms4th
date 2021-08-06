package chapter2.section4;

import convention.PriorityQueueConv;

public class PriorityQueueUsingLink<T extends Comparable<T>> implements PriorityQueueConv<T> {

    private class Node {
        T value;
        Node father, lchild, rchild;

        Node(final T value, final Node father) {
            this.value = value;
            this.father = father;
        }
    }

    private Node sentinel = new Node(null, null); // root is rchild of `sentinel`
    private int size;

    @Override
    public boolean isEmpty() {
        return 0 == this.size;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.sentinel = new Node(null, null);
        this.size = 0;
    }

    @Override
    public void insert(final T value) {
        final Node f = this.getNode((++this.size) / 2);
        final Node newNode = new Node(value, f);
        if (0 == (this.size & 1)) {
            f.lchild = newNode;
        } else {
            f.rchild = newNode;
        }
        this.swim(newNode);
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.sentinel.rchild.value;
    }

    @Override
    public T delete() {
        if (this.isEmpty()) {
            return null;
        }
        Node root = this.getNode(1), tail = this.getNode(this.size--);
        this.exch(root, tail);

        Node f = tail.father;
        tail.father = null;
        if (0 == (this.size & 1)) {
            f.rchild = null;
        } else {
            f.lchild = null;
        }

        this.sink(root);
        return tail.value;
    }

    private void swim(Node node) {
        Node swimming = new Node(node.value, null);
        while (this.sentinel != node.father && this.less(node.father, swimming)) {
            node.value = node.father.value;
            node = node.father;
        }
        node.value = swimming.value;
    }

    private void sink(Node node) {
        Node sinking = new Node(node.value, null);
        while (null != node.lchild) {
            Node child = node.lchild;
            if (null != node.rchild && this.less(node.lchild, node.rchild)) {
                child = node.rchild;
            }
            if (!this.less(sinking, child)) {
                break;
            }
            node.value = child.value;
            node = child;
        }
        node.value = sinking.value;
    }

    private boolean less(final Node n1, final Node n2) {
        return n1.value.compareTo(n2.value) < 0;
    }

    private void exch(final Node n1, final Node n2) {
        final T tmp = n1.value;
        n1.value = n2.value;
        n2.value = tmp;
    }

    private Node getNode(final int i) {
        Node res = this.sentinel;
        if (i > 0) {
            final String bin = Integer.toBinaryString(i);
            for (int j = 0; j < bin.length(); ++j) {
                if ('0' == bin.charAt(j)) {
                    res = res.lchild;
                } else {
                    res = res.rchild;
                }
            }
        }
        return res;
    }

}
