package common;

public class Pair<T, U> {

    public T x;
    public U y;

    public Pair() {
    }

    public Pair(final T x_, final U y_) {
        this.x = x_;
        this.y = y_;
    }

    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }

}
