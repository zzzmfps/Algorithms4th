package chapter1;

import chapter1.section5.CompressedWeightedQuickUnion;
import chapter1.section5.RandomGrid;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Section5 {

    public static int exercise17(int N) { // as `ErdosRenyi`
        CompressedWeightedQuickUnion us = new CompressedWeightedQuickUnion(N);
        for (int i = 0; true; ++i) {
            if (1 == us.count()) {
                return i;
            }
            int p = StdRandom.uniform(N), q = StdRandom.uniform(N);
            if (!us.connected(p, q)) {
                us.union(p, q);
            }
        }
    }

    public static void exercise18(int N) {
        RandomGrid.main(N);
    }

    public static void exercise21(int N) {
        int real = exercise17(N);
        int guess = Double.valueOf(0.5 * N * Math.log(N)).intValue();
        StdOut.println("generated: " + real);
        StdOut.println("guess:     " + guess);
    }

    public static void main(String[] args) {
    }

}
