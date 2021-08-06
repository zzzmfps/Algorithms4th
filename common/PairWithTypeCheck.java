package common;

public class PairWithTypeCheck<T, U> extends Pair<T, U> {

    private final Class<T> typeT;
    private final Class<U> typeU;

    public PairWithTypeCheck(Class<T> typeT, Class<U> typeU) {
        super();
        this.typeT = typeT;
        this.typeU = typeU;
    }

    public PairWithTypeCheck(T x_, U y_, Class<T> typeT, Class<U> typeU) {
        super(x_, y_);
        this.typeT = typeT;
        this.typeU = typeU;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || this.getClass() != o.getClass()) {
            return false;
        }
        PairWithTypeCheck<T, U> o_ = (PairWithTypeCheck<T, U>) o;
        if (this.typeT != o_.typeT || this.typeU != o_.typeU) {
            return false;
        }
        return this.x == o_.x && this.y == o_.y;
    }

}
