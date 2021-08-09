package chapter2;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import chapter1.section3.List;
import chapter2.section1.Insertion;
import chapter2.section3.Quick;
import chapter2.section4.PriorityQueue;
import chapter2.section5.California;
import chapter2.section5.Domain;
import chapter2.section5.Square;
import chapter2.section5.StableBox;
import chapter2.section5.StablePriorityQueue;
import chapter2.section5.Vector;
import chapter2.section5.Version;
import common.Pair;
import convention.base.AbstractSort.Mode;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Section5 {

    public static void exercise2(String[] words) {
        Quick.sort(words);
        for (int i = 0, j; i < words.length; i = j) {
            for (j = i + 1; j < words.length; ++j) {
                if (!words[j].startsWith(words[i])) {
                    break;
                }
                int index = words[i].length();
                index = Section5.__exercise2(words, words[j].substring(index));
                if (-1 != index) {
                    StdOut.println(words[j]);
                }
            }
        }
    }

    private static int __exercise2(String[] words, String word) {
        int l = 0, r = words.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (word.equals(words[mid])) {
                return mid;
            }
            if (word.compareTo(words[mid]) < 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static void exercise4(String[] strs) { // as `dedup`
        PriorityQueue<String> pq = new PriorityQueue<>(strs, Mode.Greater);
        List<String> list = new List<>();
        while (!pq.isEmpty()) {
            String val = pq.delete();
            if (list.isEmpty() || !val.equals(list.last().value)) {
                list.add(val);
            }
        }
        String[] dedup = new String[list.size()];
        for (int i = 0; i < dedup.length; ++i) {
            dedup[i] = list.deleteHead();
        }
        for (String str : dedup) {
            StdOut.println(str);
        }
    }

    public static <T extends Comparable<T>> void exercise6(T[] array, int k) {
        StdOut.println(Quick.select(array, k));
    }

    public static void exercise8() {
        PriorityQueue<String> pq1 = new PriorityQueue<>();
        while (!StdIn.isEmpty()) {
            pq1.insert(StdIn.readLine());
        }
        if (pq1.isEmpty()) {
            return;
        }
        PriorityQueue<Pair<Integer, String>> pq2 = new PriorityQueue<>();
        String last = pq1.peek();
        int count = 0;
        while (true) {
            String str = pq1.delete();
            if (last.equals(str)) {
                ++count;
            } else {
                pq2.insert(new Pair<Integer, String>(count, last));
                last = str;
                count = 1;
            }
            if (pq1.isEmpty()) {
                pq2.insert(new Pair<Integer, String>(count, last));
                break;
            }
        }
        while (!pq2.isEmpty()) {
            Pair<Integer, String> p = pq2.delete();
            StdOut.println(p.y + " " + p.x);
        }
    }

    public static void exercise9() {
        PriorityQueue<Pair<Integer, String>> pq = new PriorityQueue<>(Mode.Greater);
        while (!StdIn.isEmpty()) {
            String date = StdIn.readString();
            Integer amount = StdIn.readInt();
            pq.insert(new Pair<Integer, String>(amount, date));
        }
        if (pq.isEmpty()) {
            return;
        }
        while (!pq.isEmpty()) {
            Pair<Integer, String> p = pq.delete();
            StdOut.println(p.y + " " + p.x);
        }
    }

    public static void exercise10() {
        PriorityQueue<Version> pq = new PriorityQueue<>();
        pq.insert(new Version("115.1.1"));
        pq.insert(new Version("115.10.1"));
        pq.insert(new Version("115.10.2"));
        while (!pq.isEmpty()) {
            StdOut.println(pq.delete());
        }
    }

    public static void exercise12() {
        Section5.exercise9();
    }

    public static void exercise13(int M) {
        PriorityQueue<Pair<Integer, String>> pq1 = new PriorityQueue<>();
        while (!StdIn.isEmpty()) {
            String task = StdIn.readString();
            Integer time = StdIn.readInt();
            pq1.insert(new Pair<Integer, String>(time, task));
        }
        PriorityQueue<Pair<Integer, String>> pq2 = new PriorityQueue<>(Mode.Greater);
        for (int i = 0; i < M; ++i) {
            pq2.insert(new Pair<Integer, String>(0, "Processor " + i));
        }
        while (!pq1.isEmpty()) {
            Pair<Integer, String> task = pq1.delete();
            Pair<Integer, String> processor = pq2.delete();
            StdOut.printf("Task [%s] assigned to [%s]\n", task.y, processor.y);
            processor.x += task.x;
            pq2.insert(processor);
        }
        StdOut.println("***************");
        while (!pq2.isEmpty()) {
            Pair<Integer, String> processor = pq2.delete();
            StdOut.printf("[%s] finished tasks with time [%d]\n", processor.y, processor.x);
        }
    }

    public static void exercise14() {
        PriorityQueue<Domain> pq = new PriorityQueue<>();
        while (!StdIn.isEmpty()) {
            pq.insert(new Domain(StdIn.readString()));
        }
        while (!pq.isEmpty()) {
            StdOut.println(pq.delete());
        }
    }

    public static void exercise16() {
        PriorityQueue<California> pq = new PriorityQueue<>(Mode.Greater);
        while (!StdIn.isEmpty()) {
            pq.insert(new California(StdIn.readString()));
        }
        while (!pq.isEmpty()) {
            StdOut.println(pq.delete());
        }
    }

    public static void exercise18() {
        String[] origin = new String[] { "a", "a", "b", "c", "a", "b", "c", "c", "a", "b" };
        StableBox<String>[] sb = StableBox.box(origin);
        Quick.sort(sb);
        String[] sorted = StableBox.unbox(sb, String.class);
        for (int i = 0; i < sorted.length; ++i) {
            StdOut.printf("%s - %s - %s\n", origin[i], sorted[i], sb[i]);
        }
    }

    public static void exercise20() {
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Mode.Greater);
        while (!StdIn.isEmpty()) {
            int beg = StdIn.readInt(), end = StdIn.readInt();
            pq.insert(new Pair<>(beg, end));
        }
        int busy = 0, spare = 0, begin = pq.peek().x, end = pq.peek().x;
        while (!pq.isEmpty()) {
            Pair<Integer, Integer> pair = pq.delete();
            if (end >= pair.x) {
                end = Math.max(end, pair.y);
            } else {
                busy = Math.max(busy, end - begin);
                spare = Math.max(spare, pair.x - end);
                begin = pair.x;
                end = pair.y;
            }
        }
        busy = Math.max(busy, end - begin);
        StdOut.println("max busy  time: " + busy); // during [min(begin), max(end)] of tasks
        StdOut.println("max spare time: " + spare);
    }

    public static void exercise21() {
        Vector[] vecs = new Vector[] { new Vector(3, 4, 5), new Vector(1, 2, 4), new Vector(1, 2, 3),
                new Vector(2, 2, 4), new Vector(2, 2, 3), new Vector(1, 3, 5) };
        Quick.sort(vecs);
        for (int i = 0; i < vecs.length; ++i) {
            StdOut.println(vecs[i]);
        }
    }

    public static void exercise24() {
        StablePriorityQueue<Integer> pq = new StablePriorityQueue<>(Mode.Greater);
        Integer[] array = { 128, 129, 128, 130, 128, 128 };
        for (int i = 0; i < array.length; ++i) {
            StdOut.println(System.identityHashCode(array[i]));
            pq.insert(array[i]);
        }
        StdOut.println("***************");
        while (!pq.isEmpty()) {
            Integer i = pq.delete();
            StdOut.printf("%d -> %d\n", System.identityHashCode(i), i);
        }
    }

    public static void exercise27() {
        Integer[] array = new Integer[] { 5, 3, 6, 8, 9, 3, 1, 2, 4, 1 };
        int[] index = Insertion.indirectSort(array);
        StdOut.println(Arrays.toString(array));
        for (int i = 0; i < index.length; ++i) {
            StdOut.printf("index = %d, value = %d\n", index[i], array[index[i]]);
        }
    }

    public static void exercise28() {
        File dir = new File(StdIn.readString());
        if (!dir.exists()) {
            return;
        }
        String[] files = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir, name).isFile();
            }
        });
        Quick.sort(files);
        StdOut.println(Arrays.toString(files));
    }

    public static void exercise32() {
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Square> pq = new PriorityQueue<>(Mode.Greater);

        pq.insert(new Square(new int[][] { { 5, 4, 3 }, { 2, 8, 6 }, { 0, 1, 7 } }));
        visited.add(Integer.valueOf(pq.peek().getSeq()));
        while (!pq.isEmpty()) {
            Square cur = pq.delete();
            if (cur.check()) {
                StdOut.println(cur);
                return;
            }
            Square[] nexts = cur.move();
            for (int i = 0; i < nexts.length; ++i) {
                int iVal = Integer.valueOf(nexts[i].getSeq());
                if (!visited.contains(iVal)) {
                    visited.add(iVal);
                    pq.insert(nexts[i]);
                }
            }
        }
        StdOut.println("cannot find a way");
    }

    public static void main(String[] args) throws IOException {
    }

}
