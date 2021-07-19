package chapter1.section2;

public class InvalidDateException extends Exception {

    private final String invalid;

    public InvalidDateException(final int y, final int m, final int d) {
        this.invalid = String.format("%d/%d/%d", y, m, d);
    }

    @Override
    public String getMessage() {
        return String.format("InvalidDateException: invalid date value [%s]", this.invalid);
    }

    @Override
    public String toString() {
        return "InvalidDateException [invalid=" + invalid + "]";
    }

}
