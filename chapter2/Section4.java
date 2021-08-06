package chapter2;

import java.util.Arrays;

import chapter2.section4.Heap;
import chapter2.section4.IndexPriorityQueue;
import chapter2.section4.Median;
import chapter2.section4.MinMaxQueue;
import chapter2.section4.PriorityQueue;
import chapter2.section4.PriorityQueueUsingLink;
import chapter2.section4.Sample;
import convention.base.AbstractSort.Mode;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Section4 {

    public static void exercise1() {
        PriorityQueue<Character> pq = new PriorityQueue<>();
        pq.insert('P');
        pq.insert('R');
        pq.insert('I');
        pq.insert('O');
        StdOut.println(pq.delete());
        pq.insert('R');
        StdOut.println(pq.delete());
        StdOut.println(pq.delete());
        pq.insert('I');
        StdOut.println(pq.delete());
        pq.insert('T');
        StdOut.println(pq.delete());
        pq.insert('Y');
        StdOut.println(pq.delete());
        StdOut.println(pq.delete());
        StdOut.println(pq.delete());
        pq.insert('Q');
        pq.insert('U');
        pq.insert('E');
        StdOut.println(pq.delete());
        StdOut.println(pq.delete());
        StdOut.println(pq.delete());
        pq.insert('U');
        StdOut.println(pq.delete());
        pq.insert('E');
    }

    public static void exercise5() {
        Character[] input = Section1.toCharacterArray("EASYQUESTION");
        PriorityQueue<Character> pq = new PriorityQueue<>(input, Mode.Less);
        StdOut.println(pq);
        StdOut.println(pq.delete());
        StdOut.println(pq);
    }

    public static void exercise6() {
        PriorityQueue<Character> pq = new PriorityQueue<>();
        pq.requireDetails(true);
        pq.insert('P');
        pq.insert('R');
        pq.insert('I');
        pq.insert('O');
        pq.delete();
        pq.insert('R');
        pq.delete();
        pq.delete();
        pq.insert('I');
        pq.delete();
        pq.insert('T');
        pq.delete();
        pq.insert('Y');
        pq.delete();
        pq.delete();
        pq.delete();
        pq.insert('Q');
        pq.insert('U');
        pq.insert('E');
        pq.delete();
        pq.delete();
        pq.delete();
        pq.insert('U');
        pq.delete();
        pq.insert('E');
        pq.requireDetails(false);
    }

    public static void exercise15() {
        Integer[] array = new Integer[20];
        for (int i = 0; i < array.length; ++i) {
            array[i] = StdRandom.uniform(20);
        }
        Heap.make(array);
        StdOut.println(Arrays.toString(array));
        StdOut.println(Heap.check(array));
        array[1] -= 3;
        StdOut.println(Arrays.toString(array));
        StdOut.println(Heap.check(array));
    }

    public static void exercise24() {
        PriorityQueueUsingLink<Character> pq = new PriorityQueueUsingLink<>();
        pq.insert('P');
        pq.insert('R');
        pq.insert('I');
        pq.insert('O');
        StdOut.println(pq.delete());
        pq.insert('R');
        StdOut.println(pq.delete());
        StdOut.println(pq.delete());
        pq.insert('I');
        StdOut.println(pq.delete());
        pq.insert('T');
        StdOut.println(pq.delete());
        pq.insert('Y');
        StdOut.println(pq.delete());
        StdOut.println(pq.delete());
        StdOut.println(pq.delete());
    }

    public static void exercise25(int N) { // very slow, better <= 10000
        class Cube implements Comparable<Cube> {
            Long value;
            int i, j;

            Cube(int i, int j) {
                this.value = Double.valueOf(Math.pow(i, 3.0) + Math.pow(j, 3.0)).longValue();
                this.i = i;
                this.j = j;
            }

            @Override
            public int compareTo(Cube o) {
                return this.value.compareTo(o.value);
            }
        }
        PriorityQueue<Cube> pq = new PriorityQueue<>(N + 1, Mode.Greater);
        for (int i = 0; i <= N; ++i) {
            pq.insert(new Cube(i, i));
        }
        Cube last = null;
        int count = 0, tmp = 1;
        while (!pq.isEmpty()) {
            Cube cube = pq.delete();
            if (null != last && last.value.equals(cube.value)) {
                ++tmp;
            } else {
                last = cube;
                if (tmp > 1) {
                    count += tmp * (tmp - 1) / 2; // combination
                    tmp = 1;
                }
            }
            if (cube.j < N) {
                pq.insert(new Cube(cube.i, cube.j + 1));
            }
        }
        // when n = 10^4, count = 41810, not 41570 mentioned by some blogs
        // for a certain sum, n equal pairs should be counted as C(n, 2), not (n-1)
        StdOut.println(count);
    }

    public static void exercise26() {
        Section4.exercise1();
        Section4.exercise24();
    }

    public static void exercise27() {
        Character[] input = Section1.toCharacterArray("EASYQUESTION");
        PriorityQueue<Character> pq = new PriorityQueue<>(input, Mode.Greater); // construct a min heap
        StdOut.println(pq.peek());
        // or just add a variable to record the min/max value
    }

    public static void exercise29() {
        MinMaxQueue<Integer> pq = new MinMaxQueue<>();
        for (int i = 0; i < 20; ++i) {
            pq.insert(i);
        }
        StdOut.println(pq.deleteMin());
        StdOut.println(pq.deleteMax());
        StdOut.println(pq.peekMin());
        StdOut.println(pq.peekMax());
    }

    public static void exercise30() {
        Median<Integer> medians = new Median<>();
        for (int i = 0; i < 10; ++i) {
            medians.insert(i);
            StdOut.print(medians.peek() + " ");
        }
        StdOut.println("\n***************");
        for (int i = 0; i < 10; ++i) {
            StdOut.print(medians.delete() + " ");
        }
        StdOut.println();
    }

    public static void exercise31() {
        int length = 1000000;
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Mode.Greater);
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Mode.Greater);
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < length; ++i) {
            pq1.insert(-i);
        }
        long t2 = System.currentTimeMillis();
        for (int i = 0; i < length; ++i) {
            pq2.insert(-i, true); // quicker if pq.length is very large, but slower if small
        }
        long t3 = System.currentTimeMillis();
        StdOut.println("normal, lgN:   " + (t2 - t1));
        StdOut.println("binary, lglgN: " + (t3 - t2));
    }

    public static void exercise33() {
        IndexPriorityQueue<Integer> ipq = new IndexPriorityQueue<>(10);
        ipq.insert(1, 1);
        ipq.insert(2, 4);
        ipq.insert(3, 9);
        StdOut.println(ipq.delete());
        StdOut.println(ipq.peek());
        StdOut.println(ipq.contains(2));
        StdOut.println(ipq.contains(3));
    }

    public static void exercise34() {
        IndexPriorityQueue<Integer> ipq = new IndexPriorityQueue<>(10);
        ipq.insert(1, 1);
        ipq.insert(2, 4);
        ipq.insert(3, 9);
        StdOut.println(ipq.peekIndex());
        ipq.change(3, -9);
        StdOut.println(ipq.peekIndex());
    }

    public static void exercise35() {
        Sample samples = new Sample(new double[] { 0.5, 1.1, 0.2, 0.2, 0.3, 0.9, 1.5 });
        for (int i = 0; i < 10; ++i) {
            StdOut.println(samples.random());
        }
        samples.change(2, 1.2);
        samples.change(6, 0.5);
        for (int i = 0; i < 10; ++i) {
            StdOut.println(samples.random());
        }
    }

    public static void exercise40() {
        int length = 10000;
        Integer[] array1 = new Integer[length];
        Integer[] array2 = new Integer[length];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = StdRandom.uniform(length);
            array2[i] = array1[i];
        }
        long t1 = System.currentTimeMillis();
        __exercise40(array1, false); // normal
        long t2 = System.currentTimeMillis();
        __exercise40(array2, true); // Floyd, sink to bottom and swim later
        long t3 = System.currentTimeMillis();
        StdOut.println(t2 - t1);
        StdOut.println(t3 - t2);
    }

    private static <T extends Comparable<T>> void __exercise40(T[] array, boolean useFloyd) {
        PriorityQueue<T> pq = new PriorityQueue<>();
        for (int i = 0; i < array.length / 2; ++i) {
            pq.insert(array[i]);
        }
        while (!pq.isEmpty()) {
            pq.delete(useFloyd);
        }
        for (int i = array.length / 2; i < array.length; ++i) {
            pq.insert(array[i]);
        }
        while (!pq.isEmpty()) {
            pq.delete(useFloyd);
        }
    }

    public static void main(String[] args) {
    }

}
