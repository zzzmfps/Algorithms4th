package chapter2;

import chapter2.section1.Shell;
import chapter2.section3.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Section3 {

    public static void exercise2() {
        Character[] array = Section1.toCharacterArray("EASYQUESTION");
        Quick.requireDetails(false);
        Quick.sort(array);
    }

    public static <T extends Comparable<T>> void exercise5(T[] array, T val1, T val2) {
        int i = -1, j = array.length;
        while (true) {
            while (i < j && val1 == array[++i]) {
            }
            while (i < j && val2 == array[--j]) {
            }
            if (i >= j) {
                break;
            }
            array[i] = val1;
            array[j] = val2;
        }
        for (int k = 0; k < array.length; ++k) {
            StdOut.print(array[k] + ((k < array.length - 1) ? " " : "\n"));
        }
    }

    public static void exercise12() {
        Character[] array = Section1.toCharacterArray("BABABABACADABRA");
        Quick.requireDetails(false);
        Quick.sort3Way(array);
        StdOut.println(Quick.isSorted(array));
    }

    public static void exercise20() {
        Character[] array = Section1.toCharacterArray("BABABABACADABRA");
        Quick.sortNoRecursion(array);
        StdOut.println(Quick.isSorted(array));
    }

    public static void exercise22() {
        Integer[] array = new Integer[100000];
        for (int i = 0; i < array.length; ++i) {
            array[i] = StdRandom.uniform(100);
        }
        Quick.sort3Way2(array);
        StdOut.println(Quick.isSorted(array));
    }

    public static void exercise23() {
        Section3.exercise22();
    }

    public static void exercise24() {
        int length = 100000;
        Integer[] array = new Integer[length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = StdRandom.uniform(length / 10);
        }
        Quick.sortWithSampling(array); // slower than normal one, somewhere go wrong maybe
        StdOut.println(Quick.isSorted(array));
    }

    public static void exercise30() {
        int length = 1000000;
        for (int i = 0; i < 2; ++i) {
            Double[] guass = new Double[length];
            for (int j = 0; j < guass.length; ++j) {
                guass[j] = StdRandom.gaussian();
            }
            Integer[] poisson = new Integer[length];
            for (int j = 0; j < poisson.length; ++j) {
                poisson[j] = StdRandom.poisson(0.5);
            }
            Integer[] geometric = new Integer[length];
            for (int j = 0; j < geometric.length; ++j) {
                geometric[j] = StdRandom.geometric(0.5);
            }
            Integer[] discrete = new Integer[length];
            for (int j = 0; j < discrete.length; ++j) {
                discrete[j] = StdRandom.discrete(StdRandom.permutation(1000));
            }
            Integer[] bits = new Integer[length];
            for (int j = 0; j < bits.length; ++j) {
                bits[j] = StdRandom.bernoulli() ? 0 : 1;
            }
            Integer[] incre = new Integer[length];
            for (int j = 0; j < incre.length; ++j) {
                incre[j] = StdRandom.bernoulli() ? 0 : StdRandom.bernoulli() ? 1 : 2;
            }
            Integer[] halfRandom = new Integer[length];
            for (int j = 0; j < halfRandom.length; ++j) {
                halfRandom[j] = StdRandom.bernoulli() ? 0 : StdRandom.uniform(length);
            }
            StdOut.printf("will %sshuffle the arrays\n", (1 == i ? "" : "NOT "));
            if (1 == i) {
                StdRandom.shuffle(guass);
                StdRandom.shuffle(poisson);
                StdRandom.shuffle(geometric);
                StdRandom.shuffle(discrete);
                StdRandom.shuffle(bits);
                StdRandom.shuffle(incre);
                StdRandom.shuffle(halfRandom);
            }
            // benifits from shuffling: poisson, geometric, discrete, bits
            long t1 = System.currentTimeMillis();
            Shell.sort(guass); // 1041, 1132 (shuffled)
            long t2 = System.currentTimeMillis();
            Shell.sort(poisson); // 203, 70
            long t3 = System.currentTimeMillis();
            Shell.sort(geometric); // 207, 74
            long t4 = System.currentTimeMillis();
            Shell.sort(discrete); // 854, 484
            long t5 = System.currentTimeMillis();
            Shell.sort(bits); // 215, 72
            long t6 = System.currentTimeMillis();
            Shell.sort(incre); // 72, 71
            long t7 = System.currentTimeMillis();
            Shell.sort(halfRandom); // 278, 278
            long t8 = System.currentTimeMillis();
            StdOut.println(t2 - t1);
            StdOut.println(t3 - t2);
            StdOut.println(t4 - t3);
            StdOut.println(t5 - t4);
            StdOut.println(t6 - t5);
            StdOut.println(t7 - t6);
            StdOut.println(t8 - t7);
        }
    }

    public static void main(String[] args) {
    }

}
