package chapter1.section5;

import java.util.Iterator;

import chapter1.section3.RandomBag;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomGrid {

    private static class Connection {
        private int p, q;

        public Connection(int p, int q) {
            this.p = p;
            this.q = q;
        }
    }

    private static RandomBag<Connection> bag;
    private static Iterator<Connection> iterator;

    public static void main(int N) {
        RandomGrid.init(N);
        while (true) {
            Connection con = RandomGrid.generate(N);
            if (null == con) {
                break;
            }
            StdOut.printf("(%d -> %d)\n", con.p, con.q);
        }
    }

    private static Connection generate(int N) {
        if (RandomGrid.iterator.hasNext()) {
            return RandomGrid.iterator.next();
        }
        return null;
    }

    private static void init(int N) {
        RandomGrid.bag = new RandomBag<>(N);
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (StdRandom.bernoulli()) {
                    RandomGrid.bag.add(new Connection(i, j));
                } else {
                    RandomGrid.bag.add(new Connection(j, i));
                }
            }
        }
        RandomGrid.iterator = bag.iterator();
    }

}
