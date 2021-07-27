package chapter1.section4;

import java.util.Arrays;

public class StaticSetOfInts {

    private final int[] array;

    public StaticSetOfInts(final int[] target) {
        this.array = new int[target.length];
        for (int i = 0; i < target.length; ++i) {
            this.array[i] = target[i];
        }
        Arrays.sort(this.array);
    }

    public boolean contains(final int target) {
        return -1 != StaticSetOfInts.minIndexOf(this.array, target, 0, this.array.length);

    }

    public int howMany(final int target) {
        final int index = StaticSetOfInts.minIndexOf(this.array, target, 0, this.array.length);
        if (-1 == index) {
            return 0;
        }
        return StaticSetOfInts.maxIndexOf(this.array, target, 0, this.array.length) - index + 1;
    }

    public static int minIndexOf(final int[] array, final int target, final int _l, final int _r) {
        int l = _l, r = _r - 1;
        while (l < r) {
            final int mid = l + (r - l) / 2;
            if (target == array[mid]) {
                if (_l == mid || target != array[mid - 1]) {
                    return mid;
                }
            }
            if (array[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return -1;
    }

    public static int maxIndexOf(final int[] array, final int target, final int _l, final int _r) {
        int l = _l, r = _r - 1;
        while (l < r) {
            final int mid = l + (r - l) / 2;
            if (target == array[mid]) {
                if (_r - 1 == mid || target != array[mid + 1]) {
                    return mid;
                }
            }
            if (array[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static int rangedIndexOf(final int[] array, final int target, int l, int r) {
        while (l < r) {
            final int mid = l + (r - l) / 2;
            if (target == array[mid]) {
                return mid;
            }
            if (target < array[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static int decrRangedIndexOf(final int[] array, final int target, int l, int r) {
        while (l < r) {
            final int mid = l + (r - l) / 2;
            if (target == array[mid]) {
                return mid;
            }
            if (target < array[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return -1;
    }

}
