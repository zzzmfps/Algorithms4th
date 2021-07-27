package chapter1.section4;

import edu.princeton.cs.algs4.StdRandom;

public class GuessNumber {

    private final int number;
    private int diff = Integer.MAX_VALUE;

    public GuessNumber(int N) {
        this.number = 1 + StdRandom.uniform(N);
    }

    public int guessWithMemory(int x) { // return -1 if diffs are equal
        if (x == this.number) {
            this.diff = 0;
            return 0;
        }
        int newDiff = Math.abs(x - this.number);
        int closer = (newDiff < this.diff) ? 1 : -1;
        this.diff = newDiff;
        return closer;
    }

}
