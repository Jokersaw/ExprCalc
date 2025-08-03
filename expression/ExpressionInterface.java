package expression;

public interface ExpressionInterface<T>{
    T evaluate(T x);

    T evaluate(T x, T y, T z);
}
