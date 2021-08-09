package chapter2.section5;

import java.util.Arrays;

public class Vector implements Comparable<Vector> {

    private int[] array;

    public Vector(int... array) {
        this.array = array;
    }

    @Override
    public int compareTo(Vector o) {
        int length = Math.min(this.array.length, o.array.length);
        for (int i = 0; i < length; ++i) {
            if (this.array[i] != o.array[i]) {
                return this.array[i] < o.array[i] ? -1 : 1;
            }
        }
        int diff = this.array.length - o.array.length;
        return 0 == diff ? 0 : diff < 0 ? -1 : 1;
    }

    @Override
    public String toString() {
        return "Vector [array=" + Arrays.toString(array) + "]";
    }

}
