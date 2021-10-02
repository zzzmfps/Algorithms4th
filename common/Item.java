package common;

public class Item<T extends Comparable<T>, U> implements Comparable<Item<T, U>> {

    public T x;
    public U y;

    public Item() {
    }

    public Item(final T x_, final U y_) {
        this.x = x_;
        this.y = y_;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>, U> Item<T, U>[] makeArray(int length) {
        return new Item[length];
    }

    @Override
    public int compareTo(final Item<T, U> o) {
        return this.x.compareTo(o.x);
    }

    @Override
    public String toString() {
        return "Item [x=" + x + ", y=" + y + "]";
    }

}
