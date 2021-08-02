package chapter2;

import java.util.HashMap;
import java.util.Map;

import chapter1.section2.Transaction;
import chapter2.section1.Insertion;
import chapter2.section1.Selection;
import chapter2.section1.Shell;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Section1 {

    public static Character[] toCharacterArray(String str) {
        Character[] res = new Character[str.length()];
        for (int i = 0; i < str.length(); ++i) {
            res[i] = str.charAt(i);
        }
        return res;
    }

    public static void exercise1() {
        Selection.requireDetails(false);
        Selection.sort(toCharacterArray("EASYQUESTION"));
    }

    public static void exercise4() {
        Insertion.requireDetails(false);
        Insertion.sort(toCharacterArray("EASYQUESTION"));
    }

    public static void exercise9() {
        Shell.requireDetails(false);
        Shell.sort(toCharacterArray("EASYSHELLQUESTION"));
    }

    public static <T extends Comparable<T>> void exercise16(T[] array) { // as `check`
        Map<Integer, Integer> map = new HashMap<>();
        for (T value : array) {
            map.put(value.hashCode(), map.getOrDefault(value.hashCode(), 0) + 1);
        }
        Selection.sort(array);
        for (T value : array) {
            if (0 == map.getOrDefault(value.hashCode(), 0)) {
                StdOut.println(false);
                return;
            }
            map.put(value.hashCode(), map.get(value.hashCode()) - 1);
        }
        StdOut.println(Selection.isSorted(array));
    }

    public static <T extends Comparable<T>> void exercise17() {
        Selection.requireDetails(true);
        Selection.sort(new Integer[] { 5, 6, 8, 9, 3, 2, 4, 0, 1 });
    }

    public static void exercise22() {
        Transaction[] txs = Transaction.readTransactions();
        Insertion.sort(txs);
        for (Transaction tx : txs) {
            StdOut.println(tx);
        }
    }

    public static void exercise26() {
        int length = 20000;
        int[] primitive = StdRandom.permutation(length);
        Integer[] wrapper = new Integer[length];
        for (int i = 0; i < wrapper.length; ++i) {
            wrapper[i] = primitive[i];
        }
        long t1 = System.currentTimeMillis();
        Insertion.sort(primitive);
        long t2 = System.currentTimeMillis();
        Insertion.sort(wrapper);
        long t3 = System.currentTimeMillis();
        StdOut.println(t2 - t1);
        StdOut.println(t3 - t2);
    }

    public static void exercise29(int N) {
        Integer[] a = new Integer[N];
        Integer[] b = new Integer[N];
        for (int i = 0; i < a.length; ++i) {
            a[i] = StdRandom.uniform(N);
            b[i] = a[i];
        }
        long t1 = System.currentTimeMillis();
        Shell.useNormal();
        Shell.sort(a);
        long t2 = System.currentTimeMillis();
        Shell.sort(b);
        long t3 = System.currentTimeMillis();
        StdOut.printf("3*n+1  : %d ms\n", t2 - t1);
        StdOut.printf("new seq: %d ms\n", t3 - t2);
    }

    public static void main(String[] args) {
    }

}
