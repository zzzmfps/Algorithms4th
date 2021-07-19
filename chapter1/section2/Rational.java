package chapter1.section2;

public class Rational {

    private final long numerator;
    private final long denominator;

    public Rational(final long n, final long d) {
        assert 0 != d : "AssertionError: denominator is [0]";
        final long gcd = Rational.gcd(n, d);
        this.numerator = n / gcd;
        this.denominator = d / gcd;
    }

    public Rational plus(final Rational b) {
        final long n = this.numerator * b.denominator + b.numerator * this.denominator;
        final long d = this.denominator * b.denominator;
        return new Rational(n, d);
    }

    public Rational minus(final Rational b) {
        final long n = this.numerator * b.denominator - b.numerator * this.denominator;
        final long d = this.denominator * b.denominator;
        return new Rational(n, d);
    }

    public Rational times(final Rational b) {
        final long n = this.numerator * b.numerator;
        final long d = this.denominator * b.denominator;
        return new Rational(n, d);
    }

    public Rational divides(final Rational b) {
        assert 0 != b.numerator : "AssertionError: divisor is [0]";
        final long n = this.numerator * b.denominator;
        final long d = this.denominator * b.numerator;
        return new Rational(n, d);
    }

    private static long gcd(final long p, final long q) {
        return (0 == q ? p : Rational.gcd(q, p % q));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || this.getClass() != o.getClass()) {
            return false;
        }
        final Rational r = (Rational) o;
        return this.numerator == r.numerator && this.denominator == r.denominator;
    }

    @Override
    public String toString() {
        return "Rational [denominator=" + denominator + ", numerator=" + numerator + "]";
    }

}
