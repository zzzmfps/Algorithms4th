package chapter1.section4;

// uses primitive type `int`, cannot implements StackConv
public class FixedCapacityStackOfInts {

    private int[] array;
    private int size;

    public FixedCapacityStackOfInts(int cap) {
        this.array = new int[cap];
    }

    public boolean isEmpty() {
        return 0 == this.size;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.size = 0;
    }

    public void push(int value) {
        if (this.size < this.array.length) {
            this.array[this.size++] = value;
        }
    }

    public int peek() {
        if (this.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return this.array[this.size - 1];
    }

    public int pop() {
        if (this.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return this.array[--this.size];
    }

}
