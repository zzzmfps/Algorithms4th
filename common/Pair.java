package common;

public class Pair<T extends Comparable<T>, U extends Comparable<U>> implements Comparable<Pair<T, U>> {

    public T x;
    public U y;

    public Pair() {
    }

    public Pair(final T x_, final U y_) {
        this.x = x_;
        this.y = y_;
    }

    @Override
    public int compareTo(final Pair<T, U> o) {
        final int cmpX = this.x.compareTo(o.x);
        return 0 == cmpX ? this.y.compareTo(o.y) : cmpX;
    }

    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }

}
