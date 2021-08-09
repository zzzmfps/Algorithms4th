package chapter2.section5;

public class Domain implements Comparable<Domain> {

    private final String[] domain;

    public Domain(final String dom) {
        this.domain = dom.split("\\.");
        for (int i = 0, j = this.domain.length - 1; i < j; ++i, --j) {
            final String tmp = this.domain[i];
            this.domain[i] = this.domain[j];
            this.domain[j] = tmp;
        }
    }

    @Override
    public int compareTo(final Domain o) {
        final int length = Math.max(this.domain.length, o.domain.length);
        for (int i = 0; i < length; ++i) {
            final int cmp = this.domain[i].compareTo(o.domain[i]);
            if (0 != cmp) {
                return cmp;
            }
        }
        return this.domain.length < o.domain.length ? -1 : 1;
    }

    @Override
    public String toString() {
        return String.join(".", this.domain);
    }

}
