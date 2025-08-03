package expression.generic;

public interface TypeOperation<T extends Number> {
    T add(T x, T y);
    T subtract(T x, T y);
    T multiply(T x, T y);
    T divide(T x, T y);
    T not(T x);
    T castType(String x);
}
