package chapter2.section5;

public class California implements Comparable<California> {

    private static final int[] order = { 7, 9, 16, 22, 18, 24, 11, 8, 17, 4, 19, 25, 5, 14, 3, 21, 2, 0, 10, 15, 20, 6,
            1, 13, 23, 12 };

    private final String name;

    public California(final String name) {
        this.name = name.toUpperCase();
    }

    @Override
    public int compareTo(final California o) {
        final int length = Math.max(this.name.length(), o.name.length());
        for (int i = 0; i < length; ++i) {
            final int c1 = California.order[this.name.charAt(i) - 65];
            final int c2 = California.order[o.name.charAt(i) - 65];
            if (c1 != c2) {
                return c1 < c2 ? -1 : 1;
            }
        }
        return this.name.length() < o.name.length() ? -1 : 1;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
