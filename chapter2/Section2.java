package chapter2;

import chapter1.section3.Queue;
import chapter2.section2.Merge;
import edu.princeton.cs.algs4.StdOut;

public class Section2 {

    public static void exercise1() {
        Merge.requireDetails(false);
        Merge.sort(Section1.toCharacterArray("AEQSUYEINOST"));
    }

    public static void exercise2() {
        Merge.requireDetails(false);
        Merge.sort(Section1.toCharacterArray("EASYQUESTION"));
    }

    public static void exercise3() {
        Merge.requireDetails(false);
        Merge.sortBU(Section1.toCharacterArray("EASYQUESTION"));
    }

    public static <T extends Comparable<T>> Queue<T> exercise14(Queue<T> q1, Queue<T> q2) {
        Queue<T> res = new Queue<>();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.peek().compareTo(q2.peek()) < 0) {
                res.offer(q1.poll());
            } else {
                res.offer(q2.poll());
            }
        }
        while (!q1.isEmpty()) {
            res.offer(q1.poll());
        }
        while (!q2.isEmpty()) {
            res.offer(q2.poll());
        }
        return res;
    }

    public static <T extends Comparable<T>> Queue<T> exercise15(T[] vals) {
        Queue<Queue<T>> res = new Queue<>();
        for (int i = 0; i < vals.length; ++i) {
            Queue<T> q = new Queue<>();
            q.offer(vals[i]);
            res.offer(q);
        }
        while (res.size() > 1) {
            Queue<T> q1 = res.poll(), q2 = res.poll();
            res.offer(exercise14(q1, q2));
        }
        return res.poll();
    }

    public static void exercise16() {
        Merge.requireDetails(false);
        Merge.sortNatural(Section1.toCharacterArray("EASYQUESTION"));
    }

    public static void exercise19() {
        StdOut.println(Merge.inversePair(Section1.toCharacterArray("321"))); // 3
        StdOut.println(Merge.inversePair(Section1.toCharacterArray("156324"))); // 7
        StdOut.println(Merge.inversePair(Section1.toCharacterArray("abpqfgab"))); // 13
    }

    public static void exercise20() {
        Character[] array = Section1.toCharacterArray("EASYQUESTION");
        int[] index = Merge.sortIndex(array);
        StdOut.println("Original:");
        for (Character ch : array) {
            StdOut.print(ch + " ");
        }
        StdOut.println("Index:");
        for (int i : index) {
            StdOut.print(i + " ");
        }
        StdOut.println("Cooresponding:");
        for (int i : index) {
            StdOut.print(array[i] + " ");
        }
    }

    public static void exercise21(String[] n1, String[] n2, String[] n3) {
        Merge.sort(n1);
        Merge.sort(n2);
        Merge.sort(n3);
        int i = 0, j = 0, k = 0;
        while (i < n1.length && j < n2.length && k < n3.length) {
            if (n1[i].equals(n2[j]) && n1[i].equals(n3[k])) {
                StdOut.println(n1[i]);
                return;
            }
            String min = n1[i];
            min = n2[j].compareTo(min) < 0 ? n2[j] : min;
            min = n3[k].compareTo(min) < 0 ? n3[k] : min;
            i += min.equals(n1[i]) ? 1 : 0;
            j += min.equals(n2[j]) ? 1 : 0;
            k += min.equals(n3[k]) ? 1 : 0;
        }
        StdOut.println("not exists");
    }

    public static void main(String[] args) {
    }

}
