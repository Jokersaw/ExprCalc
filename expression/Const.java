package expression;

import java.util.Objects;

public class Const<T> implements ExpressionInterface<T> {

    private final T value;

    public Const(T value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x) {
        return value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public int hashCode() {
        return Objects.hashCode(value);
    }
}
