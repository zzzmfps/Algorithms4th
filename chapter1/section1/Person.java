package chapter1.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Person {

    public String name;
    public int x, y;
    public double ratio;

    public Person() {
        this.name = StdIn.readString();
        this.x = StdIn.readInt();
        this.y = StdIn.readInt();
        this.ratio = Double.valueOf(x) / Double.valueOf(y);
    }

    public void print() {
        StdOut.printf("%s\t%d\t%d\t%.3f\n", this.name, this.x, this.y, this.ratio);
    }

}
