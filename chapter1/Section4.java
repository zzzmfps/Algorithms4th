package chapter1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import chapter1.section3.List;
import chapter1.section3.Stack;
import chapter1.section4.DequeUsingStackAndSteque;
import chapter1.section4.DequeUsingThreeStacks;
import chapter1.section4.FixedCapacityStack;
import chapter1.section4.FixedCapacityStackOfInts;
import chapter1.section4.GuessNumber;
import chapter1.section4.ResizingArrayStack;
import chapter1.section4.StackUsingOneQueue;
import chapter1.section4.StaticSetOfInts;
import chapter1.section4.StequeUsingTwoStacks;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Section4 {

    public static void exercise2(int[] a, boolean printResult) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                for (int k = j + 1; k < N; ++k) {
                    int[] b = new int[] { a[i], a[j], a[k] };
                    Arrays.sort(b);
                    if (b[0] > 0 || b[2] < 0 || Integer.MIN_VALUE == b[1] || b[0] + b[2] != -b[1]) {
                        continue;
                    }
                    ++cnt;
                }
            }
        }
        if (printResult) {
            StdOut.println(cnt);
        }
    }

    public static void exercise3(int N, double timeLimit) {
        List<Double> times = new List<>();
        for (int i = N; true; i <<= 1) {
            double time = __exercise3_1(i) / 1000.0;
            times.add(time);
            if (time > timeLimit) {
                break;
            }
        }
        __exercise3_2(N, times);
    }

    private static long __exercise3_1(int N) {
        int max = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-max, max);
        }
        long t1 = System.currentTimeMillis();
        exercise2(a, false);
        long t2 = System.currentTimeMillis();
        return t2 - t1;
    }

    private static void __exercise3_2(int N, List<Double> times) {
        StdDraw.setXscale(-N, N << (times.size() - 1));
        StdDraw.setYscale(-1.0, times.last().value);
        StdDraw.line(-N, 0.0, N << (times.size() - 1), 0.0);
        StdDraw.line(0.0, -1.0, 0.0, times.last().value);

        double x1 = N, y1 = times.first().value;
        for (int i = 1; i < times.size(); ++i) {
            double x2 = (2.0 * x1), y2 = times.get(i);
            StdDraw.line(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }

        StdDraw.setYscale();
        StdDraw.setXscale();
    }

    public static void exercise8(int[] a) {
        int count = 0;
        Arrays.sort(a);
        for (int i = 0, j = 0; i < a.length; i = j) {
            while (++j < a.length && a[i] == a[j]) {
                continue;
            }
            count += (j - i) * (j - i - 1) / 2;
        }
        StdOut.println(count);
    }

    public static void exercise10(int[] array, int target) {
        StdOut.println(StaticSetOfInts.minIndexOf(array, target, 0, array.length));
    }

    public static void exercise11() {
        StaticSetOfInts set = new StaticSetOfInts(new int[] { 3, 2, 2, 7, 5, 2, 4, 9, 5, 3 });
        StdOut.println(set.howMany(2));
        StdOut.println(set.howMany(3));
        StdOut.println(set.howMany(4));
        StdOut.println(set.howMany(5));
    }

    public static void exercise12(int[] arr1, int[] arr2) {
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                StdOut.println(arr1[i]);
                while (++i < arr1.length && arr1[i] == arr1[i - 1]) {
                    continue; // skip repeated elements
                }
                while (++j < arr2.length && arr2[j] == arr2[j - 1]) {
                    continue;
                }
            } else if (arr1[i] < arr2[j]) {
                ++i;
            } else {
                ++j;
            }
        }
    }

    public static void exercise14(int[] array, int target) {
        int count = 0;
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                for (int k = j + 1; k < array.length; k++) {
                    int value = Long.valueOf((long) target - array[i] - array[j] - array[k]).intValue();
                    int index = StaticSetOfInts.rangedIndexOf(array, value, k + 1, array.length);
                    if (-1 != index) {
                        ++count;
                    }
                }
            }
        }
        StdOut.println(count);
    }

    public static void exercise15_1(int[] array, int target) { // 2-sum
        StdOut.println(__exercise15(array, target, 0, array.length));
    }

    public static void exercise15_2(int[] array, int target) { // 3-sum
        int count = 0;
        for (int i = 0; i < array.length; ++i) {
            int thisTurn = __exercise15(array, target - array[i], i + 1, array.length);
            // cannot break loop here when (0 == thisTurn)
            // see: [-10, -9, -5, 0, 11, 6], target = 1
            count += thisTurn;
        }
        StdOut.println(count);
    }

    private static int __exercise15(int[] array, int target, int l, int r) { // 2-sum helper
        int count = 0;
        int i = l, j = r - 1;
        while (i < j) {
            if (0 == target && (target == array[i] || target == array[j])) {
                int idx1 = StaticSetOfInts.minIndexOf(array, target, i, j + 1);
                int idx2 = StaticSetOfInts.maxIndexOf(array, target, i, j + 1);
                StdOut.println(i + " " + j + " " + idx1 + " " + idx2);
                if (-1 != idx1 && -1 != idx2) {
                    count += (idx2 - idx1) * (idx2 - idx1 + 1) / 2;
                }
                break;
            }
            int sum = array[i] + array[j];
            if (sum < target) {
                ++i;
            } else if (sum > target) {
                --j;
            } else {
                int i2 = i, j2 = j;
                while (i2 < j && array[i2] == array[i]) {
                    ++i2;
                }
                while (j2 > i && array[j2] == array[j]) {
                    --j2;
                }
                count += (i2 - i) * (j - j2);
                i = i2;
                j = j2;
            }
        }
        return count;
    }

    public static void exercise16(int[] array) {
        Arrays.sort(array);
        int x = -1, y = -1, diff = Integer.MAX_VALUE;
        for (int i = 1; i < array.length; ++i) {
            int d = array[i] - array[i - 1];
            if (d < diff) {
                diff = d;
                x = array[i - 1];
                y = array[i];
            }
        }
        StdOut.printf("min_diff = %d, x = %d, y = %d\n", diff, x, y);
    }

    public static void exercise17(int[] array) {
        int x = Integer.MAX_VALUE, y = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; ++i) {
            x = Math.min(x, array[i]);
            y = Math.max(y, array[i]);
        }
        StdOut.printf("min_diff = %d, x = %d, y = %d\n", y - x, x, y);
    }

    public static void exercise18(int[] array) {
        int index = __exercise18(array, 1, array.length - 1);
        StdOut.printf("index = %d: [%d, %d, %d]", index, array[index - 1], array[index], array[index + 1]);
    }

    private static int __exercise18(int[] array, int l, int r) {
        if (l >= r) {
            return -1;
        }
        int mid = l + (r - l) / 2;
        if (array[mid] < array[mid - 1] && array[mid] < array[mid + 1]) {
            return mid;
        }
        int i = (array[mid - 1] < array[mid]) ? __exercise18(array, l, mid) : -1;
        int j = (array[mid + 1] < array[mid]) ? __exercise18(array, mid + 1, r) : -1;
        return (i == j ? -1 : (-1 == i) ? j : i);
    }

    public static void exercise19(int[][] matrix) {
        // TODO
    }

    public static void exercise20(int[] array, int target) {
        if (target < array[0] && target < array[array.length - 1]) {
            StdOut.println(false);
            return;
        }
        int pivot = __exercise20(array);
        int i = StaticSetOfInts.rangedIndexOf(array, target, 0, pivot + 1);
        if (-1 != i) {
            StdOut.println(true);
            return;
        }
        int j = StaticSetOfInts.decrRangedIndexOf(array, target, pivot, array.length);
        StdOut.println((-1 != j) ? true : false);
    }

    private static int __exercise20(int[] array) { // find max
        int l = 0, r = array.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (0 == mid || array.length - 1 == mid) { // increase or decrease only?
                return mid; // 0 for decrease, another for increase
            }
            if (array[mid] > array[mid - 1] && array[mid] > array[mid + 1]) {
                return mid;
            }
            if (array[mid] > array[mid - 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return -1; // dead code for bitonic array
    }

    public static void exercise21() {
        StaticSetOfInts set = new StaticSetOfInts(new int[] { 6, 8, 3, 1, 3, 4, 4, 9, 2, 0 });
        StdOut.println(set.contains(0));
        StdOut.println(set.contains(7));
    }

    public static void exercise22(int[] array, int target) {
        StdOut.println(__exercise22(array, target, 0, array.length));
    }

    private static boolean __exercise22(int[] array, int target, int l, int r) {
        if (l >= r) {
            return false;
        }
        int d0 = 0, d1 = 0, d2 = 1;
        while (l + d1 < r) {
            if (target == array[l + d1]) {
                return true;
            }
            if (target < array[l + d1]) {
                return __exercise22(array, target, l + d0 + 1, l + d1);
            } else {
                d0 = d1;
                d1 = d2;
                d2 += d2;
            }
        }
        return __exercise22(array, target, l + d0 + 1, array.length);
    }

    public static void exercise27() {
        Section3.exercise49();
    }

    public static void exercise28() {
        StackUsingOneQueue<Integer> stack = new StackUsingOneQueue<>();
        stack.push(1);
        stack.push(2);
        StdOut.println(stack.peek());
        stack.pop();
        stack.push(3);
        StdOut.println(stack);
    }

    public static void exercise29() { // amortized O(1)
        final StequeUsingTwoStacks<Integer> steque = new StequeUsingTwoStacks<>();
        steque.push(1);
        steque.push(2);
        steque.offer(3);
        steque.offer(4);
        steque.pop();
        steque.poll();
        StdOut.print(steque); // 2 3
    }

    public static void exercise30() {
        DequeUsingStackAndSteque<Integer> deque = new DequeUsingStackAndSteque<>();
        deque.offerFirst(1);
        StdOut.println(deque.pollLast()); // 1
        deque.offerLast(2);
        deque.offerLast(3);
        deque.offerLast(4);
        StdOut.println(deque.pollFirst()); // 2
        StdOut.println(deque); // 3 4
    }

    public static void exercise31() {
        DequeUsingThreeStacks<Integer> deque = new DequeUsingThreeStacks<>();
        deque.offerFirst(1);
        StdOut.println(deque.pollLast()); // 1
        deque.offerLast(2);
        deque.offerLast(3);
        deque.offerLast(4);
        StdOut.println(deque.pollFirst()); // 2
        StdOut.println(deque); // 3 4
    }

    public static void exercise34(int N) { // [1, N], ~lgN
        if (1 == N) {
            StdOut.println("only possible answer is 1");
            return;
        }

        GuessNumber gn = new GuessNumber(N);
        if (0 == gn.guessWithMemory(1)) {
            StdOut.println("answer = 1, iteration = 1");
            return;
        }

        int last = 1, l = 1, r = N, iteration = 1;
        while (l <= r) {
            ++iteration;
            int chk = (l == r) ? l : l + r - last; // symmetric with `last`
            int res = gn.guessWithMemory(chk);
            if (0 == res) {
                StdOut.printf("answer = %d, iteration = %d", chk, iteration);
                return;
            } else if (res > 0 && chk <= l || res < 0 && chk >= r) {
                r = l + (r - l) / 2;
            } else {
                l = l + (r - l + 1) / 2;
            }
            last = chk;
        }
    }

    public static void exercise37(int round) {
        long t1 = System.currentTimeMillis();
        FixedCapacityStack<Integer> st1 = new FixedCapacityStack<>(10);
        for (int i = 0; i < round; ++i) {
            st1.push(i);
            st1.pop();
        }
        long t2 = System.currentTimeMillis();
        FixedCapacityStackOfInts st2 = new FixedCapacityStackOfInts(10);
        for (int i = 0; i < round; ++i) {
            st2.push(i);
            st2.pop();
        }
        long t3 = System.currentTimeMillis();
        StdOut.printf("time with wrapper:   %d ms\n", t2 - t1);
        StdOut.printf("time with primitive: %d ms\n", t3 - t2);
    }

    public static void exercise38(int round) {
        int[] nums = new int[1000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = StdRandom.uniform(-100, 100);
        }
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < round; ++i) {
            __exercise38_1(nums);
        }
        long t2 = System.currentTimeMillis();
        for (int i = 0; i < round; ++i) {
            __exercise38_2(nums);
        }
        long t3 = System.currentTimeMillis();
        StdOut.printf("ThreeSum origin with %d round(s): %d ms\n", t2 - t1);
        StdOut.printf("ThreeSum p135   with %d round(s): %d ms\n", t3 - t2);
    }

    private static int __exercise38_1(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                for (int k = j + 1; k < N; ++k) {
                    if (0 == a[i] + a[j] + a[k]) {
                        ++cnt;
                    }
                }
            }
        }
        return cnt;
    }

    private static int __exercise38_2(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                for (int k = 0; k < N; ++k) {
                    if (i < j && j < k) {
                        if (0 == a[i] + a[j] + a[k]) {
                            ++cnt;
                        }
                    }
                }
            }
        }
        return cnt;
    }

    public static void exercise43(int round) {
        // but ResizingArray-based stack is SLOWER than LinkedList-based one
        long t1 = System.currentTimeMillis();
        ResizingArrayStack<Integer> st1 = new ResizingArrayStack<>(16);
        for (int i = 0; i < round / 2; ++i) {
            st1.push(i);
            st1.peek();
        }
        for (int i = 0; i < round / 2; ++i) {
            st1.pop();
        }
        long t2 = System.currentTimeMillis();
        Stack<Integer> st2 = new Stack<>();
        for (int i = 0; i < round / 2; ++i) {
            st2.push(i);
            st1.peek();
        }
        for (int i = 0; i < round / 2; ++i) {
            st2.pop();
        }
        long t3 = System.currentTimeMillis();
        StdOut.printf("Stack using resizing array with %d round(s): %d ms\n", round, t2 - t1);
        StdOut.printf("Stack using linked list    with %d round(s): %d ms\n", round, t3 - t2);
    }

    public static void exercise44(int N) {
        int count = 0;
        for (int i = 0; i < 100; ++i) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < N; ++j) {
                int rd = StdRandom.uniform(N);
                if (set.contains(rd)) {
                    count += set.size();
                    break;
                }
                set.add(rd);
            }
        }
        StdOut.printf("100 rounds avg: %f\n", count / 100.0);
        StdOut.printf("sqrt(pi*N/2):     %f\n", Math.sqrt(Math.PI * N / 2.0));
    }

    public static void exercise45(int N) {
        int count = 0;
        for (int i = 0; i < 100; ++i) {
            Set<Integer> set = new HashSet<>();
            while (true) {
                ++count;
                int rd = StdRandom.uniform(N);
                set.add(rd);
                if (N == set.size()) {
                    break;
                }
            }
        }
        StdOut.printf("100 rounds avg: %f\n", count / 100.0);
        StdOut.printf("N*H(N):         %f\n", N * __exercise45(N));

    }

    private static double __exercise45(int N) { // harmony
        double res = 0.0;
        for (int i = 1; i < N; ++i) {
            res += 1.0 / i;
        }
        return res;
    }

    public static void main(final String[] args) {
    }

}
